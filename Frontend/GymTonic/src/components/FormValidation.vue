<script setup lang="ts">
import IconError from "@/components/icons/IconError.vue";

defineProps({
  error:String
})

</script>

<template>
  <div v-if="error" class="validation">
    <IconError/>
    <span> {{error}}</span>
  </div>
</template>

<style lang="scss" scoped>
  .validation {
    vertical-align: middle;
    font-size: 0.8rem;
    text-align: left;

    svg {
      vertical-align: text-bottom;
    }
  }
</style>
