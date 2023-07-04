<script setup lang="ts">
import ExerciseIndex from '@/components/index_components/ExerciseIndex.vue';
</script>
<template>
    <h1>Name</h1>

    <div>
        <ExerciseIndex v-for="element in exerciseList"></ExerciseIndex>
    </div>
</template>