<script setup lang="ts">
    import { useRouter, useRoute } from 'vue-router'
    import axios from 'axios'
    import {onBeforeMount, onUpdated, ref } from 'vue'
    import { exerciseService } from '@/services/exercise.service'

    const router = useRouter()
    const route = useRoute()
    const statusMsg = ref('')
    const status = ref(false)
    const exercise = ref()

    onBeforeMount(async() => {
        const res = await exerciseService.getById(String(route.params.itemId))
        
        if (res.error) {
            status.value = true
            statusMsg.value = 'Error loading the exercise.'
        } else if (res.result) {
            exercise.value = res.result.data[0]
        }

        if (exercise.value == null) {
            statusMsg.value = "The exercise ID doesn't exist."
            status.value = true
        }
    })
</script>

<template>
    <button onclick="window.history.go(-1);" class="back">GO BACK</button>
    <p v-if="status">{{ statusMsg }}</p>
    <div v-else class="detail-grid">
        <div>
            <video :src="exercise.video[0]" class="exercise_video" controls muted loop></video>
            <video :src="exercise.video[1]" class="exercise_video" controls muted loop></video>
        </div>
        <div>
            <h1>
                {{ exercise.name }}
            </h1>
            Muscle
            <ul>
                <li v-for="muscleData in exercise.muscle" :key="muscleData">
                    {{ muscleData }}
                </li>
            </ul>
            Material: {{ exercise.material }}
        </div>
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

.detail-grid {
    display: grid;
    grid-template-columns: 40% 60%;
    margin: 30px;
    color: white;
}

.detail-grid div{
    margin-left: 20px;
}

.exercise_video {
    margin-top: 20px;
    width: 90%;
    object-fit: cover;
}

@media screen and (max-width: 800px){
    .detail-grid{
        display: inline;
        margin: 30px;
        color: white;
    }
}
</style>
