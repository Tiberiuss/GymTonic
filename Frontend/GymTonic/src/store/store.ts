import { createStore } from 'vuex';
import type { Sets, payloadReps, payloadWeight, payloadDeleteSets, payloadCreateWorkout } from '@/types';

interface state {
    actualSets: { routine: {id: string}; date: Date; set: Array<Sets>;};
    selectedExercises: Set<String>
}

const store = createStore<state>({
    state () {
        return {
            selectedExercises: new Set(),
            actualSets: {
                routine: {
                    id: ""
                },
                date: new Date(),
                set: new Array<Sets>()
            }
        }
    },
    mutations: {
        addExercise (state: state, exercise: String) {
            state.selectedExercises.add(exercise)
        },
        deleteExercise (state: state, exercise: String) {
            state.selectedExercises.delete(exercise)
        },
        cleanList (state: state) {
            state.selectedExercises.clear()
        },
        inicializeWorkout(state: state, payload: payloadCreateWorkout){
            state.actualSets.routine.id = payload.routine
            state.actualSets.date = new Date(payload.date)
            state.actualSets.set = new Array<Sets>()
        },
        addSet(state: state, set: Sets){
            state.actualSets?.set.push(set)
        },
        changeReps(state: state, payload: payloadReps){
            // Don't delete 
        },
        changeWeight(state: state, payload: payloadWeight){
            // Don't delete
        },
        deleteWorkout(state: state, number: number){
            state.actualSets.set = state.actualSets.set
                .filter((s) => s.number !== number)
                .map((s,index) => {s.number=index+1; return s;})
        },
        cleanActualSets(state: state){
            state.actualSets = {
                routine: {
                    id: ""
                },
                date: new Date(),
                set: new Array<Sets>()
            }
        }     
    },
    getters: {
        isExerciseSelected: (state: state) => (exercise: String) => {
            return state.selectedExercises.has(exercise)
        },
        isWorkoutInitializedPropertly: (state: state) => () =>{
            return state.actualSets.routine.id != ""
        }
    }
})

export const vueStore = store;

export const mutations = store.mutations;