<template>
  <div class="file-management">
    <div class="file-management-toolbar">
      <el-button type="primary" @click="uploadFile">上传文件</el-button>
      <el-button type="info" @click="goBack">返回</el-button>
    </div>
    <el-table :data="fileList" stripe border>
      <el-table-column prop="filename" label="文件名" width="300"></el-table-column>
      <el-table-column prop="uploader" label="上传者" width="200"></el-table-column>
      <el-table-column prop="uploadTime" label="上传时间" width="200"></el-table-column>
      <el-table-column label="操作" width="150">
        <template #default="scope">
          <el-button type="text" @click="downloadFile(scope.row)">下载</el-button>
          <el-button
              type="text"
              v-if="scope.row.canDelete"
              @click="deleteFile(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="totalFiles"
        :page-size="pageSize"
        @current-change="handlePageChange"
    ></el-pagination>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import {useRouter} from 'vue-router';

import axios from 'axios';
import {ElMessage} from 'element-plus'


const router = useRouter();
const fileList = ref([]); // 示例数据，你需要替换为从后端获取的文件列表
const totalFiles = ref(100); // 示例数据，你需要替换为从后端获取的文件总数
const pageSize = ref(10); // 设置每页显示的文件数量
const currentPage = ref(1); // 当前页

const uploadFile = () => {
  // 上传文件操作
};

const goBack = () => {
  router.back();
};

const downloadFile = (file) => {
  // 下载文件操作
};

const deleteFile = (file) => {
  // 删除文件操作
};

const handlePageChange = (page) => {
  currentPage.value = page;
  // 获取对应页的文件列表
};
</script>

<style scoped>
.file-management {
  padding: 20px;
}

.file-management-toolbar {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}
</style>
