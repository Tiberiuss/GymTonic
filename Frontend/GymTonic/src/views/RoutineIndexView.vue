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
        if (routineDone.value == null || routineDone.value.id == '')
            router.push('/routine/' + selectedOptions.value.id + '/sets/' + todayRef.value)
        else{
            router.push('/workout/' + routineDone.value.id + '/sets/' + todayRef.value)
        }
    }

</script>

<template>
    <section class="routine">
    <RoutineDateSelectorComponent ref="child" @change-day="changeDay"></RoutineDateSelectorComponent>
    <div class="routines-div">
        <p class="status" v-if="status">{{ statusMsg }}</p>
        <select v-if="routineDone == null || routineDone?.id == ''" v-model="selectedOptions" class="select-routine">
            <option v-bind:value="''" disabled>Select a routine.</option>
            <option v-for="routine in routines" v-bind:value="routine" v-bind:key="routine.name">
                {{ routine.name }}
            </option>
        </select>
        <button v-if="(routineDone != null && routineDone?.id != '')" class="delete-routine-index" @click="removeFromId">DELETE WORKOUT</button>
        <button @click="router.push('/routine/create')" class="button-routine">Create a routine</button>
        <div v-if="selectedOptions.id != '' || (routineDone != null && routineDone?.id != '')" class="routine-show">
            <button v-if="selectedOptions.id != ''" class="delete-routine-index" @click="removeFromId">DELETE ROUTINE</button>
            <button class="see-routine-button" @click="createSet">
                <img class="img-routine" src="@/assets/images/routine-show.jpg"/>
                <span class="img_text">
                    <p>SELECTED ROUTINE: {{ selectedOptions.name || routineDone?.routine?.name}}</p>
                </span>
            </button>
        </div>
    </div>
    </section>
</template>

<style>
.routine {
    width: 45em;
    margin-left: auto;
    margin-right: auto;
}

.routines-div {
    height: 35em;
    margin-top: 1em;
    background-color: var(--form-user-color);
    padding: 2em;
    border-radius: 30px;
}

.select-routine {
    background-color: var(--buttons-routines-color);
    border-radius: 20px;
    border: 0;
    width: 10em;
    height:30px;
    outline: none;
    margin-right: 30px;
    color: white;
    padding-left: 10px;
}

.button-routine {
    float: right;
    background-color: var(--buttons-routines-color);
    color: white;
    border: 0px;
    border-radius: 10px;
    height: 30px;
    font-size: 20px;
    transition: 500ms;
}

.button-routine:hover {
    background-color: var(--grey-color);
}

.routine-show {
    margin-top: 1em;
    height: 80%;
}

.see-routine-button {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    border-radius: 10px;
    border: 0px;
    background-color: transparent;
    transition: transform 500ms ease-in-out;
    padding: 0;
    margin-top: 1em;
}

.img_text {
    position: absolute;
    padding: 0.5em 0;
    font-size: 2.7em;
    letter-spacing: 0.1em;
    color: white;
    width: 100%;
    background: rgba(0, 0, 0, 0.8);
}

.see-routine-button:hover {
    transform: scale(1.05);
}

.delete-routine-index {
    border: 0px;
    border-radius: 10px;
    background-color: var(--red-color);
    margin-bottom: 1em;
}

.routine-show img {
    width: 100%;
    height: 100%;
    border-radius: 30px;
}



@media screen and (max-width: 800px){
    .routine {
        width: 22em;
    }
}
</style>