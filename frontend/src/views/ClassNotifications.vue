<template>
  <el-container>
    <el-main>
      <div class="class-notifications">
        <h1>班级通知</h1>
        <el-button type="primary" @click="goBack">返回</el-button>
        <el-table :data="classNotifications" style="width: 100%; margin-top: 20px;">
          <el-table-column prop="classId" label="班级ID" width="120"></el-table-column>
          <el-table-column prop="title" label="通知标题" width="180"></el-table-column>
          <el-table-column prop="content" label="通知内容"></el-table-column>
          <el-table-column prop="userId" label="创建者ID" width="120"></el-table-column>
        </el-table>
      </div>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import axios from 'axios';
import {useRouter} from 'vue-router';

const router = useRouter();
const classNotifications = ref([]);

onMounted(async () => {
  const userString = localStorage.getItem('user');
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.id) {
      const userId = user.id;
      try {
        const classResponse = await axios.get(`http://localhost:8080/member/${userId}`);
        const classIds = classResponse.data.map(cls => cls.id);
        for (const classId of classIds) {
          const notificationResponse = await axios.get(`http://localhost:8080/api/notifications/class/${classId}`);
          classNotifications.value.push(...notificationResponse.data);
        }
      } catch (error) {
        console.error('获取班级通知失败:', error);
      }
    }
  }
});

const goBack = () => {
  router.go(-1);
};
</script>

<style scoped>
.class-notifications {
  padding: 20px;
}
</style>
