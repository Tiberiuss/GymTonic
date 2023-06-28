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
        <div class="exercise-desc">
            <div class="exercise-extra">
                <p class="exercise-title">
                    <slot name="exercise-title"></slot>
                </p>
                <div class="exercise-icons">
                    <a class="description-icon">
                        <IconExerciseDescription></IconExerciseDescription>
                    </a>
                    <a class="progress-icon">
                        <IconExerciseProgress></IconExerciseProgress>
                    </a>
                </div>
            </div>
            <div class="exercise-muscles">
                <p class="muscle-text" v-for="muscle in muscles">
                    {{ muscle['muscle_name'] }}
                </p>
            </div>
        </div>
    </section>
</template>
<style>
    .exercise {
        margin-top: 1%;
        min-height: 200px;
        display: grid;
        grid-template-columns: 50% 50%;
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
        font-size: 12px;
    }

    @media screen and (max-width: 1000px) {
        .exercise {
            min-height: 200px;
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