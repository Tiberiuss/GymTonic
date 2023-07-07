<script setup lang="ts">
import GridExercisesComponent from '@/components/index_components/GridExercisesComponent.vue';
import { ref } from 'vue';

const create = ref("new")

</script>

<template>
    <GridExercisesComponent :component-type=create />
</template>