<script setup lang="ts">
import ExerciseComponent from '@/components/ExerciseComponent.vue';
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
        "date": "2023-07-18",
        "exercise": [] as Exercise[]
    }

    store.state.selectedExercises.forEach((key: string) => {
        postRequest.exercise.push(JSON.parse(key))
    });

    await routineService.createRoutine(postRequest).then( () => {
        store.commit('cleanList')
        router.push({ path: '/routine' })
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
        <button class="create" @click="createRoutine">CREATE ROUTINE</button>
        <div class="name-div">
            <p>
                Insert the name of the routine: 
                <input v-model="routineName" class="input-name"/>
            </p>
        </div>
        <h1>Preview of exercises:</h1>
        <div class="exercises-div">
            <ExerciseComponent class="exercise-index" :element="JSON.parse(exercise)" v-for="exercise in store.state.selectedExercises" :key="exercise.id"></ExerciseComponent>
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
    border-color: white;
    background-color: transparent;
    outline: none;
    color: white;
    transition: all 500ms;
}

.input-name:hover {
    background-color: white;
    color: black
}

h1 {
    margin-top: 10vh;
}

.create {
    border-radius: 5px;
    background-color: var(--orange-color);
    color: black;
    border: 0px;
    height: 30px;
}

.exercises-div {
    height: 20%;
    width: 80%;
    margin-left: 10%;
}

.exercise-index {
    margin-bottom: 5%;
}

@media screen and (max-width: 300px){
    .exercises-div{
        width: 1/4;
        height: 70vh;
    }
}
</style>