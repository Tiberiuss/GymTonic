<script setup lang="ts">
import LandingComponent from "@/components/LandingComponent.vue";
import {ref} from "vue";
import {userService} from "@/services/user.service";
import {useRouter} from "vue-router";

const router = useRouter()
const user = ref();
const password = ref();
const password_confirm = ref();

async function register() {
  if (password.value === password_confirm.value){
    const res = await userService.register(user.value,password.value)
    return router.push('/')
  }else {
    console.log("ERROR")
  }
}
</script>

<template>
  <LandingComponent>
    <form class="form" @submit.prevent="register">
      <h1 class="form__title">CREATE ACCOUNT</h1>

      <input class="form__input" v-model="user"  type="text" id="user" placeholder="Username" required>
      <input class="form__input" v-model="password" type="password" id="passwd" placeholder="Password" required>
      <input class="form__input" v-model="password_confirm" type="password" id="passwd_confirm" placeholder="Confirm Password" required>
      <router-link class="form__existing_acc" to="/login">Already have an account?</router-link>
      <input class="form__button button" type="submit" value="START NOW">
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
    background-color: rgba(217, 217, 217, 0.12);
    border-radius: 2em;

    @media (max-width: 600px) {
      padding: 2em 2em;
    }

    &__title {
      margin-bottom: 1em;
    }

    &__input {
      padding: 1em;
      background-color: rgba(217, 217, 217, 0.40);
      border:none;
      border-radius: 0.5em;
      color: white;

      &::placeholder {
        color: white;
        font-weight: bold;
      }
    }

    &__existing_acc {
      color: rgba(217, 217, 217, 1);
      text-decoration: none;
      align-self: end;
    }

    &__button {
      border-radius: 0.5em;
      border:none;
    }

  }

</style>