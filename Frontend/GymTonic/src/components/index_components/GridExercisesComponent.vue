<script setup lang="ts">
    import SearchComponent from './SearchComponent.vue';
    import ExerciseIndex from './ExerciseIndex.vue';
    import {ref, onMounted} from 'vue';
    import {exerciseService} from "@/services/exercise.service";
    import type {Exercise} from "@/types";
    import { useStore } from 'vuex';
    import { useRouter } from 'vue-router';

    const store = useStore();
    const router = useRouter();
    const exercises = ref<Exercise[]>()
    const status = ref(false)
    const statusMsg = ref("")
    
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
          exercises.value = res.result.data;
        }
    }

    async function nextCreate() {
        if (store.state.selectedExercises.size != 0){
            router.push({name: 'addnameroutine'})
        }else{
            statusMsg.value = "You must select one or more exercises."
        }
    }

</script>

<template>
<div class="exercises">
    <p v-if="componentType">{{ statusMsg }}</p>
    <div>
        <SearchComponent  @set-query="sendQuery"/>
        <button v-if="componentType" @click="nextCreate" class="create-routine">NEXT</button>
    </div>
    <div v-if="!status" class="section-grid">
        <ExerciseIndex @addExercise="store.commit('addExercise', JSON.stringify(element))" @deleteExercise="store.commit('deleteExercise', JSON.stringify(element))" :component-type=componentType :element=element v-for="element in exercises" v-bind:key="element.id">
        </ExerciseIndex>
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