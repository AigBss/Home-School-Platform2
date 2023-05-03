<template>
  <div class="register">
    <h1>注册</h1>
    <el-form @submit.prevent="submitForm" ref="registerForm" :model="registerForm" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="registerForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="registerForm.password"></el-input>
      </el-form-item>
      <el-form-item label="用户类型">
        <el-select v-model="registerForm.userType" placeholder="请选择用户类型">
          <el-option label="管理员" value="1"></el-option>
          <el-option label="老师" value="2"></el-option>
          <el-option label="学生" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      registerForm: {
        username: '',
        password: '',
        userType: '',
      },
    };
  },
  methods: {
    async submitForm() {
      try {
        // 调用后端 API 进行注册
        const response = await axios.post('http://localhost:8080/api/register', this.registerForm);

        if (response.status === 201) {
          this.$message.success('注册成功');
          this.$router.push('/');
        } else {
          this.$message.error('注册失败');
        }
      } catch (error) {
        console.log(error)
        this.$message.error('注册失效');
      }
    },
  },
};
</script>

<style scoped>
.register {
  width: 400px;
  margin: 50px auto;
}
</style>
