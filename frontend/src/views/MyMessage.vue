<template>
  <div class="mymessage">
    <div class="messages">
      <div v-for="(message, index) in messages" :key="index">
        <p><strong>{{ message.username }}:</strong> {{ message.content }}</p>
      </div>
    </div>
    <div class="send-message">

      <el-input v-model="newMessageText" placeholder="输入新的消息" type="textarea"></el-input>
      <el-button type="primary" @click="sendMessage">发送</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const newMessageText = ref('');
const messages = ref([]);
const route = useRoute();
let chatId;
let timer;

onMounted(() => {
  chatId = route.params.chatId;
  loadMessages();
  timer = setInterval(loadMessages, 5000);
});

onBeforeUnmount(() => {
  clearInterval(timer);
});

const loadMessages = async () => {
  const response = await axios.get(`http://localhost:8080/api/message/chat/${chatId}`);
  messages.value = response.data;
};

const sendMessage = async () => {
  const userString = localStorage.getItem('user');
  let senderId;
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.id) {
      senderId = user.id;
    }
  }
  await axios.post('http://localhost:8080/api/message/send', {
    'content': newMessageText.value,
    'chatId': chatId,
    'senderId': senderId,
  });
  newMessageText.value = '';
  loadMessages();
};

</script>

<style scoped>
.mymessage {
  padding: 20px;
}

.messages {
  height: 80vh;
  overflow-y: auto;
  margin-bottom: 20px;
}

.send-message {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
