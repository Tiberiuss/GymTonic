<script setup lang="ts">
    import { onMounted, ref, watch } from 'vue';
    import { useRouter } from 'vue-router';
    import { routineService } from '@/services/routine.service';
    import DateComponent from '@/components/date/DateComponent.vue';
    import { useStore } from 'vuex';

    const router = useRouter()
    const store = useStore()

    const routines = ref()
    const status = ref<boolean>(false)
    const statusMsg = ref<string>("")
    const selectedOptions = ref("")

    watch(selectedOptions, () => {
        console.log(selectedOptions)
        store.commit('inicializeSet', {
            routineId: selectedOptions.value.id,
            exercises: selectedOptions.value.exercise
        })
    })

    onMounted(async() => {
        const res = await routineService.getAll()

        if (res.error){
            status.value = true
            statusMsg.value = "Error on routines."
        }else if (res.result){
            routines.value = res.result.data
        }

    })

    function changeDay(){
        selectedOptions.value = ""
        store.commit("cleanActualSets")
    }

</script>

<template>
    <DateComponent @change-day="changeDay"></DateComponent>
    <div class="routines-div">
        <select v-model="selectedOptions" class="select-routine">
            <option v-bind:value="''" selected disabled>Select a routine.</option>
            <option v-for="routine in routines" v-bind:value="routine" v-bind:key="routine.name">
                {{ routine.name }}
            </option>
        </select>
        <button @click="router.push('/routine/create')" class="button-routine">Create a routine</button>
        <div v-if="selectedOptions != ''" class="routine-show">
            <button class="see-routine-button" @click="router.push('/routine/' + selectedOptions.id + '/sets')">
                <h1>SELECTED ROUTINE</h1>
                <p class="routine-name">{{ selectedOptions.name}}</p>
                <img class="img-routine" src="@/assets/routine-show.jpg"/>
            </button>
        </div>
    </div>
</template>

<style>
.routines-div {
    padding: 30px;
    height: 70vh;
}

.select-routine {
    background-color: var(--orange-color);
    border: 0px;
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
    width: 200px;
    height: 25px;
    float: left;
}

.button-routine {
    float: right;
    background-color: var(--orange-color);
    border: 0px;
    border-radius: 10px;
    height: 25px;
}

.routine-show {
    position: relative;
    width: 85vw;
    height: 60vh;
    margin-left: 2.5vw;
    margin-top: 100px;
}

.see-routine-button {
    width: 100%;
    height: 100%;
    border-radius: 10vw;
    border: 0px;
    background-color: transparent;
}

.see-routine-button h1 {
    position: absolute;
    color: white;
    margin-left: 12vw;
    margin-top: 5vh;
}

.routine-show img {
    width: 100%;
    height: 100%;
    border-radius: 10vw;
}

.routine-name {
    position: absolute;
    color: white;
    font-size: 20px;
    margin-top: 20vh;
    margin-left: 12vw;
}


@media screen and (max-width: 400px){
    .select-routine {
        width: 82vw;
    }
    
    .button-routine {
        float: right;
        margin-top: 20px;
    }
}
</style>