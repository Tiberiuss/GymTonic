<script setup lang="ts">
import ExerciseIndex from '@/components/index_components/ExerciseIndex.vue';
import { ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { routineService } from '@/services/routine.service';
import type { Exercise } from '@/types';

const store = useStore();
const router = useRouter();

const routineName = ref("")
const status = ref(false)
const statusMsg = ref("")

async function createRoutine(){
    if (routineName.value == ""){
        status.value = true
        statusMsg.value = "You can't create a routine with an empty name."
        return
    }
    if (routineName.value.match(/^[a-zA-Z0-9]+$/) === null) {
        status.value = true
        statusMsg.value = "Add only letters and numbers to the name."
        return
    }

    status.value = false
    statusMsg.value = ""

    const postRequest = { 
        "name": routineName.value,
        "date": "2007-12-03",
        "exercise": [] as Exercise[]
    }

    store.state.selectedExercises.forEach((key: string) => {
        postRequest.exercise.push(JSON.parse(key))
    });

    await routineService.createRoutine(postRequest).then( () => {
        store.commit('cleanList')
        router.push({ path: '/routine/create' })
    }).catch( () => {
        store.commit('cleanList')
        status.value = true
        statusMsg.value = "Server error in the routine creation."
    })
}

</script>

<template>
    <div class="template-div">
        <p v-if="status">{{statusMsg}}</p>
        <button class="back" @click="router.push('/routine/create')">GO BACK</button>
        <div class="name-div">
            <p>
                Insert the name of the routine: 
                <input v-model="routineName" class="input-name"/>
            </p>
            <button class="create" @click="createRoutine">CREATE ROUTINE</button>
        </div>
        <div class="exercises-div">
            <h1>Preview of exercises:</h1>
            <ExerciseIndex :element="JSON.parse(exercise)" v-for="exercise in store.state.selectedExercises" v-bind:key="exercise.id"></ExerciseIndex>
        </div>
    </div>
</template>

<style>
.template-div {
    margin: 20px;
}

.name-div {
    font-size: 20px;
    padding-top: 5vh;
    width: 85vw;
    height: 20%;
}

.input-name {
    width: 150px;
}

.exercises-div {
    width: 1/4;
    height: 70%;
    margin-top: 10vh;
}

.create {
    border-radius: 5px;
    background-color: var(--orange-color);
    color: black;
    border: 0px;
    height: 30px;
}

.back {
    border-radius: 5px;
    background-color: var(--red-color);
    color: black;
    border: 0px;
    height: 30px;
}

@media screen and (max-width: 300px){
    .exercises-div{
        width: 1/4;
        height: 70vh;
        margin-top: 20vh;
    }
}
</style>