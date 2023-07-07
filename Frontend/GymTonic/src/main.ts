import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import { createStore } from 'vuex';

interface state {
    selectedExercises: Set<String>
}

const store = createStore<state>({
    state () {
        return {
            selectedExercises: new Set()
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
        }
    },
    getters: {
        isExerciseSelected: (state: state) => (exercise: String) => {
            return state.selectedExercises.has(exercise)
        }
    }
})

const app = createApp(App)

app.use(router)
app.use(store)

app.mount('#app')
