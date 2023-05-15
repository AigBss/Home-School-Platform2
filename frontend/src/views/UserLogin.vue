<template>
  <el-container>
    <div class="loginbody">
      <div class="logindata">
        <div class="logo-wrapper">
          <img src="https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg" alt="cartoon-factory" />
        </div>
        <div class="form-wrapper">
          <div class="logintext">
            <h2>Welcome</h2>
          </div>
          <div class="login-container">
            <el-form :model="loginForm" :rules="rules" label-width="80px">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="loginForm.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="loginForm.password" autocomplete="new-password"></el-input>
              </el-form-item>
              <el-form-item class="drag-verify-wrapper">
                <drag-verify
                    ref="dragVerify"
                    v-model:value="isPassing"
                >
                </drag-verify>
              </el-form-item>
              <el-form-item class="button-wrapper" >
                <el-link type="primary" round class="register-button" @click="switchToRegister">没有账号？立即注册</el-link>
                <el-button type="success" round class="login-button" @click="submitForm('loginForm')">登录</el-button>

              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
      <div class="logo-background"></div>
    </div>
  </el-container>
</template>

<script>

import {ref} from "vue";
import {useRouter} from "vue-router";
import {ElForm, ElFormItem, ElInput, ElButton} from "element-plus";
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
  margin: 0 auto;
  padding: 20px;
}

.button-wrapper >>> .el-form-item__content{
  margin-left: 20px !important;
  display: flex;
  justify-content: space-between;
}

.login-button {
  margin-left: 0px !important;

}

.register-button {
  margin-left: 0px !important;
  margin-right: 70px;
}



.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  background-image: linear-gradient(135deg, #8599fd 0%, #13247e 100%);
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
  padding-top: 150px;
}

.logindata {
  width: 800px;
  height: 380px;
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  transform: translate(-50%);
  margin-left: 50%;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  display: flex;
}

.logo-wrapper {
  width: 50%;
  height: 68%;
  margin-top: 50px;
  margin-left: 20px;
  background-color: rgba(255, 255, 255, 0.8);
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-wrapper img {
  max-width: 100%;
  max-height: 100%;
}
.form-wrapper{
  width: 48%;
  margin-top: 0px;
  margin-left: 20px;
}

.logintext {
  margin-bottom: 20px;
  line-height: 50px;
  text-align: center;
  font-size: 30px;
  font-weight: bolder;
  color: #333;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);
}


</style>




