<script setup lang="ts">
    import ExerciseIndex from './ExerciseIndex.vue';
    import axios from 'axios';
</script>
<template>
<div v-if="!status" class="section-grid">
    <ExerciseIndex :muscles="element['muscle']" v-for="element in exercises">
        <template #exercise-title>{{ element['name'] }}</template>
    </ExerciseIndex>
</div>
<div v-else>
    An error ocurred.
</div>
</template>

<script lang="ts">
export default {
    data() {
        return {
            exercises: [],
            status: false
        }
    },
    async mounted() {
        axios.get('http://localhost:8080/api/v1/exercise')
        .then(response => (this.exercises = response.data))
        .catch(error => {
            console.log(error)
            this.status = true
        })
    }
}
</script>

<style>
.section-grid {
    display: grid;
    grid-template-columns: 50% 50%;
    grid-gap: 2vw;
    width: 90vw;
    height: 80vh;
}

@media screen and (max-width: 700px) {
    .section-grid {
        display: grid;
        grid-template-columns: 100%;
    }
}
</style>