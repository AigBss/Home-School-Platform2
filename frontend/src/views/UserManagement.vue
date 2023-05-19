<template>
  <div style="margin-left: 30px">
    <el-table :data="users" style="width: 100% ">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="username" label="用户名" width="180"></el-table-column>
      <el-table-column prop="password" label="密码" width="180"></el-table-column>
      <el-table-column prop="userType" label="用户类型" width="180">
        <template #default="scope">
          {{ userTypeToRole(scope.row.userType) }}
        </template>
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="120">
        <template #default="scope">
          <el-button
              type="text"
              size="small"
              @click="handleEdit(scope.$index, scope.row)">修改
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" title="Edit user" :before-close="handleClose">
      <el-form :model="form">
        <el-form-item label="Username" :label-width="formLabelWidth">
          <el-input v-model="form.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" :label-width="formLabelWidth">
          <el-input v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="User Type" :label-width="formLabelWidth">
          <el-select v-model="form.userType" placeholder="please select your zone">
            <el-option label="管理员" value="1"></el-option>
            <el-option label="教师" value="2"></el-option>
            <el-option label="学生" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template v-slot:footer>
  <span class="dialog-footer">
    <el-button @click="dialogVisible = false">Cancel</el-button>
    <el-button type="primary" @click="submitEdit">Confirm</el-button>
  </span>
      </template>

    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      users: [],
      dialogVisible: false,
      form: {
        id: null,
        username: '',
        password: '',
        userType: null
      },
      formLabelWidth: '120px'
    }
  },
  mounted() {
    this.fetchUsers()
  },


  methods: {
    userTypeToRole(userType) {
      switch (userType) {
        case 1:
          return '管理员';
        case 2:
          return '教师';
        case 3:
          return '学生';
        default:
          return '未知';
      }
    },
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8080/api/users')
        this.users = response.data
      } catch (error) {
        console.error(error)
      }
    },
    handleEdit(index, row) {
      this.form.id = row.id
      this.form.username = row.username
      this.form.password = row.password
      this.form.userType = row.userType
      this.dialogVisible = true
    },
    handleClose() {
      this.dialogVisible = false
    },
    async submitEdit() {
      try {
        await axios.put(`http://localhost:8080/api/users/${this.form.id}/username`, this.form.username)
        await axios.put(`http://localhost:8080/api/users/${this.form.id}/password`, this.form.password)
        await axios.put(`http://localhost:8080/api/users/${this.form.id}/type`, this.form.userType)
        await this.fetchUsers()
        this.dialogVisible = false
      } catch (error) {
        console.error(error)
      }
    }
  }
}
</script>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  padding: 10px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-table {
  margin-top: 20px;
}

.el-button {
  margin-right: 10px;
}
</style>
