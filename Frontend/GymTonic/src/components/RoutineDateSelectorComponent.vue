<script setup lang="ts">
    import IconNextDate from '@/components/icons/IconNextDate.vue';
    import IconBackDate from '@/components/icons/IconBackDate.vue';
    import { onMounted, ref } from 'vue';
    import type { dayType } from "@/types";
    import { workoutService } from '@/services/workout.service';

    const emit = defineEmits(['change-day'])

    const date = ref<Date>(new Date())
    const actualDateStr = ref<string>(new Date().toLocaleDateString())
    const dateStr = ref<string>(date.value.toLocaleDateString())
    const week = ref<dayType[]>([])
    
    let actualMonth = date.value.getMonth()
    let actualYear = date.value.getFullYear()

    date.value.setDate(date.value.getDate() - date.value.getDay())

    let lettersDay = [ 'S', 'M', 'T', 'W', 'T', 'F', 'S']

    let lastDate = false
    let today = false
    const actualDate = new Date()

    onMounted(async() => {
        week.value = await fetchWeek();
        dateStr.value = date.value.toLocaleDateString()
    })
    async function changeWeek(days:number){
        date.value.setDate((date.value.getDate() - date.value.getDay()) + days);
        week.value = await fetchWeek();

        updateDates();
        // TODO why the lastDate and today??
        let returnDate = date.value;
        if (actualDate >= date.value && actualDate <= new Date(new Date(date.value.getTime()).setDate(date.value.getDate() + 7))) {
           returnDate = actualDate;
        }
        emit('change-day', returnDate.toISOString().slice(0,10), lastDate, today);
    }

    function backWeek(){
        changeWeek(-7);
    }

    function nextWeek(){
        changeWeek(7);
    }

    function updateDates(){
        dateStr.value = date.value.toLocaleDateString();
        actualMonth = date.value.getMonth();
        actualYear = date.value.getFullYear();
        lastDate = date.value < actualDate;
        today = date.value === actualDate;
    }
    async function fetchWeek(){
        let fetchedWeek = []
        let auxDate = new Date(date.value.getTime())
        let todayInWeek = actualDate >= date.value && actualDate <= new Date(new Date(date.value.getTime()).setDate(date.value.getDate() + 7))

        for (let i=0; i<7; i++){
            let workout: any = null
            let todayDate = auxDate.toISOString().slice(0, 10)
            let res = await workoutService.getByDate(todayDate)
            if (res.result && res.result.data.length !== 0){
                workout = res.result.data
            }
            fetchedWeek.push({
                letter: lettersDay[i],
                strDate: todayDate,
                day: auxDate.getDate(),
                today: actualDateStr.value === auxDate.toLocaleDateString(),
                selected: todayInWeek ? actualDateStr.value === auxDate.toLocaleDateString() : i===0,
                workout: workout
            })
            auxDate.setDate(auxDate.getDate() + 1)
        }

        return fetchedWeek;
    }

    function changeSelected(day: dayType){
        week.value.forEach((day) => day.selected = false)
        day.selected = true
        let auxMove = date.value.getDate() - day.day
        if (auxMove > 7){
            if (actualMonth == 0 || actualMonth == 2 || actualMonth == 4 || actualMonth == 6 || actualMonth == 7 || actualMonth == 9 || actualMonth == 11){
                auxMove = auxMove - 31
            } else {
                if (actualMonth == 1){
                    if ((actualYear % 4 == 0) && ((actualYear % 100 != 0) || (actualYear % 400 == 0))){
                        auxMove = auxMove - 29
                    }else{
                        auxMove = auxMove - 28
                    }
                }else{
                    auxMove = auxMove - 30
                }
            }
            date.value.setDate(date.value.getDate() - auxMove)
            actualMonth = date.value.getMonth()
            actualYear = date.value.getFullYear()
        }else if(auxMove < -7){
            if (actualMonth == 0 || actualMonth == 1 || actualMonth == 3 || actualMonth == 5 || actualMonth == 6 || actualMonth == 7 || actualMonth == 8 || actualMonth == 10){
                auxMove = auxMove + 31
            } else {
                if (actualMonth == 2){
                    if ((actualYear % 4 == 0) && ((actualYear % 100 != 0) || (actualYear % 400 == 0))){
                        auxMove = auxMove + 29
                    }else{
                        auxMove = auxMove + 28
                    }
                }else{
                    auxMove = auxMove + 30
                }
            }
            date.value.setDate(date.value.getDate() - auxMove)
            actualMonth = date.value.getMonth()
            actualYear = date.value.getFullYear()
        }else{
            date.value.setDate(date.value.getDate() - auxMove)
        }
        dateStr.value = date.value.toLocaleDateString()
        if (date.value < actualDate){
            lastDate = true
        }else{
            lastDate = false
        }

        if (date.value == actualDate){
            today = true
        }

        emit('change-day', date.value.toISOString().slice(0,10))
    }

    function notify(){
        week.value[date.value.getDay()].workout = null
    }

    defineExpose({
        notify
    })
</script>

<template>
    <div class="date-div">
        <h1>
            <button class="change-date" @click="backWeek">
                <IconBackDate></IconBackDate>
            </button>
            {{ dateStr }}
            <button class="change-date" @click="nextWeek">
                <IconNextDate></IconNextDate>
            </button>
        </h1>
        <div class="day-grid">
            <button v-for="day in week" @click="changeSelected(day)" :class="(day.today)?'today':'day'" :key="day.day">
                <div v-if="day.selected" class="iam">
                    <div v-if="day.workout !== null" class="workout-notify"></div>
                    <p>{{ day.letter }}</p>
                    <p>{{ day.day }}</p>
                </div>
                <div v-else>
                    <div v-if="day.workout !== null" class="workout-notify"></div>
                    <p>{{ day.letter }}</p>
                    <p>{{ day.day }}</p>
                </div>
            </button>
        </div>
    </div>
</template>

<style>
.workout-notify {
    background-color: var(--text);
    width: 7px;
    height: 7px;
    margin-left: auto;
    margin-right: auto;
    margin-top: -7px;
    border-radius: 10px;
}

.date-div {
    text-align: center;
    background-color: var(--form-user-color);
    border-radius: 30px;
    max-width: 45em;
}

.change-date {
    border: 0px;
    background-color: transparent;
}

.day-grid {
    height: 100px;    
    display: grid;
    grid-template-columns: repeat(7, 14.2%);
    margin-left: auto;
    margin-right: auto;
}

.day {
    background-color: var(--grey-color);
    height: 80px;
    width: 50px;
    margin-left: auto;
    margin-right: auto;
    border-radius: 30px;
    border: 0;
    transition: 800ms;
}

.day:hover {
    background-color: white;
} 

.today {
    height: 80px;
    width: 50px;
    margin-left: auto;
    margin-right: auto;
    border-radius: 30px;
    background-color: var(--orange-color);
    border: 0px;
}

.day p {
    margin-bottom: 0px;
}

.today p {
    margin-bottom: 0px;
}

.iam{
    position: relative;
    background-color: var(--white-transparent-color);
    border-radius: 30px;
    width: 50px;
    height: 80px;
    margin-left: -6px;
    padding-top: 15px;
}

@media screen and (max-width: 800px) {
    
    .workout-notify {
        background-color: var(--orange-color);
        width: 7px;
        height: 7px;
        margin-left: auto;
        margin-right: auto;
        border-radius: 10px;
    }

    .day {
        height: 80px;
        width: 40px;
        margin-left: 40%;
        border-radius: 30px;
    }

    .today {
        height: 80px;
        width: 40px;
        margin-left: 40%;
        border-radius: 30px;
    }

    .iam {
        width: 40px;
    }
}

@media screen and (max-width: 600px) {
    .day-grid {
        display: grid;
        height: 60px;
        margin-left: 0%;
        grid-template-columns: repeat(7, 14.2%);
    }

    .workout-notify {
        width: 5px;
        height: 5px;
        margin-left: auto;
        margin-right: auto;
        border-color: var(--orange-color);
        border-radius: 10px;
        margin-top: 1px;
        margin-bottom: -3px;
    }
    
    .day {
        padding-top: 0px;
        height: 50px;
        width: 40px;
        margin-left: 20%;
        border-radius: 30px;
    }

    .today {
        padding-top: 0px;
        height: 50px;
        width: 40px;
        margin-left: 20%;
        border-radius: 30px;
    }

    .iam {
        height: 50px;
        padding-top: 2px;
    }

    .iam .workout-notify {
        width: 5px;
        height: 5px;
        margin-left: auto;
        margin-right: auto;
        margin-top: -2px;
        border-color: var(--orange-color);
        border-radius: 10px;
        margin-bottom: -3px;
    }
}

@media screen and (max-width: 400px) {
    .day-grid {
        display: grid;
        grid-template-columns: repeat(7, 14.2%);
    }

    .day {
        height: 50px;
        width: 30px;
        margin-left: 10%;
        border-radius: 30px;
    }

    .today {
        height: 50px;
        width: 30px;
        margin-left: 10%;
        border-radius: 30px;
    }

    .iam {
        height: 50px;
        width: 30px;
        padding-top: 0px;
    }
}

@media screen and (max-width: 300px) {
    .date-div h1{
        font-size: smaller;
    }

    .day-grid {
        display: grid;
        grid-template-columns: repeat(7, 14.2%);
    }

    .day {
        height: 50px;
        width: 30px;
        border-radius: 30px;
    }

    .today {
        height: 50px;
        width: 30px;
        border-radius: 30px;
    }

    .iam {
        height: 50px;
        width: 30px;
        padding-top: 0px;
    }
}

</style>