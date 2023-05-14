<template>
  <div class="chat">
    <div class="chat-sidebar">
      <p>当前用户：{{ username }}</p>
      <el-button type="primary" @click="showCreateChatDialog = true">发起聊天</el-button>
      <el-dialog title="发起聊天" v-model="showCreateChatDialog">
        <el-input v-model="recipientId" placeholder="请输入对方ID"></el-input>
        <template v-slot:footer>
          <el-button @click="showCreateChatDialog = false">取消</el-button>
          <el-button type="primary" @click="startChat">创建</el-button>
        </template>
      </el-dialog>
      <el-list>
        <el-list-item v-for="(chat, index) in chats" :key="index">
          <el-row @click="goToChat(chat.id)">
            <el-col :span="20">
              <span>{{ chat.initiatorName }} 与 {{ chat.recipientName }} 的聊天</span>
            </el-col>
            <el-col :span="4">
              <el-button @click="endChat(chat.id)">删除</el-button>
            </el-col>
          </el-row>
        </el-list-item>
      </el-list>
    </div>
<!--    <div class="chat-content">-->
<!--      <div class="messages">-->
<!--        <div v-for="(message, index) in messages" :key="index">-->
<!--          <p><strong>{{ message.senderUsername }}:</strong> {{ message.text }}</p>-->
<!--        </div>-->
<!--      </div>-->
<!--      <div class="send-message">-->
<!--        <el-input v-model="newMessageText" placeholder="输入新的消息"></el-input>-->
<!--        <el-button type="primary" @click="sendMessage">发送</el-button>-->
<!--      </div>-->
<!--    </div>-->
    <el-main>
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';

const username = ref('');
const userId = ref(null);

onMounted(() => {
  const userString = localStorage.getItem('user');
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.username) {
      username.value = user.username;
      userId.value = user.id;
    }
  }
  console.log(userId)

  loadChats();
  // router.push("")
  // chatId = route.params.chatId;
  // loadMessages();
  // timer = setInterval(loadMessages, 5000);
});

const chats = ref([]);
const showCreateChatDialog = ref(false);
const recipientId = ref('');
const router = useRouter();


// const loadChats = async () => {
//   const response = await axios.get('http://localhost:8080/api/chat/userChats', {
//     params: { userId: userId.value },
//   });
//   chats.value = response.data;
// };
// loadChats();

const loadChats = async () => {
  const response = await axios.get(`http://localhost:8080/api/chat/user/${userId.value}`);
  chats.value = response.data;
  for (let i = 0; i < chats.value.length; i++){
    let c = chats.value[i];
    console.log(c.initiatorId, c.recipientId)
    const initiatorName = await axios.get(`http://localhost:8080/api/users/${c.initiatorId}`);
    const recipientName = await axios.get(`http://localhost:8080/api/users/${c.recipientId}`);
    console.log(initiatorName, recipientName)
    c.initiatorName = initiatorName.data.username
    c.recipientName = recipientName.data.username
    chats.value[i] = c;
  }
  console.log(chats.value)
};



const startChat = async () => {
  try {
    const userString = localStorage.getItem('user');
    let initiatorId;
    if (userString) {
      const user = JSON.parse(userString);
      if (user && user.id) {
        initiatorId = user.id;
      }
    }await axios.post('http://localhost:8080/api/chat/start',  {}, {
      params: {
        initiatorId,
        recipientId: recipientId.value,
      },
    });
    showCreateChatDialog.value = false;
    router.go(0);
    // loadChats();
  } catch (error) {
    console.error(error);
  }
};

const endChat = async (chatId) => {
  try {
    await axios.delete(`http://localhost:8080/api/chat/end/${chatId}`);
    await router.push(`/mychat`);
    router.go(0);
    // loadChats();
  } catch (error) {
    console.error(error);
  }
};
const goToChat = (chatId) => {
  router.push(`/mychat/mymessage/${chatId}`);
  // console.log(router.getRoutes())
  // router.push(`/mychat/mymessage/1`);
};

const newMessageText = ref('');
const messages = ref([]);
const route = useRouter();
let chatId;
let timer;

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
    text: newMessageText.value,
    chatId,
    senderId,
  });
  newMessageText.value = '';
  loadMessages();
};

// You can add other methods or computed properties as needed.

</script>
<style scoped>
.chat {
  display: flex;
  height: 100vh;
}

.chat-sidebar {
  background-color: #f5f7fa;
  width: 250px;
  padding: 20px;
  border-right: 1px solid #e4e7ed;
  height: 100%;
  overflow-y: auto;
}

.el-list {
  margin-top: 20px;
}

.el-list-item {
  margin-bottom: 15px;
  cursor: pointer;
}
</style>