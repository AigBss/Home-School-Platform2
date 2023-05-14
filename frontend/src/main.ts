import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import App from './App.vue';

import UserRegister from './views/UserRegister.vue';
import UserLogin from '@/views/UserLogin.vue';
import TestMe from '@/views/TestMe.vue';
import TeacherDashboard from '@/views/TeacherDashboard.vue';
import StudentDashboard from '@/views/StudentDashboard.vue'
import StudentProfile from '@/views/StudentProfile.vue';
import TeacherProfile from '@/views/TeacherProfile.vue';
import StudentGrades from '@/views/StudentGrades.vue';
import GradesAnalysis from '@/views/GradesAnalysis.vue';
import CreateNotification from '@/views/CreateNotification.vue';
import MyNotifications from '@/views/MyNotifications.vue';
import ClassNotifications from '@/views/ClassNotifications.vue';
import FileManagement from '@/views/FileManagement.vue';
import MyMessage from '@/views/MyMessage.vue';
import MyChat from '@/views/MyChat.vue';
import MyClass from '@/views/MyClass.vue';
import UserManagement from '@/views/UserManagement.vue'
import GroupMessage from '@/views/GroupMessage.vue'
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
        path: '/gradesanalysis',
        component: GradesAnalysis,
    },
    {
        path: '/teacherdashboard',
        component: TeacherDashboard,
        children:[
            {
                path:'teacherprofile',
                component:TeacherProfile,
            },
            {
                path: 'createnotification',
                component: CreateNotification,
            },
            {
                path: 'mynotifications',
                component: MyNotifications,
            }
        ],
    },
    {
        path: '/studentdashboard',
        component: StudentDashboard,
        children:[
            {
                path:'studentprofile',
                component:StudentProfile,
            },
            {
                path:'studentgrades',
                component:StudentGrades,
            },
            {
                path: 'test',
                component: TestMe,
            },
            {
                path: 'classnotifications',
                component: ClassNotifications,
            },

        ],
    },
    {
        path: '/filemanagement',
        component: FileManagement,
    },
    // {
    //     path: '/mymessage',
    //     component: MyMessage,
    // },
    {
        path: '/mychat',
        component: MyChat,
        children: [
            {
                path: 'mymessage/:chatId',
                component: MyMessage,
            }
        ]
    },
    {
       path: '/myclass',
       component: MyClass,
    },
    {
        path: '/usermanagement',
        component: UserManagement
    },
    {
        path: '/chat/:groupChatId',
        component: GroupMessage
    }

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);

app.use(ElementPlus);
app.use(router);

app.mount('#app');
