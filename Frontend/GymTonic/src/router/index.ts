import { createRouter, createWebHistory } from 'vue-router'
import {userService} from "@/services/user.service";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landing',
      component: () => import('@/views/LandingView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/LoginView.vue'),
      meta: {guest:true}
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/RegisterView.vue'),
      meta: {guest:true}
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('@/views/IndexView.vue'),
      meta: {auth:true}
    },
    {
      path: '/index/:itemId',
      name: 'index-item',
      component: () => import('@/views/ExerciseDetailsView.vue'),
      meta: {auth:true}
    },
    {
      path: '/routine/create',
      name: 'createroutine',
      component: () => import('@/views/CreateRoutine.vue'),
      meta: {auth:true}
    },
    {
      path: '/routine/create/addname',
      name: 'addnameroutine',
      component: () => import('@/views/PreviewRoutineView.vue'),
      meta: {auth:true}
    },
    {
      path: '/routine/:idRoutine/sets',
      name: 'routinesets',
      component: () => import('@/views/RoutineSetsView.vue'),
      meta: {auth:true}
    },
    {
      path: '/chart',
      name: 'chart',
      component: () => import('@/views/ChartView.vue'),
      meta: {auth:false}
    },
  ]
})

router.beforeEach((to) => {
  if(to.matched.some(record => record.meta.auth) && !userService.isAuthenticated())
    return {path:'/login'};
})

router.beforeEach((to) => {
  if (to.matched.some((record) => record.meta.guest) && userService.isAuthenticated())
    return {path:"/index"};
});

export default router