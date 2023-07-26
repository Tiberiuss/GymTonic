<script setup lang="ts">
    import { onMounted, ref, toRefs, watch } from 'vue';
    import { useRouter } from 'vue-router';
    import { routineService } from '@/services/routine.service';
    import RoutineDateSelectorComponent from '@/components/RoutineDateSelectorComponent.vue';
    import { useStore } from 'vuex';
    import type { selectedOption, Exercise } from '@/types';
    import { workoutService } from '@/services/workout.service';

    const router = useRouter()
    const store = useStore()

    const routines = ref()
    const routineDone = ref<any>(null)
    const todayRef = ref<string>("")
    const status = ref<boolean>(false)
    const statusMsg = ref<string>("")
    const selectedOptions = ref<selectedOption>({
        date: "", 
        exercise: new Array<Exercise>(),
        id: "",
        name: ""
    })
    const child = ref(null)

    watch(selectedOptions, () => {
        store.commit('inicializeWorkout', {
            routine: selectedOptions.value.id,
            date: todayRef.value
        })
    })

    onMounted(async() => {
        const res = await routineService.getAll()

        if (res.error){
            status.value = true
            statusMsg.value = "Error on routines."
        }else if (res.result){
            routines.value = res.result.data
        }

        changeDay(new Date().toISOString().split('T')[0])
    })

    async function changeDay(day: string){
        selectedOptions.value = {
            date: "", 
            exercise: new Array<Exercise>(),
            id: "",
            name: ""
        }
        store.commit("cleanActualSets")

        const res = await workoutService.getByDate(day)

        if (res.error){
            routineDone.value = null
        } else if (res.result){
            routineDone.value = res.result.data[0]
        }

        todayRef.value = day

        store.commit('inicializeWorkout', {
            routine: selectedOptions.value.id,
            date: todayRef.value
        })
    }

    async function removeFromId(){
        if (routineDone.value != null){
            const res = await workoutService.removeSet(routineDone.value.id)
            
            if (res.error) {
                status.value = true
                statusMsg.value = "Can't remove the workout."
            }else if (res.result){
                routineDone.value = {
                    date: "", 
                    exercise: new Array<Exercise>(),
                    id: "",
                    name: ""
                }

                const res = await routineService.getAll()

                if (res.error){
                    status.value = true
                    statusMsg.value = "Error on routines."
                }else if (res.result){
                    routines.value = res.result.data
                }
            }
        } else {
            const res = await routineService.removeRoutine(selectedOptions.value.id)
            
            if (res.error) {
                status.value = true
                statusMsg.value = "Can't remove."
            }else if (res.result){
                selectedOptions.value = {
                    date: "", 
                    exercise: new Array<Exercise>(),
                    id: "",
                    name: ""
                }

                const res = await routineService.getAll()

                if (res.error){
                    status.value = true
                    statusMsg.value = "Error on routines."
                }else if (res.result){
                    routines.value = res.result.data
                }
            }
        }

        child.value.notify()
    }

    function createSet(){
        if (routineDone.value == null)
            router.push('/routine/' + selectedOptions.value.id + '/sets/' + todayRef.value)
        else{
            router.push('/workout/' + routineDone.value.id + '/sets/' + todayRef.value)
        }
    }

</script>

<template>
    <section class="routine">
    <RoutineDateSelectorComponent ref="child" @change-day="changeDay"></RoutineDateSelectorComponent>
    <p class="status" v-if="status">{{ statusMsg }}</p>
    <button @click="router.push('/routine/create')" class="button-routine">Create a routine</button>
    <div class="routines-div">
        <select v-if="routineDone == null || routineDone?.id == ''" v-model="selectedOptions" class="select-routine">
            <option v-bind:value="''" disabled>Select a routine.</option>
            <option v-for="routine in routines" v-bind:value="routine" v-bind:key="routine.name">
                {{ routine.name }}
            </option>
        </select>
        <button v-if="selectedOptions.id != '' || (routineDone != null && routineDone?.id != '')" class="delete-routine-index" @click="removeFromId">DELETE {{ (selectedOptions.id) != null ? "ROUTINE" : "WORKOUT" }}</button>
        <div v-if="selectedOptions.id != '' || (routineDone != null && routineDone?.id != '')" class="routine-show">
            <button class="see-routine-button" @click="createSet">
                <div>
                    <h1>SELECTED ROUTINE: {{ selectedOptions.name || routineDone?.routine?.name}}</h1>
                </div>
                <img class="img-routine" src="@/assets/images/routine-show.jpg"/>
            </button>
        </div>
    </div>
    </section>
</template>

<style>
.routine {
    padding: 2em;
}

.status {
    margin-left: 20%;
}

.routines-div {
    margin-left: 20%;
    margin-right: 20%;
    height: 45em;
}

.select-routine {
    background-color: var(--orange-color);
    border: 0px;
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
    width: 200px;
    height: 25px;
    outline: none;
    margin-right: 30px;
}

.button-routine {
    float: right;
    background-color: var(--orange-color);
    border: 0px;
    border-radius: 10px;
    height: 25px;
    margin-top: 10px;
    margin-right: 20%;
    transition: 500ms;
}

.button-routine:hover {
    background-color: var(--orange-dark-color);
}

.routine-show {
    position: relative;
    margin-left: 2.5vw;
    margin-top: 2em;
}

.see-routine-button {
    width: 100%;
    height: 100%;
    border-radius: 10vw;
    border: 0px;
    margin-top: 2vh;
    background-color: transparent;
    transition: 500ms;
    padding-left: 0%;
    padding-right: 0%;
}


.see-routine-button div {
    position: absolute;
    width: 100%;
    top: 45%;
    background-color: var(--orange-color);
}

.see-routine-button:hover {
    transform: scale(1.05);
}

.delete-routine-index {
    border: 0px;
    border-radius: 20px;
    background-color: var(--red-color);
}

.see-routine-button h1 {
    color: white;
    font-size: 3em;
}

.routine-show img {
    width: 100%;
    height: 100%;
    border-radius: 10vw;
}

@media screen and (max-width: 1200px){
    .see-routine-button h1 {

        font-size: 2em;
    }
}


@media screen and (max-width: 800px){
    .back{
        margin-left: 5%;
    }

    .button-routine {
        margin-right: 5%;
    }

    .routines-div{
        margin-left: 5%;
        margin-right: 5%;
    }

    .see-routine-button h1 {
        font-size: 1.7em;
    }
}


@media screen and (max-width: 400px){
    .select-routine {
        width: 82vw;
    }
    
    .button-routine {
        margin: 10px;
    }

    .see-routine-button h1 {
        font-size: 1.5em;
    }
}
</style>