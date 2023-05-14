<template>
  <div>
    <el-input v-model="newMessage" placeholder="Type a message" @keyup.enter="postMessage"></el-input>
    <el-button type="primary" @click="postMessage">Send</el-button>
    <el-table :data="messages" style="width: 100%">
      <el-table-column prop="content" label="Message"></el-table-column>
      <el-table-column prop="username" label="User Name"></el-table-column>
      <el-table-column label="Operations">
        <template #default="{ row }">
          <el-button v-if="userId === row.userId" type="danger" @click="deleteMessage(row.id)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'
import {useRoute} from "vue-router"

export default {
  setup() {
    const username = ref();
    const messages = ref([])
    const newMessage = ref('')
    const userId = ref(null) // You should get the current user's ID from your authentication system
    const groupId = ref(null) // You should get the group ID from the URL or some other source
    const route = useRoute();

    const userString = localStorage.getItem('user');
    if (userString) {
      const user = JSON.parse(userString);
      if (user && user.username) {
        username.value = user.username;
        userId.value = user.id;
      }
    }
    groupId.value = route.params.groupChatId;

    const fetchMessages = async () => {
      const response = await axios.get(`http://localhost:8080/group-chat/${groupId.value}/messages`)
      for (let i = 0; i < response.data.length; i++){
        let gc = response.data[i];
        const user = await axios.get(`http://localhost:8080/api/users/${gc.senderId}`);
        gc.username = user.data.username
        response.data[i] = gc;
      }
      messages.value = response.data
    }

    const postMessage = async () => {
      const message = { content: newMessage.value, senderId: userId.value, groupId: groupId.value}
      const response = await axios.post('http://localhost:8080/group-chat/message', message)
      if (response.status === 200) {
        fetchMessages()
      }
      newMessage.value = ''
    }

    const deleteMessage = async (id) => {
      const response = await axios.delete(`http://localhost:8080/group-chat/message/${id}`)
      if (response.status === 204) {
        fetchMessages()
      }
    }

    // Fetch the messages when the component is created
    fetchMessages()

    return {
      messages,
      newMessage,
      postMessage,
      deleteMessage
    }
  }
}
</script>

<style scoped>
h2 {
  margin-bottom: 20px;
}

.message-input {
  display: flex;
  margin-bottom: 20px;
}

.message-input .el-input {
  flex: 1;
  margin-right: 10px;
}
</style>