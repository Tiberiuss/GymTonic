<script setup lang="ts">
    import { useRoute } from 'vue-router';
    import { useRouter } from 'vue-router';
    import { onBeforeMount } from 'vue';
    import { routineService } from '@/services/routine.service';
    import { exerciseService } from '@/services/exercise.service';
    import { ref } from 'vue';
    import ExerciseSets from '@/components/sets/ExerciseSets.vue';
    import { useStore } from 'vuex';
    import { workoutService } from '@/services/workout.service';
    import type{ Exercise } from '@/types';

    const router = useRouter()
    const route = useRoute()
    const store = useStore()

    const routine = ref()
    const state = ref(false)
    const exercises = ref<Array<Exercise>>(new Array<Exercise>())
    const stateMsg = ref("")

    onBeforeMount(async() => {
        const exercisesAux = ref<Array<Exercise>>(new Array<Exercise>())
        const res = await routineService.getById(String(route.params.idRoutine))

        if (!store.getters.isWorkoutInitializedPropertly()){
            store.commit('inicializeWorkout', {
                routine: String(route.params.idRoutine),
                date: String(route.params.date)
            })
        }

        if (res.error){
            state.value = true
            stateMsg.value = "Error loading the routine."
            return
        }else if (res.result){
            routine.value = res.result.data
            let i = 0
            while(i < routine.value.exercise.length){
                const res = await exerciseService.getById(routine.value.exercise[i].id)
                if (res.error){
                    state.value = true
                    stateMsg.value = "Error loading the routine exercises."
                    break
                }else if (res.result){
                    exercisesAux.value.push(res.result.data)
                }

                i += 1;
            }
        }

        if (routine.value == null){
            state.value = true
            stateMsg.value = "The routine doesn't exist."
        }
        exercises.value = exercisesAux.value
    })

    async function routineSave() {
        const res = await workoutService.createSet(store.state.actualSets)

        if (res.error){
            state.value = true
            stateMsg.value = "The sets aren't saved."
        }else if (res.result){
            store.commit("cleanActualSets")
            router.push('/routine')
        }
    }

    function errorExercise() {
        state.value = true;
        stateMsg.value = "Error while reading exercises."
    }

</script>

<template>
    <button class="back-sets" @click="router.push('/routine')">GO BACK</button>
    <button class="save" @click="routineSave">SAVE</button>
    <div v-if="state">{{ stateMsg }}</div>
    <div v-else class="exercise-information">
        <h1>
            {{ routine?.name }}
        </h1>
        <ExerciseSets @errorExercise="errorExercise" :element="element" v-for="element in exercises" v-bind:key="element.id"></ExerciseSets>
    </div>
</template>

<style>
.back-sets {
    border-radius: 5px;
    background-color: var(--red-color);
    color: black;
    border: 0px;
    height: 30px;
    margin: 20px;
}

.save {
    border-radius: 5px;
    background-color: var(--green-color);
    color: black;
    border: 0px;
    height: 30px;
    margin: 20px;
    float: right;
}

.exercise-information {
    margin-left: 20%;
    margin-right: 20%;
    margin-top: 5%;
}

.exercise-information div {
    margin-bottom: 50px;
}
</style>
