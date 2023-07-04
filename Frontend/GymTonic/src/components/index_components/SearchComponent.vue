<script setup lang="ts">
import SearchIcon from '../icons/IconSearch.vue';
import { ref } from 'vue';

const query = ref("")
</script>
<template>
    <input class="searchbar" type="text" v-model="query" placeholder="Search..." />
    <button class="button-search" @click="$emit('set-query', query)">
        <SearchIcon></SearchIcon>
    </button>
</template>

<style>
.searchbar {
    border-radius: 2px;
    border-color: transparent;
    background-color: #362C2C;
    width: 200px;
    height: 30px;
    margin-right: -25px;
    color: white;
}

.button-search {
    background-color: transparent;
    border: 0px;
    height: 25px;
    width: 25px;
}
</style>