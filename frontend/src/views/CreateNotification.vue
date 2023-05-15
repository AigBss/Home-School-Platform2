<template>
  <el-container>
    <el-main>
      <div class="layout-main">
        <h1>发布通知</h1>

        <el-form  :model="publishNotificationForm" label-width="120px" style="margin-right: 200px">
          <el-form-item label="通知标题">
            <el-input v-model="publishNotificationForm.title"></el-input>
          </el-form-item>
          <el-form-item label="通知内容">
            <el-input v-model="publishNotificationForm.content" type="textarea" autosize></el-input>
          </el-form-item>
          <el-form-item label="班级ID">
            <el-input v-model="publishNotificationForm.classId" type="number"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="publishNotification">发布通知</el-button>
            <el-button @click="$router.go(-1)">返回</el-button>
          </el-form-item>
        </el-form>

        <el-dialog title="确认发布通知" v-model="confirmPublishDialogVisible">
          <p>您确定要发布这条通知吗？</p>
          <template #footer>
            <el-button @click="confirmPublishDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="submitPublishNotification">确认</el-button>
          </template>
        </el-dialog>
      </div>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import {ElMessage, ElInput} from 'element-plus';
import axios from 'axios';
import {ref} from 'vue';

import { reactive } from 'vue'
const publishNotificationForm = reactive({
  title: '',
  content: '',
  classId: null,
});
const confirmPublishDialogVisible = ref(false);

const publishNotification = () => {
  if (
      publishNotificationForm.title &&
      publishNotificationForm.content &&
      publishNotificationForm.classId
  ) {
    confirmPublishDialogVisible.value = true;
  } else {
    ElMessage.error('请填写完整的通知信息');
  }
};

const submitPublishNotification = async () => {
  const userString = localStorage.getItem('user');
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.id) {
      const notification = {
        title: publishNotificationForm.title,
        content: publishNotificationForm.content,
        classId: publishNotificationForm.classId,
        userId: user.id,
      };

      try {
        const response = await axios.post('http://localhost:8080/api/notifications', notification);
        if (response.status === 200) {
          ElMessage.success('通知发布成功');
          confirmPublishDialogVisible.value = false;
          publishNotificationForm.title = '';
          publishNotificationForm.content = '';
          publishNotificationForm.classId = null;
        } else {
          ElMessage.error(response.data.message);
        }
      } catch (error) {
        ElMessage.error('发布通知失败');
      }
    }
  }
};
</script>

<style scoped>
.layout-main {
  margin-left: 150px;
  margin-top: 50px;
}


</style>
