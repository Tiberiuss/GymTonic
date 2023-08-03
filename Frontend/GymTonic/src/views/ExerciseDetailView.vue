<script setup lang="ts">
    import { useRoute } from 'vue-router'
    import {onBeforeMount, ref } from 'vue'
    import { exerciseService } from '@/services/exercise.service'
    import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'

    const route = useRoute()
    const statusMsg = ref('')
    const status = ref(true)
    const exercise = ref()


    onBeforeMount(async() => {
        const res = await exerciseService.getById(String(route.params.itemId))
        
        if (res.error) {
            status.value = true
            statusMsg.value = 'Error loading the exercise.'
        } else if (res.result) {
            exercise.value = res.result.data
            status.value=false
        }

        if (exercise.value == null) {
            statusMsg.value = "The exercise ID doesn't exist."
            status.value = true
        }
    })
</script>

<template>
    <p v-if="status">{{ statusMsg }}</p>
    <div v-else class="detail">
        <div>
          <Carousel >
            <Slide v-for="video in exercise.video" :key="video">
              <video :src="video" class="exercise_video" muted loop autoplay></video>
            </Slide>

            <template #addons>
              <Navigation />
              <Pagination />
            </template>
          </Carousel>
        </div>
        <div class="exercise__info">
          <h1>
              {{ exercise.name }}
          </h1>
          <hr>
          <div>
            <span>Muscles</span>
            <ul>
                <li v-for="muscleData in exercise.muscle" :key="muscleData">
                    {{ muscleData }}
                </li>
            </ul>
          </div>
          <hr>
          <span>Material: <span>{{ exercise.material }}</span></span>
          <hr>
        </div>
        <div class="exercise__steps">
          <span>Steps:</span>
          <ol>
            <li v-for="step in exercise.steps" :key="step">
              {{ step }}
            </li>
          </ol>
        </div>
    </div>
</template>

<style>

.exercise__info {
  margin-right: 1em;
}

.exercise__steps {
  text-align: justify;
  grid-column: 1/-1;
  margin-right: 1em;
}

.detail {
  display: grid;
  grid-template-columns: 50% 50%;
  grid-gap: 1em;
  background-color: var(--secondary); /*#2c3e50;*/
  border-radius: 1em;
  padding: 1em;
    margin: 2em;
    color: white;
}
.exercise_video {
    margin-top: 20px;
    width: 100%;
    object-fit: cover;
}
@media screen and (max-width: 800px){
  .detail{
    display: flex;
    flex-direction: column;
  }
}
</style>
