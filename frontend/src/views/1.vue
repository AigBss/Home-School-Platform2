<template>
  <div class="loginbody">
    <div class="logindata">
      <div class="logo-wrapper">
        <img src="../../assets/cartoon-factory.jpg" alt="cartoon-factory" />
      </div>
      <div class="form-wrapper">
        <div class="logintext">
          <h2>Welcome</h2>
        </div>
        <el-form ref="form" :model="form" :rules="rules">
          <el-form-item prop="name">
            <el-input v-model="form.name" clearable placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="form.password" clearable placeholder="请输入密码" show-password></el-input>
          </el-form-item>
        </el-form>
        <div class="tool">
          <div>
            <el-checkbox v-model="checked" @change="remenber">记住密码</el-checkbox>
          </div>
          <div>
            <span class="shou">忘记密码？</span>
          </div>
        </div>
        <div class="butt-wrapper">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button>注册</el-button>
        </div>
      </div>
    </div>
    <div class="logo-background"></div>
  </div>
</template>


<script>
import { inject } from 'vue';
export default {
  name: "MyLogin",
  setup() {
    const axiosInstance = inject('axiosInstance');
    return { axiosInstance };
  },
  data() {
    return {
      form: {
        password: "",
        name: "",
      },
      checked: false,
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    login() {
      this.axiosInstance.post("user/login", this.form).then((resp) => {
        if (resp.status === 200) {
          this.$message({
            message: "登录成功",
            type: "success",
          });

          const sessionId = resp.data;
          localStorage.setItem("SESSION_ID", sessionId);
          this.$router.push("/factorymain");
        } else {
          this.$message({
            message: "账号或密码错误",
            type: "error",
          });
        }
      });
    },
  },
}
</script>
<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  background-image: linear-gradient(135deg, #f6d365 0%, #fda085 100%);
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
  background-color: #f7f7f7;
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

.formdata {
  margin-bottom: 20px;
}

.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}

.butt {
  margin-top: 10px;
  text-align: center;
}

.shou {
  cursor: pointer;
  color: #606266;
  font-weight: bold;
  transition: color 0.3s;
}

.shou:hover {
  color: #409EFF;

}

.el-form-item__label {
  font-weight: bolder;
  font-size: 15px;
  text-align: left;
}

.el-button {
  width: 100%;
  margin-bottom: 10px;
}
</style>