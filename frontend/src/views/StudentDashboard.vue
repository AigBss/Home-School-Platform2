<template>
  <el-container class="layout-container-demo" style="height: 900px">
    <div>
      <div
          style="background: rgb(28, 31, 34); height: 60px; width:200px; text-align: center; vertical-align: middle; display: table-cell; color: white">
        家校信息互通平台
      </div>
      <el-aside width="200px"
                style="background-color: #545c64; height: 840px"
                class="layout-aside">

        <el-scrollbar>
          <el-menu :default-openeds="['1', '3']"
                   active-text-color="#ffd04b"
                   background-color="#545c64"
                   text-color="#fff">
            <el-sub-menu index="1">
              <template #title>
                <el-icon>
                  <message/>
                </el-icon>
                我的聊天
              </template>
              <el-menu-item-group>
                <template #title>个人聊天</template>
                <el-menu-item index="1-1" @click="gotoMyChat">我的聊天</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="班级聊天">
                <el-menu-item index="1-3" @click="gotomyclass">我的班级</el-menu-item>
                <el-menu-item index="1-4" @click="showJoinClassDialog = true">加入班级</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon>
                  <icon-menu/>
                </el-icon>
                通知
              </template>
              <el-menu-item index="2-1" @click="goToClassNotifications">班级通知</el-menu-item>
            </el-sub-menu>

            <el-sub-menu index="3">
              <template #title>
                <el-icon>
                  <icon-menu/>
                </el-icon>
                成绩
              </template>
              <el-menu-item index="3-2" @click="navigateToMyGrades">我的成绩</el-menu-item>
              <el-menu-item index="3-3" @click="navigateToGradeAnalysis">成绩分析</el-menu-item>


            </el-sub-menu>
            <el-sub-menu index="4">
              <template #title>
                <el-icon>
                  <icon-menu/>
                </el-icon>
                文件
              </template>

              <el-menu-item index="4-1" @click="gotoFileManagement">文件管理</el-menu-item>
            </el-sub-menu>

          </el-menu>
        </el-scrollbar>
      </el-aside>
    </div>
    <el-container>
      <el-header
          style="text-align: right; font-size: 16px;background-color: #ffffff;color: black;class:'layout-header'">

        <div class="toolbar">
          <div>
            <el-avatar :icon="UserFilled"/>
          </div>
          <span style="margin-left: 10px">{{ username }}</span>


          <el-dropdown style="margin-left: 10px">
            <el-icon color="black" style="margin-right: 8px; margin-top: 1px;color: black"
            >
              <ArrowDownBold/>
            </el-icon>

            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="logout">Quit</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>


        </div>
      </el-header>


      <el-main>
        <router-view></router-view>
      </el-main>

      <!-- Edit Username Dialog -->
      <el-dialog title="修改用户名" v-model="showEditUsernameDialog">
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


      <!-- 加入班级弹窗 -->
      <el-dialog title="加入班级" v-model="showJoinClassDialog">
        <el-input v-model="classToJoinId" placeholder="请输入班级ID" type="number"></el-input>
        <template #footer>
          <el-button @click="showJoinClassDialog = false">取消</el-button>
          <el-button type="primary" @click="joinClass">确认</el-button>
        </template>
      </el-dialog>


    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import {useRouter} from 'vue-router';
import {Menu as IconMenu, Message, Setting} from '@element-plus/icons-vue';
import axios from 'axios';
import {ElMessage} from 'element-plus'
import {UserFilled} from '@element-plus/icons-vue'

const fileUploadRef = ref(null);

const router = useRouter();

const username = ref('');
const userId = ref(null);
const newUsername = ref('');
const newPassword = ref('');

const navigateToMyGrades = () => {
  router.push('/studentdashboard/studentgrades');
};

const navigateToGradeAnalysis = () => {
  router.push('/gradesanalysis');
};


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
const showEditUsernameDialog = ref(false);
const showEditPasswordDialog = ref(false);


const showJoinClassDialog = ref(false);
const classToJoinId = ref(null);

const logout = () => {
  router.push('/')

}
const joinClass = async () => {
  console.log('in')
  console.log(classToJoinId)
  const t = Number(classToJoinId.value)
  classToJoinId.value = t
  if (classToJoinId.value && typeof classToJoinId.value === 'number') {
    try {
      const params = new URLSearchParams();
      params.append('userId', userId.value);
      params.append('classId', classToJoinId.value);

      const response = await axios.post(`http://localhost:8080/class/join`, params);

      if (response.status === 200) {
        classToJoinId.value = null;
        showJoinClassDialog.value = false;
        ElMessage.success('加入班级成功');
      } else {
        // 根据后端返回的错误消息显示错误
        ElMessage.error(response.data.message);
      }
    } catch (error) {
      ElMessage.error('加入班级失败');
    }
  }
};
const goToClassNotifications = () => {
  router.push('/studentdashboard/classnotifications');
};
const gotoFileManagement = () => {
  router.push('/studentdashboard/filemanagement')

};
const gotoMyChat = () => {
  router.push('/mychat')

};
const gotomyclass = () => {
  router.push('/studentdashboard/myclass')

}


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




