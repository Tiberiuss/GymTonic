<script setup lang="ts">
    import IconExerciseDescription from '../icons/IconExerciseDescription.vue';
    import IconExerciseProgress from '../icons/IconExerciseProgress.vue';
</script>

<script lang="ts">
    export default {
        props: ['muscles']
    }
</script>
<template>
    <section class="exercise">
        <div class="img">
            <img />
        </div>
        <div class="exercise-extra">
            <p class="exercise-title">
                <slot name="exercise-title"></slot>
            </p>
                <a class="description-icon">
                    <IconExerciseDescription></IconExerciseDescription>
                </a>
            <p></p>
            <a class="progress-icon">
                <IconExerciseProgress></IconExerciseProgress>
            </a>
        </div>
        <div class="exercise-muscles" v-for="muscle in muscles">
            <p>
                {{ muscle['muscle_name'] }}
            </p>
        </div>
    </section>
</template>
<style>
    .exercise {
        background-color: #CC6F1A;
        border-top-left-radius: 20px;
        border-bottom-right-radius: 20px;
        margin-top: 10%;
        height: 150px;
        width: 40vw;
        display: flex;
    }

    .img {
        width: 50%;
        background-color: rgb(76, 72, 72);
        border-top-left-radius: 20px;
        height: 100%;
    }

    .exercise-extra {
        position: absolute;
        display: grid;
        grid-template-columns: 80% 20%;
        margin-left: 22vw;
        height: 60px;
        width: 18vw;
        margin-top: 5px;
    }

    .exercise-muscles {
        display: flex;
        height: 60px;
        margin-right: 4vh;
        margin-left: 4vh;
        margin-top: 80px;
    }

    .exercise-title {
        display: inline;
        font-size: 2vw;
    }


    @media screen and (max-width: 1000px) {
        .exercise {
            width: 90vw;
            height: 150px;
        }

        .exercise-extra {
            margin-left: 47vw;
        }

        .exercise-title {
            display: inline;
            font-size: 30px;
        }

        .exercise-extra {
            position: absolute;
            display: grid;
            grid-template-columns: 80% 20%;
            margin-left: 47vw;
            height: 60px;
            width: 43vw;
            margin-top: 5px;
        }
    }

    @media screen and (max-width: 550px) {

    }

    @media screen and (max-width: 450px) {
    }

    @media screen and (max-width: 300px) {
        .exercise-extra {
            position: absolute;
            display: grid;
            grid-template-columns: 70% 30%;
            margin-left: 47vw;
            height: 60px;
            width: 43vw;
            margin-top: 5px;
        }

        .exercise-title {
            font-size: 20px;
        }
    }
</style>