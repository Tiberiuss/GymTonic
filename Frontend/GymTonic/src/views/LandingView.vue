<script setup lang="ts">
import LandingComponent from "@/components/LandingComponent.vue";
</script>

<template>
  <LandingComponent class="main">
    <h1 class="main__title">GYM TONIC</h1>
    <h2 class="main__sub">The best way to exercise</h2>
    <router-link class="button main__button--margin" to="/hub">GET STARTED</router-link>
  </LandingComponent>
</template>

<style lang="scss" scoped>
 .main {
   overflow: hidden;
   position: relative;
   color: white;
   text-align: center;
   display: flex;
   justify-content: center;
   align-items: center;
   height: 100vh;
   flex-direction: column;

   &__title {
    font-size: 6em;
   }

   &__sub {
     margin: 0;
     padding: 0;
     font-size: 2em;
   }

   &__button--margin {
     margin-top: 10em;
   }
 }
</style>
