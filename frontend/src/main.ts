import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import App from './App.vue';

import UserRegister from './views/UserRegister.vue';
import UserDashboard from './views/UserDashboard.vue';
import UserProfile from './views/UserProfile.vue';
import UserLogin from '@/views/UserLogin.vue';

const routes = [
    {
        path: '/',
        redirect: '/userdashboard',
    },
    {
        path: '/userlogin',
        component: UserLogin,
    },
    {
        path: '/userregister',
        component: UserRegister,
    },
    {
        path: '/userdashboard',
        component: UserDashboard,
        children: [
            {
                path: 'userprofile',
                component: UserProfile,
            },
        ],
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
