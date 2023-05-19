<template>
  <div class="mymessage">
    <div class="messages">
      <div v-for="(message, index) in messages" :key="index">
        <p><strong>{{ message.username }}:</strong> {{ message.content }}</p>
      </div>
    </div>
    <div class="send-message">

      <el-input v-model="newMessageText" placeholder="输入新的消息" type="textarea"></el-input>

    </div>
    <div style="float: right; margin-top: 10px">
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
let webSocketObject: WebSocket;

onMounted(() => {
  chatId = route.params.chatId;
  loadMessages();
  timer = setInterval(loadMessages, 5000);

  webSocketInit() // 建立ws连接
});

onBeforeUnmount(() => {
  webSocketObject.close(); // 关闭ws连接
  clearInterval(timer);
});

const loadMessages = async () => {
  const response = await axios.get(`http://localhost:8080/api/message/chat/${chatId}`);

  function compare(property: string){
    return function(a,b){
      const value1 = a[property];
      const value2 = b[property];
      return value1 - value2;  //降序只需要  return value2- value1
    }
  }
  response.data.sort(compare('createdAt'))

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


const webSocketInit= async () => {
  const userString = localStorage.getItem('user');
  let senderId;
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.id) {
      senderId = user.id;
    }
  }
  const webSocketUrl = 'ws://localhost:8080/websocket/'+senderId
  webSocketObject = new WebSocket(webSocketUrl);
  webSocketObject.onopen = webSocketOnOpen
  webSocketObject.onmessage = webSocketOnMessage
  webSocketObject.onerror = webSocketOnError
  webSocketObject.onclose = webSocketOnClose
}
const webSocketOnOpen= async (e) => {
  console.log('与服务端连接打开->',e)
}
const webSocketOnMessage= async (e) => {
  console.log('来自服务端的消息->',e)
  if (e.data === "please reload") {
    loadMessages()
  }
}
const webSocketOnError= async (e) => {
  console.log('与服务端连接异常->',e)
}
const webSocketOnClose= async (e) => {
  console.log('与服务端连接关闭->',e)
}
const handleSendButton= async (e) =>  {
  const username = username
  const message = sendMessage
  webSocketObject.send(JSON.stringify({
    id: 1,
    message,
    username,
    time: new Date().getTime()
  }))
  this.sendMessage = ''
}

</script>

<style scoped>
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
