<script setup lang="ts">
    import IconExerciseDescription from '../icons/IconExerciseDescription.vue';
    import IconExerciseProgress from '../icons/IconExerciseProgress.vue';
    import IconAddExercise from '../icons/IconAddExercise.vue';
    import { onMounted, ref, computed } from 'vue';
    import { useStore } from 'vuex';

    const props = defineProps(['element', 'componentType'])
    const emit = defineEmits(['addExercise', 'deleteExercise'])

    const exerciseDesc = ref()
    const video = ref();
    let buttonClicked = false;

    const store = useStore();

    const selectedExercises = computed(() => store.state.selectedExercises)

    onMounted(() => {
        if (selectedExercises.value.has(JSON.stringify(props.element))) {
            buttonClicked = true
            exerciseDesc.value.style.backgroundColor = "#a15814";
        }
    })

    function clkButton() {
        buttonClicked = !buttonClicked;
    
        if (buttonClicked) {
            emit('addExercise', props.element)
            exerciseDesc.value.style.backgroundColor = "#a15814";
        }else{
            emit('deleteExercise', props.element)
            exerciseDesc.value.style.backgroundColor = "#CC6F1A";
        }
    }

</script>
<template>
    <section class="exercise" @mouseenter="video.play()" @mouseleave="video.currentTime=0;video.pause()">
        <div class="img">
            <button class="add-button" @click="clkButton" v-if="componentType">
                <IconAddExercise />
            </button>
          <video ref="video" class="exercise__video" :src=element.video[0] muted loop></video>
        </div>
        <div ref="exerciseDesc" class="exercise-desc">
            <div class="exercise-extra">
                <p class="exercise-title">
                    {{ element.name }}
                </p>
                <div class="exercise-icons">
                    <router-link :to="`/index/${element.id}`">
                        <IconExerciseDescription></IconExerciseDescription>
                    </router-link>
                    <a class="progress-icon">
                        <IconExerciseProgress></IconExerciseProgress>
                    </a>
                </div>
            </div>
            <div class="exercise-muscles">
                <p class="muscle-text" v-for="muscle in element['muscle']" v-bind:key="muscle.id">
                    {{ muscle }}
                </p>
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
        background-color: rgba(96, 91, 91, 0.291);
        position: absolute;
        z-index: 10;
    }

    .exercise {
        margin-top: 10px;
        min-height: 215px;
        max-height: 350px;
        display: grid;
        grid-template-columns: 50% 50%;
    }

    .exercise__video {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .img {
        background-color: rgb(76, 72, 72);
        border-top-left-radius: 20px;
    }

    .exercise-desc {
        
        background-color: #CC6F1A;
        border-bottom-right-radius: 20px;
        display: grid;
        grid-template-rows: 60% 40%;
    }

    .exercise-extra {
        display: grid;
        grid-template-columns: 85% 15%;
        grid-gap: 5px;
        padding-top: 5px;
        padding-left: 5px;
        padding-right: 5px;
    }

    .exercise-muscles {
        display: grid;
        grid-template-columns: 50% 50%;
        column-gap: 1vw;
        margin-right: 3vw;
        margin-left: 1vw;
    }

    .exercise-title {
        display: inline;
        font-size: 20px;
    }

    .muscle-text {
        background-color: black;
        color: white;
        border-radius: 20px;
        text-align: center;
        height: 20px;
        font-size: 9px;
    }

    @media screen and (max-width: 1000px) {
        .exercise{
            height: 200px;
            display: grid;
            grid-template-columns: 50% 50%;
        }

        .exercise-title {
            display: inline;
            font-size: 17px;
        }

        .exercise-extra {
            display: grid;
            grid-template-columns: 80% 20%;
            grid-gap: 10px;
            padding-top: 5px;
            padding-left: 5px;
        }
    }

    @media screen and (max-width: 300px) {
        .exercise-extra {
            display: grid;
            grid-template-columns: 70% 30%;
            grid-gap: 10px;
            padding-top: 5px;
            padding-left: 5px;
        }

        .exercise-title {
            font-size: 15px;
        }

        .muscle-text {
            background-color: black;
            color: white;
            border-radius: 20px;
            text-align: center;
            height: 20px;
            font-size: 9px;
        }
    }
</style>