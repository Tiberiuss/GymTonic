<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import {userService} from "@/services/user.service";
import router from "@/router";
</script>

<template>
    <button v-if="userService.isAuthenticated()" type="button" @click="userService.removeToken()" class="button session">Logout</button>
    <button v-else type="button" @click="router.push('/login')" class="session">Login</button>

    <RouterView />
</template>

<style lang="scss">
  .session {
    position: absolute;
    right: 0;
    background-color: var(--secondary);
    border: none;
    border-radius: 1em;
    padding: 0.5em;
    margin: 0.5em;
    z-index: 10;
  }

</style>