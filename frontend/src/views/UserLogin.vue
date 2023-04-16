<template>
  <el-container>
    <el-main>
      <div class="login-container">
        <h1 class="login-title">登录</h1>
        <el-form ref="loginForm" :model="loginForm" :rules="rules" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="loginForm.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="loginForm.password" autocomplete="new-password"></el-input>
          </el-form-item>
          <el-form-item>
            <vue-drag-verify
                ref="dragVerify"
                @success="onDragVerifySuccess"
                @refresh="onDragVerifyRefresh"
            ></vue-drag-verify>
          </el-form-item>
          <el-form-item>
            <el-button type="success" round @click="submitForm('loginForm')">登录</el-button>
            <el-button @click="switchToRegister">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
import { ref } from "vue";
import { useRouter } from "vue-router";
import { ElForm, ElFormItem, ElInput, ElButton } from "element-plus";
import {VueDragVerify} from "vue-drag-verify";
import axios from "axios";

export default {
  name: "LoginWithDragVerify",
  components: {
    ElForm,
    ElFormItem,
    ElInput,
    ElButton,
    VueDragVerify,
  },
  setup() {
    const router = useRouter();
    const loginForm = ref({
      username: "",
      password: "",
    });
    const rules = ref({
      username: [
        { required: true, message: "请输入用户名", trigger: "blur" },
      ],
      password: [
        { required: true, message: "请输入密码", trigger: "blur" },
      ],
    });

    const submitForm = async (formName) => {
      if (!loginForm.value.username || !loginForm.value.password) {
        return;
      }
      try {
        const response = await axios.post("http://localhost:8080/api/login", {
          username: loginForm.value.username,
          password: loginForm.value.password,
        });
        if (response.data.success) {
          console.log("登录成功");
          // 根据实际情况跳转到其他页面，例如：
          // router.push("/dashboard");
        } else {
          console.log("登录失败");
        }
      } catch (error) {
        console.error("登录请求失败", error);
      }
    };

    const switchToRegister = () => {
      router.push("/userregister");
    };

    const onDragVerifySuccess = () => {
      console.log("拖动验证成功");
    };

    const onDragVerifyRefresh = () => {
      console.log("拖动验证刷新");
    };

    return {
      loginForm,
      rules,
      submitForm,
      switchToRegister,
      onDragVerifySuccess,
      onDragVerifyRefresh,
    };
  },
};
</script>


<style scoped>
.login-container {
  max-width: 400px;
  margin: 100px auto;
}

.login-title {
  text-align: center;
  margin-bottom: 30px;
}
</style>




