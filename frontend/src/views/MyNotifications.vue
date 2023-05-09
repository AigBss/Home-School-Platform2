<template>
  <el-container>
    <el-main>
      <div class="my-notifications">
        <h1>我的通知</h1>
        <el-table :data="notifications" style="width: 100%">
          <el-table-column prop="id" label="通知ID" width="180"></el-table-column>
          <el-table-column prop="title" label="通知标题" width="180"></el-table-column>
          <el-table-column prop="content" label="通知内容"></el-table-column>
        </el-table>
      </div>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import axios from 'axios';

const notifications = ref([]);

onMounted(async () => {
  const userString = localStorage.getItem('user');
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.id) {
      const userId = user.id;
      try {
        const response = await axios.get(`http://localhost:8080/api/notifications/user/${userId}`);
        if (response.status === 200) {
          notifications.value = response.data;
        }
      } catch (error) {
        console.error('获取通知失败:', error);
      }
    }
  }
});
</script>

<style scoped>
.my-notifications {
  padding: 20px;
}
</style>
