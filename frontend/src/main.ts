import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import App from './App.vue';

import UserRegister from './views/UserRegister.vue';
import UserLogin from '@/views/UserLogin.vue';
import TestMe from '@/views/TestMe.vue';
import TeacherDashboard from '@/views/TeacherDashboard.vue';
// import axios from "axios";
//
// axios.defaults.withCredentials=true;

const routes = [

    {
        path: '/',
        component: UserLogin,
    },
    {
        path: '/userregister',
        component: UserRegister,
    },
    {
        path: '/test',
        component: TestMe,
    },
    {
        path: '/teacherdashboard',
        component: TeacherDashboard,
    },

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);

app.use(ElementPlus);
app.use(router);

app.mount('#app');
