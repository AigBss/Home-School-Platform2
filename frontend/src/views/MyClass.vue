<template>
  <div>
    <el-table :data="classes" style="width: 100%">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="Name">
        <template #default="{ row }">
          <el-link  @click="gotoGroupChat(row.id)">{{ row.name }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="creatorId" label="Creator ID"></el-table-column>
      <el-table-column label="Operations">
        <template #default="{ row }">
          <el-button v-if="userId === row.creatorId" type="danger" @click="deleteClass(row.id)">Delete</el-button>
          <el-button v-if="userId === row.creatorId" @click="manageClass(row.id)">Manage</el-button>
          <el-button v-if="userId !== row.creatorId" @click="leaveClass(row.id)">Leave</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from "vue-router";

export default {

  mounted(){
    const userString = localStorage.getItem('user');
    if (userString) {
      const user = JSON.parse(userString);
      if (user && user.username) {
        this.username = user.username;
        this.userId = user.id;
      }
    }
    this.fetchClasses(undefined, undefined)


  },
  setup() {
    const classes = ref([])
    const userId = ref(null)
    const username = ref(null)
    const router = useRouter();

    const fetchClasses = async () => {
      const response = await axios.get('http://localhost:8080/class/all')
      classes.value = response.data
    }

    const deleteClass = async (id) => {
      const response = await axios.delete(`http://localhost:8080/class/${id}?creatorId=${userId.value}`)
      if (response.status === 204) {
        fetchClasses()
      }
    }

    const manageClass = (id) => {
      window.location.href = `/class/manage/${id}`
    }

    const leaveClass = async (id) => {
      const response = await axios.delete(`http://localhost:8080/class/${id}/member/${userId.value}`)
      if (response.status === 204) {
        fetchClasses()
      }
    }

    const gotoGroupChat = async (id) => {
      const response = await axios.get(`http://localhost:8080/group-chat/findGroupChatIdByClassId/${id}`)
      if (response.status === 200) {
        window.location.href = `/chat/${response.data}`
        // router.push()
      }
    }


    return {
      classes,
      userId,
      username,
      deleteClass,
      manageClass,
      leaveClass,
      fetchClasses,
      gotoGroupChat,
    }
  }

}
</script>
<style scoped>
h2 {
  margin-bottom: 20px;
}
</style>