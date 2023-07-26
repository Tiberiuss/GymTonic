import { createRouter, createWebHistory } from 'vue-router'
import {userService} from "@/services/user.service";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landing',
      component: () => import('@/views/LandingView.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/UserLoginView.vue'),
      meta: {guest:true}
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/UserRegisterView.vue'),
      meta: {guest:true}
    },
    {
      path: '/hub',
      name: 'hub',
      component: () => import('@/views/HubView.vue'),
      meta: {auth:true}
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('@/views/ExerciseIndexView.vue'),
      meta: {auth:true,header:true}
    },
    {
      path: '/index/:itemId',
      name: 'index-item',
      component: () => import('@/views/ExerciseDetailView.vue'),
      meta: {auth:true,header:true}
    },
    {
      path: '/routine/create',
      name: 'createroutine',
      component: () => import('@/views/RoutineCreateView.vue'),
      meta: {auth:true,header:true}
    },
    {
      path: '/routine/create/addname',
      name: 'addnameroutine',
      component: () => import('@/views/RoutinePreviewView.vue'),
      meta: {auth:true, header: true}
    },
    {
      path: '/routine/:idRoutine/sets/:date',
      name: 'routinesets',
      component: () => import('@/views/RoutineSetsView.vue'),
      meta: {auth:true, header:true}
    },
    {
      path: '/chart/:itemId',
      name: 'chart',
      component: () => import('@/views/ExerciseChartView.vue'),
      meta: {auth:false, header: true}
    },
    {
      path: '/routine',
      name: 'routineindex',
      component: () => import('@/views/RoutineIndexView.vue'),
      meta: {auth:true,header:true}
    },
    {
      path: '/workout/:idWorkout/sets/:date',
      name: 'workoutsets',
      component: () => import('@/views/WorkoutSetsView.vue'),
      meta: {auth:true}
    }
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