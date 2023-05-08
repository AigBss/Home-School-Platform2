<template>
  <el-container>
    <el-main>
    <div class="grades">
    <h1>我的成绩</h1>
    <el-table :data="gradesList" style="width: 100%">
      <el-table-column prop="subject" label="科目" width="180"></el-table-column>
      <el-table-column prop="term" label="学期" width="180"></el-table-column>
      <el-table-column prop="score" label="成绩" width="120"></el-table-column>
    </el-table>
    </div>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';

const gradesList = ref([]);

onMounted(async () => {
  const userString = localStorage.getItem('user');
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.id) {
      try {
        const response = await axios.get(`http://localhost:8080/grades/user/${user.id}`);
        if (response.status === 200) {
          gradesList.value = response.data;
        }
      } catch (error) {
        console.error('获取成绩失败');
      }
    }
  }
});
</script>

<style scoped>
.grades {
  padding: 20px;
}
</style>
