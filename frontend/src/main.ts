import { createApp } from 'vue'
import App from './App.vue'
// import Vue from 'vue'
import router from './router/index'
import vuex from  './store/index'
import axios from 'axios'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import "lib-flexible/flexible.js"
axios.defaults.baseURL="http://localhost:8080/"
axios.defaults.withCredentials=true

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
// Vue.prototype.$echarts = echarts
app.config.globalProperties.$http = axios
app.use(router)
app.use(vuex)
app.use(ElementPlus)
app.mount('#app')