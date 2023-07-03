<script setup lang="ts">
    import SearchComponent from './SearchComponent.vue';
    import ExerciseIndex from './ExerciseIndex.vue';
    import axios from 'axios';
</script>
<template>
<SearchComponent  @set-query="sendQuery"/>
<div v-if="!status" class="section-grid">
    <ExerciseIndex :element="element" v-for="element in exercises">
    </ExerciseIndex>
    <router-view></router-view>
</div>
<div v-else>
    An error ocurred.
</div>
</template>

<script lang="ts">
export default {
    data() {
        return {
            exercises: [],
            status: false
        }
    },
    methods: {
        sendQuery(value: any){
            this.status = false
            axios.get('http://localhost:8080/api/v1/exercise/name=' + value)
            .then(response => (this.exercises = response.data))
            .catch(error => {
                console.log(error)
                this.status = true
            })
        }
    },
    async mounted() {
        axios.get('http://localhost:8080/api/v1/exercise')
        .then(response => (this.exercises = response.data))
        .catch(error => {
            console.log(error)
            this.status = true
        })
    }
}
</script>

<style>
.section-grid {
    display: grid;
    grid-template-columns: 50% 50%;
    grid-gap: 30px;
    width: 90vw;
    height: 80vh;
}

@media screen and (max-width: 700px) {
    .section-grid {
        display: grid;
        grid-template-columns: 100%;
    }
}
</style>