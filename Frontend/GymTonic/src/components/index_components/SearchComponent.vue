<script setup lang="ts">
import { ref } from 'vue';
import IconSearch from "@/components/icons/IconSearch.vue";

defineEmits(['set-query']);

const query = ref("")

</script>
<template>
    <input class="searchbar" type="text" v-model="query" placeholder="Search..." @input="$emit('set-query', query)"/>
    <button class="button-search" @click="$emit('set-query', query)">
      <IconSearch/>
    </button>
</template>

<style>
.searchbar {
    border-radius: 2px;
    border-color: transparent;
    background-color: var(--grey-color);
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