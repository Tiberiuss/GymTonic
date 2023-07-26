<script setup lang="ts">
    import { useRouter } from 'vue-router';

    const router = useRouter()
</script>

<template>
    <div class="hub">
        <button class="banner" @click="router.push('/index')">
            <img class="banner__img" src="@/assets/images/hub-exercise.jpg"/>
            <span class="banner__text">EXERCISES</span>
        </button>
        <button class="banner" @click="router.push('/routine')">
            <img class="banner__img" src="@/assets/images/hub-routine.jpg"/>
            <span class="banner__text">ROUTINES</span>
        </button>
    </div>
</template>

<style lang="scss" scoped>
.hub {
    padding: 5rem;
    gap: 4rem;
    display: grid;
    grid-template-columns: 50% 50%;
    overflow: hidden;
    height: 100vh;
    justify-content: space-evenly;
    align-content: space-evenly;
}

.banner{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    border-radius: 40px;
    border: 0px;
    background-color: transparent;
    transition: transform 500ms ease-in-out;
    padding: 0;

    &__img {
        width: 100%;
        height: 100%;
        border-radius: 40px;
        object-fit: cover;
    }

    &:hover {
        transform: scale(1.1);
    }

    &__text {
        position: absolute;
        padding: 0.5em 0;
        font-size: 2.7em;
        letter-spacing: 0.1em;
        color: white;
        width: 100%;
        background: #000c;
    }
}


@media screen and (max-width: 992px) {
    .hub {
        grid-template-columns: none;
        grid-template-rows: 50% 50%;
    }
}

</style>