<script setup lang="ts">
import LandingComponent from "@/components/LandingComponent.vue";
import {ref} from "vue";
import {userService} from "@/services/user.service";
import {useRouter} from "vue-router";
import IconError from "@/components/icons/IconError.vue";
import FormValidation from "@/components/FormValidation.vue";

const router = useRouter();
const user = ref();
const password = ref();
const error = ref();

async function login(){
  console.log(user.value)
  const res = await userService.login(user.value,password.value);
  if (res.error)
    error.value = "Wrong credentials.";
  else return router.go(0)
}

</script>

<template>
  <LandingComponent>
    <form class="form" @submit.prevent="login">
      <h1 class="form__title">LOG IN</h1>

      <input class="form__input" v-model="user" type="text" id="user" placeholder="Username" required>
      <input class="form__input" v-model="password" type="password" id="passwd" placeholder="Password" required>
      <FormValidation :error="error"/>
      <div class="form__buttons">
        <router-link class="create" to="/register">CREATE ACCOUNT</router-link>
        <input class="form__login button" type="submit" value="LOG IN">
      </div>
    </form>
  </LandingComponent>
</template>

<style lang="scss" scoped>
  .form {
    display: flex;
    flex-direction: column;
    justify-content: stretch;
    gap: 1em;
    padding: 2em 10em;
    background-color: var(--form-user-color);
    border-radius: 2em;

    @media (max-width: 600px) {
      padding: 2em 2em;
    }

    &__title {
      margin-bottom: 1em;
    }

    &__input {
      padding: 1em;
      background-color: var(--input-user-color);
      border:none;
      border-radius: 0.5em;
      color: white;

      &::placeholder {
        color: white;
        font-weight: bold;
      }
    }

    &__buttons {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      gap: 2em;

      .create {
        align-self: center;
        color: white;
        text-decoration: none;
      }

      .form__login {
        border: none;
        padding: 0.5em 3em;
      }
    }
  }
</style>
