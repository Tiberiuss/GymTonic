<script setup lang="ts">
    import SearchIcon from '../icons/IconSearch.vue';
</script>
<template>
    <input class="searchbar" type="text" placeholder="Search..."/>
    <SearchIcon></SearchIcon>
</template>

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