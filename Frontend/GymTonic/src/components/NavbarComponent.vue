<script setup lang="ts">
import {userService} from "@/services/user.service";
import {useRoute,useRouter} from "vue-router"

const router = useRouter();
const route = useRoute();

</script>

<template>
    <nav v-if="route.meta.header" class="nav">
        <router-link class="nav__link" to="/hub">GYM TONIC</router-link>

        <div class="nav__account">
            <button v-if="userService.isAuthenticated()" type="button" @click="userService.removeToken()" class="button">Logout</button>
            <button v-else type="button" @click="router.push('/login')" class="button">Login</button>
        </div>
    </nav>

</template>


<style lang="scss">
.button {
    margin: 0.5em 0;
}

.nav {
    display: flex;
    justify-content: space-between;
    margin: 0 2em;


    &:before {
        content: "";
        flex: 1;
    }
    &__link {
        text-decoration: none;
        color: var(--text);
        padding: 1rem;
        font-size: 1.3em;
        text-align: center;
        &:hover{
            color: var(--accent);
        }
    }

    &__account {
        display: flex;
        justify-content: end;
        flex: 1;

        .button {
            background-color: var(--secondary);
        }
    }
}

@media screen and (max-width: 300px) {
    .nav {
        margin: 0;

        &:before {
            content: none;
            flex: 1;
        }

        &__account {
            margin-right: 1em;
        }
    }
}

</style>