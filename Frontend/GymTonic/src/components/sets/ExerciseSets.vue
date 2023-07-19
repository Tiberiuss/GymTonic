<script setup lang="ts">
    import { onBeforeMount, ref } from 'vue';
    import { useStore } from 'vuex';
    import router from '@/router';
    import IconExerciseDescription from '@/components/icons/IconExerciseDescription.vue'
    import type { Sets } from '@/types';

    const props = defineProps(['element'])
    const store = useStore()
    const emit = defineEmits(['errorExercise'])

    const table = ref()
    let setId = 1
    let indexStore = 0

    function newSet(){
        store.commit('addSet', {
                number: setId,
                reps: 0,
                weight: 0,
                exerciseMongo: {
                    id: props.element.id
                }
        })

        setId += 1
    }
</script>

<template>
            <h2>
                {{element.name}}
            </h2>
            <button class="more-info-button" @click="router.push('/index/' + element.id)">
                <IconExerciseDescription></IconExerciseDescription>
            </button>
            <table class="table-sets" ref="table">
                <tr class="table-header">
                    <td>Reps</td>
                    <td>Weight</td>
                    <td></td>
                </tr>
                <tr v-for="set in store.state.actualSets.set?.filter((set: Sets) => set.exerciseMongo?.id === props.element?.id)" v-bind:key="set">
                    <td>
                        <input type="number" v-model="set.reps" min="0" @input="store.commit('changeReps', {number: set.number, reps: set.reps})"/>    
                    </td>
                    <td>
                        <input type="number" v-model="set.weight" min="0" @input="store.commit('changeWeight', {number: set.number, weight: set.weight})"/>
                    </td>
                    <td>
                        <button class="delete" @click="store.commit('deleteWorkout', set.number)">DELETE</button>
                    </td>
                </tr>
            </table>
            <button class="newset" @click="newSet">
                NEW SET
            </button>
</template>

<style>
.more-info-button {
    background-color: var(--white-transparent-color);
    border: 0px;
    border-radius: 10px;
    margin-bottom: 5px;
}

.table-sets {
    background-color: var(--orange-color);
    color: black;
    border-radius: 30px;
}

.table-sets * {
    height: 30px;
}

.table-sets td {
    width: 300px;
    text-align: center;
}

.newset {
    background-color: var(--green-color);
    margin-top: 20px;
    border: 0px;
    border-radius: 30px;
}

.table-sets input {
    background-color: transparent;
    border-left: 0px;
    border-top: 0px;
    border-right: 0px;
    border-color: black;
    width: 60px;
    text-align: center;
    outline: 0;
}

.delete {
    background-color: var(--red-delete-color);
    border: 0px;
    border-radius: 30px;
}
</style>