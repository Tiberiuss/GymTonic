<script setup lang="ts">
    import ExerciseIndex from './ExerciseIndex.vue';

    const exercises = ['{ "name": "Biceps", "muscles": ["Biceps", "Arms"]}'] // Aux Exercises Array

</script>
<template>
<div class="section-grid">
    <ExerciseIndex :muscles="JSON.parse(element).muscles" v-for="element in exercises">
        <template #exercise-title>{{ JSON.parse(element).name }}</template>
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

@media screen and (max-width: 800px) {
    .section-grid {
        display: grid;
        grid-template-columns: 100%;
    }
}
</style>