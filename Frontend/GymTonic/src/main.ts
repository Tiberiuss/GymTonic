import 'vue3-carousel/dist/carousel.css'

import '@/assets/css/global-variables.css';
import '@/assets/css/main.css'
import '@/assets/css/carousel.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { vueStore } from '@/store/store'

const app = createApp(App)

app.use(router)
app.use(vueStore)
app.mount('#app')
