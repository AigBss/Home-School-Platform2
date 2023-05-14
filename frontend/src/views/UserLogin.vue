<template>
  <el-container>
    <el-main>
      <div class="login-container">
        <h1 class="login-title">登录</h1>
        <el-form :model="loginForm" :rules="rules" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="loginForm.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="loginForm.password" autocomplete="new-password"></el-input>
          </el-form-item>
          <el-form-item>
            <!--            <drag-verify-->
            <!--                ref="dragVerify"-->
            <!--                @success="onDragVerifySuccess"-->
            <!--                @refresh="onDragVerifyRefresh"-->
            <!--            ></drag-verify>-->
            <drag-verify
                ref="dragVerify"
                v-model:value="isPassing"
            >
            </drag-verify>
          </el-form-item>
          <el-form-item>
            <el-button type="success" round class="login-button" @click="submitForm('loginForm')">登录</el-button>
            <el-button type="primary" round class="register-button" @click="switchToRegister">注册</el-button>

          </el-form-item>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {ref} from "vue";
import {useRouter} from "vue-router";
import {ElForm, ElFormItem, ElInput, ElButton} from "element-plus";
// import  {VueDragVerify } from "vue-drag-verify";
// import dragVerify from "vue-drag-verify";
import dragVerify from "@/components/DragVerify";
import cookies from "js-cookie";

import axios from "axios";

export default {
  name: "LoginWithDragVerify",
  components: {
    ElForm,
    ElFormItem,
    ElInput,
    ElButton,
    // VueDragVerify,
    dragVerify
  },
  setup() {
    const router = useRouter();
    const loginForm = ref({
      username: "",
      password: "",
    });
    const isPassing = ref({});
    const userType = ref(null);
    const rules = ref({
      username: [
        {required: true, message: "请输入用户名", trigger: "blur"},
      ],
      password: [
        {required: true, message: "请输入密码", trigger: "blur"},
      ],
    });

    const submitForm = async (formName) => {
      console.log(isPassing.value)
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

          cookies.set("userId", response.data.user.id);
          cookies.set("userType", response.data.user.userType);

          // 保存user信息到localstorage
          localStorage.setItem("user", JSON.stringify(response.data.user));

          userType.value = response.data.user.userType;

          if (userType.value == "3") {
            await router.push("/studentdashboard/studentprofile");
          } else if (userType.value == "2") {
            await router.push("/teacherdashboard/teacherprofile");
          } else if (userType.value == "1") {
            await router.push("/usermanagement");
          } else {
            // 如果用户类型不存在，您可以选择重定向到其他页面或显示错误消息。
            console.error("Invalid userType.");
          }
          // // 跳转到其他页面，例如：
          // await router.push("/user-profile");
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

    const passcallback = () => {
      console.log(isPassing)
      console.log("pass");
    };

    return {
      loginForm,
      rules,
      submitForm,
      switchToRegister,
      onDragVerifySuccess,
      onDragVerifyRefresh,
      isPassing,
    };
  },
};
</script>


<style scoped>
.login-container {
  max-width: 400px;
  margin: 100px auto;
}

.login-button {
  float: right;
  margin-left: 55%;
}

.register-button {
  float: right;
  margin-left: 20px;
}


.login-title {
  text-align: center;
  margin-bottom: 80px;
}
</style>




