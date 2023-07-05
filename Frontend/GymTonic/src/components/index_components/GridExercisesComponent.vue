<script setup lang="ts">
    import SearchComponent from './SearchComponent.vue';
    import ExerciseIndex from './ExerciseIndex.vue';
    import {ref, onMounted} from 'vue';
    import {exerciseService} from "@/services/exercise.service";
    import type {Exercise} from "@/types";

    const exercises = ref<Exercise[]>()
    const selectedExercises = ref<Exercise[]>([])
    const status = ref(false)
    
    defineProps({
        componentType: String
    })

    onMounted(async () => {
      const res = await exerciseService.getAll();
      if (res.error) {
        console.log(res.error);
        exercises.value=[];
        status.value = true;
      } else if(res.result) {
        exercises.value = res.result.data;
      }
    })

    async function sendQuery(value: any) {
        status.value = false
        const res = await exerciseService.getByName(value);
        if(res.error) {
            console.log(res.error);
            exercises.value=[];
            status.value = true;
        }else if (res.result){
          console.log("COSITAS")
          exercises.value = res.result.data;
        }
    }

    async function addExercise(exercise: Exercise) {
        selectedExercises.value.push(exercise)
    }

    async function deleteExercise(exercise: Exercise) {
        const index = selectedExercises.value.indexOf(exercise);
        if (index > -1)
          selectedExercises.value.splice(index,1);
    }
</script>

<template>
  <div class="exercises">
    <div>
        <SearchComponent  @set-query="sendQuery"/>
        <button v-if="componentType" @click="$router.push({name: 'addnameroutine', params: {exerciseList: selectedExercises}})" class="create-routine">NEXT</button>
    </div>
    <div v-if="!status" class="section-grid">
        <ExerciseIndex @addExercise="addExercise" @deleteExercise="deleteExercise" :component-type=componentType :element=element v-for="element in exercises">
        </ExerciseIndex>
        <router-view></router-view>
    </div>
    <div v-else>
        An error ocurred.
    </div>
  </div>
</template>

<style>
.exercises {
  margin: 1em;

}
.section-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit,minmax(30em,1fr));
    grid-gap: 30px;
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