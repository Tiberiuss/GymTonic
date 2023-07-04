import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/IndexView.vue'),
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('../views/IndexView.vue'),
    },
    {
      path: '/index/:itemId',
      name: 'index-item',
      props: true,
      component: () => import('../views/ExerciseDetailsView.vue')
    },
    {
      path: '/routine/create',
      name: 'createroutine',
      component: () => import('../views/CreateRoutine.vue'),
    },
    {
      path: '/routine/create/addname',
      name: 'addnameroutine',
      props: true,
      component: () => import('../views/PreviewRoutineView.vue'),
    }
  ]
})

export default router
