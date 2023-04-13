import axios from 'axios'
import { createStore } from 'vuex'
import user from './user'

export default createStore({
    modules: {
        user
    }
})


const state = () => ({
token: localStorage.getItem('token') || ''
})

const getters = {
isLoggedIn: (state) => !!state.token
}

const actions = {
async login({ commit }, loginForm) {
try {
const response = await axios.post('/api/login', loginForm)
const token = response.data.token
localStorage.setItem('token', token)
commit('setToken', token)
} catch (error) {
console.error('登录失败:', error)
throw error
}
},
logout({ commit }) {
localStorage.removeItem('token')
commit('setToken', '')
}
}

const mutations = {
setToken: (state, token) => (state.token = token)
}

export default {
namespaced: true,
state,
getters,
actions,
mutations
}
