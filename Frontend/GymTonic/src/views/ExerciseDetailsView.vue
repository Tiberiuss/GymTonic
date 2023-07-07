<script setup lang="ts">
    import { useRouter, useRoute } from 'vue-router';
    import axios from 'axios';
    import { ref } from 'vue';
    import type { Exercise } from '@/types';

    const router = useRouter();
    const route = useRoute();
    const statusMsg = ref("");
    const status = ref(false);
    const video = ref();
    const exercise = ref(null);

    await axios.get('http://localhost:8080/api/v1/exercise/id=' + route.params.itemId)
    .then((response) => {
        exercise.value = response.data.data
    })
    .catch( () => {
        statusMsg.value = "Server error."
        status.value = true
    })

    console.log(exercise.value)

    if (exercise.value == null){
        statusMsg.value = "The exercise ID doesn't exist."
        status.value = true
    }

</script>

<template>
    <p v-if="status">{{statusMsg}}</p>
    <div v-else class="detail-grid">
        <div>
            {{ exercise.value.id }}
        </div>
    </div>
</template>

<style>
.detail-grid {
    display: grid;
    grid-template-columns: 30% 70%;
    margin: 10px;
}

.exercise__video {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
</style>