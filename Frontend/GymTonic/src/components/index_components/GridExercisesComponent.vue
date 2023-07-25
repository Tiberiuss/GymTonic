<script setup lang="ts">
    import SearchComponent from './SearchComponent.vue';
    import ExerciseIndex from './ExerciseIndex.vue';
    import {ref, onMounted, onUnmounted} from 'vue';
    import {exerciseService} from "@/services/exercise.service";
    import type {Exercise} from "@/types";
    import { useStore } from 'vuex';
    import { useRouter } from 'vue-router';

    const store = useStore();
    const router = useRouter();
    const exercises = ref<Exercise[]>([]);
    const status = ref(false);
    const statusMsg = ref("");
    const grid = ref();

    const searchValue = ref("");
    const loader = ref();
    const offset = ref(0);
    const pageSize = 10;

    const observer = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          addMore();
        }
      })
    },{threshold:0.2});
    
    defineProps({
        componentType: String
    })

    function watchScroll(){
      if (loader.value) {
        observer.observe(loader.value);
      }
    }

    function unwatchScroll(){
      if (loader.value) {
        observer.unobserve(loader.value);
      }
    }

    onMounted(async () => {
      const res = await exerciseService.getByNamePaginated(searchValue.value,offset.value,pageSize);
      if (res.error) {
        console.log(res.error);
        exercises.value=[];
        status.value = true;
      } else if(res.result) {
        offset.value+=pageSize;
        exercises.value = res.result.data;
        watchScroll();
      }
    })

    onUnmounted(() => {
      unwatchScroll();
    })

    async function sendQuery(value: any) {
        unwatchScroll();
        grid.value.scrollTo(0,0);
        status.value = false
        offset.value=0;
        searchValue.value = value;
        const res = await exerciseService.getByNamePaginated(value,offset.value,pageSize);
        if(res.error) {
            console.log(res.error);
            exercises.value=[];
            status.value = true;
        }else if (res.result){
          offset.value+=pageSize;
          watchScroll();
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
  async function addMore() {
    const res = await exerciseService.getByNamePaginated(searchValue.value,offset.value,pageSize);
    if (res.error) {
      unwatchScroll();
      loader.value.style.visibility = "visible";
    } else if(res.result) {
      offset.value+=pageSize;
      exercises.value?.push(...res.result.data);
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
    <div v-if="!status" class="section-grid" ref="grid">
        <ExerciseIndex @addExercise="store.commit('addExercise', JSON.stringify(element))" @deleteExercise="store.commit('deleteExercise', JSON.stringify(element))" :component-type=componentType :element=element v-for="element in exercises" v-bind:key="element.id">
        </ExerciseIndex>
      <div class="section-grid__load">
        <span ref="loader">NO MORE RESULTS...</span>
      </div>
    </div>
    <div v-else>
        An error ocurred.
    </div>
</div>
</template>

<style>

.exercises {
  display: flex;
  flex-direction: column;
  padding: 2em;
}

.section-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit,minmax(30em,1fr));
    grid-gap: 30px;
    margin-top: 1em;
    overflow: hidden;
    flex: 1;
}

.section-grid__load {
  text-align: center;
}

.section-grid__load span{
  visibility: hidden;
}

.create-routine {
    float: right;
    border-radius: 5px;
    background-color: var(--orange-color);
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
    background-color: var(--orange-color);
    color: black;
    border: 0px;
    margin-top: 10px;
    height: 30px;
    width: 75px;
}
}
</style>