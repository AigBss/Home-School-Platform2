<template>
  <el-container>
    <el-main>
      <div class="file-management">
        <div class="file-management-toolbar">
          <input type="file" ref="fileInput" @change="handleFileUpload" style="display:none"/>
          <el-button type="primary" @click="uploadFile">上传文件</el-button>
        </div>
        <el-table :data="fileList" stripe border>
          <el-table-column prop="filename" label="文件名" width="300"></el-table-column>
          <el-table-column prop="userid" label="上传者ID" width="200"></el-table-column>
          <el-table-column label="操作" width="150">
            <template #default="scope">
              <el-button type="text" @click="downloadFile(scope.row)">下载</el-button>
              <el-button
                  type="text"
                  @click="deleteFile(scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import axios from 'axios';

const fileList = ref([]);
const fileInput = ref(null);

const getAllFiles = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/files/all');
    fileList.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

onMounted(() => {
  getAllFiles();
});

const uploadFile = () => {
  fileInput.value.click();
};

const handleFileUpload = async (event) => {
  const file = event.target.files[0];
  if (!file) return;

  const formData = new FormData();
  formData.append('file', file);
  formData.append('userId', '1'); // Replace with the actual user ID

  try {
    await axios.post('http://localhost:8080/api/files/upload', formData);
    getAllFiles();
  } catch (error) {
    console.error(error);
  }
};

const downloadFile = (file) => {
  window.open(`http://localhost:8080/api/files/${file.id}`, '_blank');
};

const deleteFile = async (file) => {
  try {
    await axios.delete(`http://localhost:8080/api/files/${file.id}`, {params: {userId: '1'}}); // Replace with the actual user ID
    getAllFiles();
  } catch (error) {
    console.error(error);
  }
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
