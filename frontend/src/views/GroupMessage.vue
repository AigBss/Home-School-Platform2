<template>

  <div class="mymessage">
    <div class="messages">
      <div v-for="(message, index) in messages" :key="index">
        <div style="text-align: center"> {{ message.createdAtParsed }}</div>
        <p><strong>{{ message.username }}:</strong> {{ message.content }} </p>

      </div>
    </div>
    <div class="send-message">

      <el-input v-model="newMessage" placeholder="输入新的消息" type="textarea"></el-input>

    </div>
    <div style="float: right; margin-top: 10px">
      <el-button type="primary" @click="postMessage">发送</el-button>
    </div>
  </div>
</template>

<script>
import {ref} from 'vue'
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
      for (let i = 0; i < response.data.length; i++) {
        let gc = response.data[i];
        const user = await axios.get(`http://localhost:8080/api/users/${gc.senderId}`);
        gc.username = user.data.username
        let date = new Date(gc.createdAt);
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? ('0' + MM) : MM;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let m = date.getMinutes();
        m = m < 10 ? ('0' + m) : m;
        let s = date.getSeconds();
        s = s < 10 ? ('0' + s) : s;
        let re = y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
        gc.createdAtParsed = re;
        response.data[i] = gc;
      }
      messages.value = response.data
    }

    const postMessage = async () => {
      const message = {content: newMessage.value, senderId: userId.value, groupId: groupId.value}
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
      deleteMessage,
      // parseTime,
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

.mymessage {
  padding: 20px;
}

.messages {
  height: 82vh;
  overflow-y: auto;
  margin-bottom: 20px;
}

.send-message {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

</style>