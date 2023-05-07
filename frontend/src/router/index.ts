import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import UserLogin from '@/views/UserLogin.vue';
import UserRegister from '@/views/UserRegister.vue';
import Test from '@/views/TestMe.vue';
import TeacherDashboard from '@/views/TeacherDashboard.vue';
import ChildrenDashboard from '@/views/ChildrenDashboard.vue'


const routes: Array<RouteRecordRaw> = [

  {
    path: '/userlogin',
    name: 'UserLogin',
    component: UserLogin,
  },
  {
    path: '/userregister',
    name: 'UserRegister',
    component: UserRegister,
  },
  {
    path: '/test',
    name: 'test',
    component: Test,
  },
  {
    path: '/teacherdashboard',
    name: 'TeacherDashboard',
    component: TeacherDashboard,
  },
  {
    path: '/childrendashboard',
    name: 'ChildrenDashboard',
    component: ChildrenDashboard,
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
