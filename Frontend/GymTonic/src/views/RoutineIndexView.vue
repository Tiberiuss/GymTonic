<script setup lang="ts">
    import { onMounted, ref, watch } from 'vue';
    import { useRouter } from 'vue-router';
    import { routineService } from '@/services/routine.service';
    import DateComponent from '@/components/date/DateComponent.vue';
    import { useStore } from 'vuex';
    import type { selectedOption, Exercise } from '@/types';

    const router = useRouter()
    const store = useStore()

    const routines = ref()
    const routineDone = ref<any>(null)
    const last = ref<boolean>(false)
    const status = ref<boolean>(false)
    const statusMsg = ref<string>("")
    const selectedOptions = ref<selectedOption>({
        date: "", 
        exercise: new Array<Exercise>(),
        id: "",
        name: ""
    })

    watch(selectedOptions, () => {
        store.commit('inicializeWorkout', selectedOptions.value.id)
    })

    onMounted(async() => {
        const res = await routineService.getAll()

        if (res.error){
            status.value = true
            statusMsg.value = "Error on routines."
        }else if (res.result){
            routines.value = res.result.data
        }
    })

    async function changeDay(day: string, lastDate: boolean){
        selectedOptions.value = {
            date: "", 
            exercise: new Array<Exercise>(),
            id: "",
            name: ""
        }
        store.commit("cleanActualSets")

        const res = await routineService.getByDate(day)

        if (res.error){
            routineDone.value = null
        } else if (res.result){
            routineDone.value = res.result.data[0]
        }

        last.value = lastDate
    }

</script>

<template>
    <DateComponent @change-day="changeDay"></DateComponent>
    <button class="back" @click="router.go(-1)">GO BACK</button>
    <button @click="router.push('/routine/create')" class="button-routine">Create a routine</button>
    <div class="routines-div">
        <select v-if="!routineDone && !last" v-model="selectedOptions" class="select-routine">
            <option v-bind:value="''" disabled>Select a routine.</option>
            <option v-for="routine in routines" v-bind:value="routine" v-bind:key="routine.name">
                {{ routine.name }}
            </option>
        </select>
        <div v-if="selectedOptions.id != '' || routineDone != null" class="routine-show">
            <button class="see-routine-button" @click="router.push('/routine/' + selectedOptions.id + '/sets')">
                <h1>SELECTED ROUTINE</h1>
                <p class="routine-name">NAME: {{ selectedOptions.name || routineDone.name}}</p>
                <img class="img-routine" src="@/assets/images/routine-show.jpg"/>
            </button>
        </div>
    </div>
</template>

<style>

.back {
    margin: 10px;
    border: 0px;
    border-radius: 5px;
    background-color: var(--red-color);
    margin-left: 20%;
    transition: 500ms;
}

.back:hover {
    background-color: #a83c1e;
}

.routines-div {
    margin-left: 20%;
    margin-right: 20%;
    height: 70%;
}

.select-routine {
    background-color: var(--orange-color);
    border: 0px;
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
    width: 200px;
    height: 25px;
    float: left;
    outline: none;
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
    background-color: rgb(154, 77, 4);
}

.routine-show {
    position: relative;
    height: 40%;
    margin-left: 2.5vw;
    margin-top: 2vh;
}

.see-routine-button {
    width: 100%;
    height: 100%;
    border-radius: 10vw;
    border: 0px;
    margin-top: 2vh;
    background-color: transparent;
    transition: 500ms;
}

.see-routine-button:hover {
    transform: scale(1.05);
}

.see-routine-button:hover h1 {
    z-index: 999;
}

.see-routine-button:hover p{
    z-index: 999;
}


.see-routine-button h1 {
    position: absolute;
    color: white;
    margin-left: 12%;
    margin-top: 5%;
    font-size: 3em;
}

.routine-show img {
    width: 100%;
    height: 100%;
    border-radius: 10vw;
}

.routine-name {
    position: absolute;
    color: white;
    font-size: 20px;
    margin-top: 20%;
    margin-left: 12%;
    font-size: 2em;
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