<script setup lang="ts">
    import IconExerciseDescription from '../icons/IconExerciseDescription.vue';
    import IconExerciseProgress from '../icons/IconExerciseProgress.vue';
    import IconAddExercise from '../icons/IconAddExercise.vue';
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
                exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, rgb(154, 77, 4), rgb(154, 77, 4))";
                addButton.value.style.backgroundColor = "var(--red-color)"
            }catch{
                exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, rgb(154, 77, 4), var(--orange-color))";
            }
        }
    })

    function clkButton() {
        buttonClicked.value = !buttonClicked.value;
    
        if (buttonClicked.value) {
            emit('addExercise', props.element)
            exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, rgb(154, 77, 4), rgb(154, 77, 4))";
            addButton.value.style.backgroundColor = "var(--red-color)"
        }else{
            emit('deleteExercise', props.element)
            exerciseDesc.value.style.backgroundImage = "linear-gradient(to right, rgb(154, 77, 4), var(--orange-color))";
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
            <div class="exercise-extra">
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
        position: absolute;
        z-index: 10;
        background-color: var(--green-color);
    }

    .exercise {
        display: grid;
        grid-template-columns: 50% 50%;
        padding: 0 1em;
        transition: transform 0.5s;
        position: relative;
    }

    .exercise__video {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .img video {
        border-top-left-radius: 20px;
    }

    .exercise-desc {
        background-image: linear-gradient(to right, rgb(154, 77, 4), var(--orange-color));
        border-bottom-right-radius: 20px;
        display: grid;
        grid-template-rows: 60% 40%;
    }

    .exercise:hover {
        transform: scale(1.02);
    }

    .exercise:hover > .exercise-desc > .exercise-extra{
        background-color: rgb(154, 77, 4);
        transition: background-color 500ms linear;
    }

    .exercise:hover > .exercise-desc > .exercise-muscles{
        background-color: rgb(154, 77, 4);
        transition: background-color 500ms linear;
        border-bottom-right-radius: 20px;
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