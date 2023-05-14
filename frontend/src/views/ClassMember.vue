<template>
  <div>
    <el-table :data="members" style="width: 100%">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="Name"></el-table-column>
      <el-table-column prop="role" label="Role"></el-table-column>
      <el-table-column label="Operations">
        <template #default="{ row }">
          <el-button v-if="userId === creatorId && userId !== row.id" type="danger" @click="kickMember(row.id)">Kick Out</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'

export default {
  setup() {
    const members = ref([])
    const userId = ref(null) // You should get the current user's ID from your authentication system
    const creatorId = ref(null) // You should get the class creator's ID from your backend

    const fetchMembers = async () => {
      const response = await axios.get('/class/members')
      members.value = response.data
    }

    const kickMember = async (id) => {
      const response = await axios.delete(`/class/member/${id}?creatorId=${userId.value}`)
      if (response.status === 204) {
        fetchMembers()
      }
    }

    // Fetch the members when the component is created
    fetchMembers()

    return {
      members,
      kickMember
    }
  }
}
</script>
<style scoped>
h2 {
  margin-bottom: 20px;
}
</style>