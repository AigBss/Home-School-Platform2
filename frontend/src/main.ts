import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import App from './App.vue';

import UserRegister from './views/UserRegister.vue';
import UserLogin from '@/views/UserLogin.vue';
import TestMe from '@/views/TestMe.vue';

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

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);

app.use(ElementPlus);
app.use(router);

app.mount('#app');
