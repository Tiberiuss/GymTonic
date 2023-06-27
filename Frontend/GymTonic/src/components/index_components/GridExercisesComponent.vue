<script setup lang="ts">
    import ExerciseIndex from './ExerciseIndex.vue';
</script>
<template>
<div class="section-grid">
    <ExerciseIndex :muscles="element['muscle']" v-for="element in exercises">
        <template #exercise-title>{{ element['name'] }}</template>
    </ExerciseIndex>
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
        const response = await fetch(
            'http://localhost:8080/api/v1/exercise',
            {
                method: 'GET',
                headers: { "Content-type": "application/json; charset=UTF-8" },
            }
        );

        if (response["status"] != 200 && response["status"] != 201) {
          this.status = true;
          return;
        }

        this.exercises = await response.json()
    }
}
</script>

<style>
.section-grid {
    display: grid;
    grid-template-columns: 50% 50%;
    width: 90vw;
    height: 80vh;
}

@media screen and (max-width: 800px) {
    .section-grid {
        display: grid;
        grid-template-columns: 100%;
    }
}
</style>