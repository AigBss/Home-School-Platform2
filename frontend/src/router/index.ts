import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import UserLogin from '@/views/UserLogin.vue';
import UserRegister from '@/views/UserRegister.vue';
import Test from '@/views/TestMe.vue';


const routes: Array<RouteRecordRaw> = [

  {
    path: '/user login',
    name: 'UserLogin',
    component: UserLogin,
  },
  {
    path: '/user register',
    name: 'UserRegister',
    component: UserRegister,
  },
  {
    path: '/test',
    name: 'test',
    component: Test,
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
