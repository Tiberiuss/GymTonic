<script setup lang="ts">
import SearchIcon from '../icons/IconSearch.vue';
import axios from 'axios';
</script>
<template>
    <input class="searchbar" type="text" placeholder="Search..." />
    <SearchIcon></SearchIcon>
</template>

<script lang="ts">
export default {
    data() {
        return {
            query: "",
            results: "",
            loading: false,
            errorMessage: "",
        }
    },
    methods: {
        
    },
    async mounted() {
        axios
      .get('http://localhost:8080/api/v1/exercise')
      .then(response => (this.results = response.data))

      console.log(this.results)
    }
}
</script>

<style>
.searchbar {
    border-radius: 2px;
    border-color: transparent;
    background-color: #362C2C;
    width: 200px;
    height: 30px;
    margin-right: -20px;
    color: white;
}
</style>