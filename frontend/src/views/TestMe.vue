<template>
<!--  <el-container>-->
<!--    <el-main>-->
<!--      <div class="login-container">-->
<!--        <h1 class="login-title">登录</h1>-->
<!--        <el-form  :model="loginForm" :rules="rules" label-width="80px">-->
<!--          <el-form-item label="用户名" prop="username">-->
<!--            <el-input v-model="loginForm.username" autocomplete="off"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="密码" prop="password">-->
<!--            <el-input type="password" v-model="loginForm.password" autocomplete="new-password"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item>-->
<!--            <el-button type="success" round class="login-button" @click="submitForm('loginForm')">登录</el-button>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--      </div>-->
<!--    </el-main>-->
<!--  </el-container>-->

  <el-button text @click="dialogVisible = true">
    click to open the Dialog
  </el-button>

  <el-dialog
      v-model="dialogVisible"
      title="Tips"
      width="30%"
      :before-close="handleClose"
  >
    <span>This is a message</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogVisible = false">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>


<script lang="ts" setup>
import { ref } from 'vue'
import {ElMessageBox} from "element-plus";
const dialogVisible = ref(false)

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('Are you sure to close this dialog?')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}
</script>



<!--<script>-->
<!--import { ref } from "vue";-->
<!--import { ElForm, ElFormItem, ElInput, ElButton } from "element-plus";-->
<!--import axios from "axios";-->

<!--export default {-->
<!--  name: "LoginMe",-->
<!--  components: {-->
<!--    ElForm,-->
<!--    ElFormItem,-->
<!--    ElInput,-->
<!--    ElButton,-->
<!--  },-->
<!--  setup() {-->
<!--    const loginForm = ref({-->
<!--      username: "",-->
<!--      password: "",-->
<!--    });-->
<!--    const rules = ref({-->
<!--      username: [-->
<!--        { required: true, message: "请输入用户名", trigger: "blur" },-->
<!--      ],-->
<!--      password: [-->
<!--        { required: true, message: "请输入密码", trigger: "blur" },-->
<!--      ],-->
<!--    });-->

<!--    const submitForm = async (formName) => {-->
<!--      if (!loginForm.value.username || !loginForm.value.password) {-->
<!--        return;-->
<!--      }-->
<!--      try {-->
<!--        const response = await axios.post("http://localhost:8080/api/login", {-->
<!--          username: loginForm.value.username,-->
<!--          password: loginForm.value.password,-->
<!--        });-->
<!--        if (response.data.success) {-->
<!--          console.log("登录成功");-->
<!--          // 根据实际情况跳转到其他页面，例如：-->
<!--          // router.push("/dashboard");-->
<!--        } else {-->
<!--          console.log("登录失败：" + response.data.message);-->
<!--        }-->
<!--      } catch (error) {-->
<!--        console.error("登录请求失败", error);-->
<!--      }-->
<!--    };-->

<!--    return {-->
<!--      loginForm,-->
<!--      rules,-->
<!--      submitForm,-->
<!--    };-->
<!--  },-->
<!--};-->
<!--</script>-->

<style scoped>
.login-container {
  max-width: 400px;
  margin: 100px auto;
}

.login-button {
  float: right;
  margin-left: 55%;
}

.login-title {
  text-align: center;
  margin-bottom: 80px;
}
</style>
