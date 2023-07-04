<script setup lang="ts">
    import SearchComponent from './SearchComponent.vue';
    import ExerciseIndex from './ExerciseIndex.vue';
    import axios from 'axios';
    import { ref, onMounted } from 'vue';

    const exercises = ref([])
    const selectedExercises = ref([])
    const status = ref(false)
    
    const props = defineProps({
        componentType: String
    })

    onMounted(() => {
        axios.get('http://localhost:8080/api/v1/exercise')
        .then(response => (exercises.value = response.data))
        .catch(error => {
            console.log(error)
            status.value = true
        })
    })

    async function sendQuery(value: any) {
        status.value = false
        axios.get('http://localhost:8080/api/v1/exercise/name=' + value)
        .then(response => (exercises.value = response.data))
        .catch(error => {
            console.log(error)
            status.value = true
        })
    }

    async function addExercise(exercise: any) {
        selectedExercises.value.push(exercise)
    }

    async function deleteExercise(exercise: any) {
        selectedExercises.value.pop(exercise)
    }
</script>

<template>
<div>
    <SearchComponent  @set-query="sendQuery"/>
    <button v-if="componentType" @click="this.$router.push({name: 'addnameroutine', params: {exerciseList: selectedExercises}})" class="create-routine">NEXT</button>
</div>
<div v-if="!status" class="section-grid">
    <ExerciseIndex @addExercise="addExercise" @deleteExercise="deleteExercise" :component-type=componentType :element=element v-for="element in exercises">
    </ExerciseIndex>
    <router-view></router-view>
</div>
<div v-else>
    An error ocurred.
</div>
</template>

<style>
.section-grid {
    display: grid;
    grid-template-columns: 50% 50%;
    grid-gap: 30px;
    width: 90vw;
    height: 80vh;
}

.create-routine {
    float: right;
    border-radius: 5px;
    background-color: #CC6F1A;
    color: black;
    border: 0px;
    margin-right: 50px;
    height: 30px;
    width: 75px;
}

@media screen and (max-width: 700px) {
    .section-grid {
        display: grid;
        grid-template-columns: 100%;
    }
}

@media screen and (max-width: 400px) {
    .create-routine {
    float: left;
    border-radius: 5px;
    background-color: #CC6F1A;
    color: black;
    border: 0px;
    margin-top: 10px;
    height: 30px;
    width: 75px;
}
}
</style>