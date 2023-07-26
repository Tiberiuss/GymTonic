<script setup lang="ts">
    import IconExerciseDescription from './icons/IconExerciseDescription.vue';
    import IconExerciseProgress from './icons/IconExerciseProgress.vue';
    import IconAddExercise from './icons/IconAddExercise.vue';
    import IconRemoveExercise from '@/components/icons/IconRemoveExercise.vue'
    import { onMounted, ref, computed } from 'vue';
    import { useStore } from 'vuex';

    const props = defineProps(['element', 'componentType'])
    const emit = defineEmits(['addExercise', 'deleteExercise'])

    const addButton = ref()
    const exerciseDesc = ref()
    const video = ref();
    let buttonClicked = ref(false);

    const store = useStore();

    const selectedExercises = computed(() => store.state.selectedExercises)

    onMounted(() => {
        if (selectedExercises.value.has(JSON.stringify(props.element))) {
            try{
                buttonClicked.value = true
                exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, var(--orange-dark-color), var(--orange-dark-color))";
                addButton.value.style.backgroundColor = "var(--red-color)"
            }catch{
                exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, var(--orange-dark-color), var(--orange-color))";
            }
        }
    })

    function clkButton() {
        buttonClicked.value = !buttonClicked.value;
    
        if (buttonClicked.value) {
            emit('addExercise', props.element)
            exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, var(--orange-dark-color), var(--orange-dark-color))";
            addButton.value.style.backgroundColor = "var(--red-color)"
        }else{
            emit('deleteExercise', props.element)
            exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, var(--orange-dark-color), var(--orange-color))";
            addButton.value.style.backgroundColor = "var(--green-color)"
        }
    }

</script>
<template>
    <section class="exercise" @mouseenter="video.play()" @mouseleave="video.play().then(() => {video.currentTime = 0; video.pause();})">
        <div class="img">
            <button ref="addButton" class="add-button" @click="clkButton" v-if="componentType">
                <IconAddExercise v-if="!buttonClicked"/>
                <IconRemoveExercise v-else/>
            </button>
          <video ref="video" class="exercise__video" :src=element.video[0] muted loop ></video>
        </div>
        <div ref="exerciseDesc" class="exercise-desc">
            <p class="exercise-title">
                {{ element.name }}
            </p>
            <div class="exercise-icons">
                <router-link :to="`/index/${element.id}`">
                    <IconExerciseDescription></IconExerciseDescription>
                </router-link>
                <router-link :to="`/chart/${element.id}`">
                    <IconExerciseProgress></IconExerciseProgress>
                </router-link>
            </div>
        </div>
    </section>
</template>
<style>
    .add-button {
        height: 50px;
        width: 50px;
        border-radius: 20px;
        border: 0px;
        position: absolute;
        z-index: 10;
        background-color: var(--green-color);
    }

    .exercise {
        position: relative;
        border-radius: 1rem;
        overflow:hidden;
    }

    .exercise__video {
      width: 100%;
      height: 100%;
      object-fit: cover;
        min-height: 10rem;
    }

    .img video {
        border-top-left-radius: 20px;
    }

    .exercise-desc {
        position: absolute;
        width: 100%;
        bottom: 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-size: 200% 100%;
        background-position: 100% 0;
        background-image: linear-gradient(to right, var(--secondary), var(--primary));
        transition: background-position 1s;
    }

    .exercise-desc a {
        color: var(--background);
        padding: 0.5rem;
    }

    .exercise-desc a:hover {
        color: var(--text);
    }

    .exercise:hover > .exercise-desc{
        background-position: 0 0;
    }

    .exercise-icons {
        display: flex;
    }
    .exercise-title {
        display: inline;
        font-size: 20px;
        margin: 0;
        padding-left: 0.4rem;
    }
</style>