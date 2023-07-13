<script setup lang="ts">
    import { useRoute } from 'vue-router';
    import { useRouter } from 'vue-router';
    import { onBeforeMount } from 'vue';
    import { routineService } from '@/services/routine.service';
    import { ref } from 'vue';
    import ExerciseSets from '@/components/sets/ExerciseSets.vue';
    import { useStore } from 'vuex';
    import { setService } from '@/services/set.service';

    const router = useRouter()
    const route = useRoute()
    const store = useStore()

    const routine = ref()
    const state = ref(false)
    const stateMsg = ref("")

    onBeforeMount(async() => {
        const res = await routineService.getById(String(route.params.idRoutine))

        if (res.error){
            state.value = true
            stateMsg.value = "Error loading the routine."
            return
        }else if (res.result){
            routine.value = res.result.data
        }

        if (routine.value == null){
            state.value = true
            stateMsg.value = "The routine doesn't exist."
        }
        
        store.commit('inicializeSet', {
            routineId: routine.value.id,
            exercises: routine.value.exerciseMongo
        })
    })

    async function routineSave() {
        const res = await setService.createSet(store.state.actualSets)

        if (res.error){
            state.value = true
            stateMsg.value = "The sets aren't saved."
        }else if (res.result){
            store.commit("cleanActualSets")
            router.push('/routine/create')
        }
    }

    function errorExercise() {
        state.value = true;
        stateMsg.value = "Error while reading exercises."
    }

</script>

<template>
    <button class="back" @click="router.push('/routine/create')">GO BACK</button>
    <button class="save" @click="routineSave">SAVE</button>
    <div v-if="state">{{ stateMsg }}</div>
    <div v-else class="exercise-information">
        <h1>
            {{ routine.name }}
        </h1>
        <ExerciseSets @errorExercise="errorExercise" :element="element" v-for="element in routine.exerciseMongo" v-bind:key="element.id"></ExerciseSets>
    </div>
</template>

<style>
.back {
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
    margin: 30px;
}

.exercise-information div {
    margin-top: 50px;
}
</style>
