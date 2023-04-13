<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" label-width="100px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="loginForm.password" type="password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitLoginForm">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()
    const loginForm = ref({
      username: '',
      password: ''
    })

    function submitLoginForm() {
      store.dispatch('login', loginForm.value)
    }

    return {
      loginForm,
      submitLoginForm
    }
  }
}
</script>

<style scoped>
.login-container {
  width: 300px;
  margin: 100px auto;
}
</style>
