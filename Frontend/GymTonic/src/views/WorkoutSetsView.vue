<script setup lang="ts">
    import { useRoute } from 'vue-router';
    import { useRouter } from 'vue-router';
    import { onBeforeMount } from 'vue';
    import { exerciseService } from '@/services/exercise.service';
    import { ref } from 'vue';
    import RoutineSetsComponent from '@/components/RoutineSetsComponent.vue';
    import { useStore } from 'vuex';
    import { workoutService } from '@/services/workout.service';
    import type{ Exercise } from '@/types';

    const router = useRouter()
    const route = useRoute()
    const store = useStore()

    const workout = ref()
    const state = ref(false)
    const exercises = ref<Array<Exercise>>(new Array<Exercise>())
    const stateMsg = ref("")

    onBeforeMount(async() => {
        const exercisesAux = ref<Array<Exercise>>(new Array<Exercise>())
        const res = await workoutService.getById(String(route.params.idWorkout))

        if (res.error) {
            state.value = true
            stateMsg.value = "Error loading the workout"
            return
        }else if (res.result){
            workout.value = res.result.data
            let i = 0
            while(i < workout.value.routine.exercise.length){
                const res = await exerciseService.getById(workout.value.routine.exercise[i].id)
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

        if (workout.value == null){
            state.value = true
            stateMsg.value = "The routine doesn't exist."
        }
        exercises.value = exercisesAux.value

        store.state.actualSets.routine.id = workout.value.routine.id
        store.state.actualSets.set = workout.value.set
    })

    async function update() {
        const res = await workoutService.updateWorkout(workout.value.id, store.state.actualSets)
    
        if (res.error) {
            state.value = true;
            stateMsg.value = "Error updating."
        }else if (res.result) {
            router.push('/routine')
        }
    }

</script>

<template>
    <button class="save"  @click="update">SAVE</button>
    <div v-if="state">{{ stateMsg }}</div>
    <div v-else class="exercise-information">
        <h1>
            {{ workout?.routine?.name }}
        </h1>
        <RoutineSetsComponent :element="element" v-for="element in exercises" :key="element.id"></RoutineSetsComponent>
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
