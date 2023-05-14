<template>
  <div>
    <el-table :data="users" style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="username" label="Username" width="180"></el-table-column>
      <el-table-column prop="password" label="Password" width="180"></el-table-column>
      <el-table-column prop="userType" label="User Type" width="180"></el-table-column>
      <el-table-column
          fixed="right"
          label="Operations"
          width="120">
        <template #default="scope">
          <el-button
              type="text"
              size="small"
              @click="handleEdit(scope.$index, scope.row)">Edit
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
          <el-input v-model="form.userType" auto-complete="off"></el-input>
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
