<template>
    <section class="exercise">
        <div class="img">
            <img />
        </div>
        <div class="exercise-extra">
            <slot name="exercise-title"></slot>
        </div>
    </section>
</template>
<style>
    .exercise {
        background-color: #CC6F1A;
        border-top-left-radius: 20px;
        border-bottom-right-radius: 20px;
        margin-top: 10%;
        height: 50%;
        width: 80%;
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
        margin-left: 20vw;
        height: 100%;
    }

</style>