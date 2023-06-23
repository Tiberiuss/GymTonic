<script setup lang="ts">
    import ExerciseIndex from './ExerciseIndex.vue';

    const exercises = ["Biceps", "Triceps", "Back"] // Aux Exercises Array
</script>
<template>
<div class="section-grid">
    <ExerciseIndex v-for="element in exercises">
        <template #exercise-title>{{ element }}</template>
    </ExerciseIndex>
</div>
</template>

<style>
.section-grid {
    display: grid;
    grid-template-columns: 50% 50%;
    width: 90vw;
    height: 80vh;
}
</style>