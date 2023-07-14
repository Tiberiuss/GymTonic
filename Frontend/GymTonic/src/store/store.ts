import { createStore } from 'vuex';
import type { Sets, Logs, payloadInitializeSets, payloadAddSets, payloadReps, payloadWeight, payloadDeleteSets } from '@/types';

interface state {
    actualSets: { routineId: string; userId: number; date: Date; logs: Array<Logs>;};
    selectedExercises: Set<String>
}

const store = createStore<state>({
    state () {
        return {
            selectedExercises: new Set(),
            actualSets: null
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
        inicializeSet(state: state, payload: payloadInitializeSets){
            state.actualSets = {
                "routineId": payload.routineId,
                "userId": 123,
                "date": new Date(),
                "logs": new Array<Logs>()
            }
            if (payload.exercises != null){
                payload.exercises.forEach((exercise) => {
                    state.actualSets.logs.push({
                        "exerciseId": exercise.id,
                        "sets": new Array<Sets>()
                    })
                })
            }
        },
        addSet(state: state, payload: payloadAddSets){
            state.actualSets.logs[payload.index].sets.push(payload.set)
        },
        changeReps(state: state, payload: payloadReps){
            const indexSet = state.actualSets.logs[payload.index].sets.findIndex((element) => element.id == payload.setId)
            state.actualSets.logs[payload.index].sets[indexSet].reps = payload.reps
        },
        changeWeight(state: state, payload: payloadWeight){
            const indexSet = state.actualSets.logs[payload.index].sets.findIndex((element) => element.id == payload.setId)
            state.actualSets.logs[payload.index].sets[indexSet].weight = payload.weight
        },
        deleteSet(state: state, payload: payloadDeleteSets){
            const indexSet = state.actualSets.logs[payload.index].sets.findIndex((element) => element.id == payload.setId)
            state.actualSets.logs[payload.index].sets.splice(indexSet,1)
        },
        cleanActualSets(state: state){
            state.actualSets.routineId = ""
            state.actualSets.userId = -1
            state.actualSets.logs = new Array<Logs>()
        }     
    },
    getters: {
        isExerciseSelected: (state: state) => (exercise: String) => {
            return state.selectedExercises.has(exercise)
        }
    }
})

export const vueStore = store;