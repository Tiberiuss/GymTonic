<script setup lang="ts">
    import { useRouter } from 'vue-router';

    const router = useRouter()
</script>

<template>
    <div class="hub">
        <button class="index-hub" @click="router.push('/index')">
            <img src="@/assets/images/exercises-hub.jpg"/>
        </button>
        <button class="routine-hub" @click="router.push('/routine')">
            <img src="@/assets/images/routine-hub.jpg"/>
        </button>
        <button class="progress-hub" @click="router.push('/chart')">
            <img src="@/assets/images/progress-hub.jpg"/>
        </button>
    </div>
</template>

<style>
.hub {
    display: grid;
    grid-template-columns: 45% 45%;
    grid-template-rows: 35% 52%;
    grid-gap: 5%;
    margin: 5%;
}

.index-hub {
    width: 100%;
    height: 100%;
    border-radius: 40px;
    border: 0px;
    background-color: transparent;
}

.routine-hub {
    width: 100%;
    height: 100%;
    border-radius: 40px;
    border: 0px;
    background-color: transparent;
}

.progress-hub {
    width: 100%;
    height: 100%;
    border-radius: 40px;
    border: 0px;
    background-color: transparent;
}

.progress-hub {
    grid-column: 1 / -1;
}

.hub button img {
    width: 100%;
    height: 100%;
    border-radius: 40px;
    transition: transform 500ms;
}

button img:hover {
    transform: scale(1.02);
}

@media screen and (max-width: 900px) {
    .hub {
        grid-template-columns: 100%;
        grid-template-rows: 27% 27% 27%;
    }
}

</style>