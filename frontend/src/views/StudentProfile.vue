<template>
  <el-container>
    <el-main>
      <div style="margin-left: 150px; margin-top: 50px">
        <h1 style="font-size: large">欢迎，学生用户 {{ username }}</h1>
        <el-button type="primary" @click="showEditUsernameDialog = true">修改用户名</el-button>
        <el-button type="primary" @click="showEditPasswordDialog = true">修改密码</el-button>
      </div>
    </el-main>
  </el-container>

  <!-- Edit Username Dialog -->
  <el-dialog title="修改用户名" v-model="showEditUsernameDialog" append-to-body>
    <el-input v-model="newUsername" placeholder="新的用户名"></el-input>
    <template #footer>
      <el-button @click="showEditUsernameDialog = false">取消</el-button>
      <el-button type="primary" @click="updateUsername">确认</el-button>
    </template>
  </el-dialog>

  <!-- Edit Password Dialog -->
  <el-dialog title="修改密码" v-model="showEditPasswordDialog">
    <el-input v-model="newPassword" placeholder="新的密码" show-password></el-input>
    <template #footer>
      <el-button @click="showEditPasswordDialog = false">取消</el-button>
      <el-button type="primary" @click="updatePassword">确认</el-button>
    </template>
  </el-dialog>
</template>


<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import {useRouter} from 'vue-router';

import axios from 'axios';
import {ElMessage} from 'element-plus'


const fileUploadRef = ref(null);

const router = useRouter();

const username = ref('');
const userId = ref(null);
const newUsername = ref('');
const newPassword = ref('');

const showEditUsernameDialog = ref(false);
const showEditPasswordDialog = ref(false);

onMounted(() => {
  const userString = localStorage.getItem('user');
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.username) {
      username.value = user.username;
      userId.value = user.id;
    }
  }
});

const updateUsername = async () => {
  if (userId.value && typeof userId.value === 'number' && newUsername.value && typeof newUsername.value === 'string') {
    try {
      const response = await axios.put(`http://localhost:8080/api/users/${userId.value}/username`, newUsername.value);

      if (response.status === 200) {
        const userString = localStorage.getItem('user');
        if (userString) {
          const updatedUser = {
            ...JSON.parse(userString),
            username: newUsername.value,
          };
          localStorage.setItem('user', JSON.stringify(updatedUser));
          username.value = newUsername.value;
          newUsername.value = '';
        }
        showEditUsernameDialog.value = false;
      } else {
        // 根据后端返回的错误消息显示错误
        ElMessage.error(response.data.message);
      }
    } catch (error) {
      ElMessage.error('非法修改');
    }
  }
};

const updatePassword = async () => {
  if (userId.value && typeof userId.value === 'number' && newPassword.value && typeof newPassword.value === 'string') {
    try {
      const response = await axios.put(`http://localhost:8080/api/users/${userId.value}/password`, newPassword.value);
      if (response.status === 200) {
        newPassword.value = '';
        showEditPasswordDialog.value = false;
      } else {
        // 根据后端返回的错误消息显示错误
        ElMessage.error(response.data.message);
      }
    } catch (error) {
      ElMessage.error('非法修改');
    }
  }
};


</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

.layout-aside {
  background-color: #545c64;
}

.el-header {
  text-align: right;
  font-size: 16px;
  background-color: #545c64;
  color: white;
}

.layout-main {
  margin-left: 150px;
  margin-top: 50px;
}


</style>
