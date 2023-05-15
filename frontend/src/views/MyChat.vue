<template>
  <div class="chat">
    <div class="chat-sidebar">
      <div>
        <p style="display: inline-block; margin-right: 10px;">当前用户：{{ username }}</p>
        <el-button style="float: right;  margin-top: 12px" type="primary" @click="showCreateChatDialog = true">发起聊天
        </el-button>
      </div>

      <div>
        <p>正在进行的会话：<br><br> {{ nowChatName }}</p>
      </div>

      <el-dialog title="发起聊天" v-model="showCreateChatDialog">
        <el-input v-model="recipientId" placeholder="请输入对方ID"></el-input>
        <template v-slot:footer>
          <el-button @click="showCreateChatDialog = false">取消</el-button>
          <el-button type="primary" @click="startChat">创建</el-button>
        </template>
      </el-dialog>

      <hr style="margin-top: 40px"/>
      <div style="margin-top: 20px">
        <el-list>
          <el-list-item v-for="(chat, index) in chats" :key="index">
            <el-row @click="goToChat(chat)" style="margin-top: 10px">
              <el-col :span="18" style="margin-top: 4px">
                <span>{{ chat.initiatorName }} 与 {{ chat.recipientName }} 的聊天</span>
              </el-col>
              <el-col :span="4">
                <el-button type="danger" @click="endChat(chat.id)">删除</el-button>
              </el-col>
            </el-row>
          </el-list-item>
        </el-list>
      </div>
      <div class="go-home-btn" style="margin-top: 100px">
        <hr/>
        <el-button type="primary" @click="goToHome">返回首页</el-button>
      </div>
    </div>

    <el-main>
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script lang="ts" setup>
import {ref, onMounted, onBeforeUnmount} from 'vue';
import axios from 'axios';
import {useRouter} from 'vue-router';
import {useRoute} from 'vue-router';

const username = ref('');
const nowChatName = ref('无');
const userId = ref(null);
const usertype = ref('')

onMounted(() => {
  const userString = localStorage.getItem('user');
  if (userString) {
    const user = JSON.parse(userString);
    if (user && user.username) {
      username.value = user.username;
      userId.value = user.id;
      usertype.value=user.userType;
    }
  }
  console.log(userId);
  console.log(usertype)

  loadChats();

});

const chats = ref([]);
const showCreateChatDialog = ref(false);
const recipientId = ref('');
const router = useRouter();


const loadChats = async () => {
  const response = await axios.get(`http://localhost:8080/api/chat/user/${userId.value}`);
  chats.value = response.data;
  for (let i = 0; i < chats.value.length; i++) {
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

const goToHome = () => {
  if (usertype.value == "3") {
     router.push("/studentdashboard/studentprofile");
  } else if (usertype.value == "2") {
     router.push("/teacherdashboard/teacherprofile");
  } else if (usertype.value == "1") {
    router.push("/usermanagement");
  } else {
    // 如果用户类型不存在，您可以选择重定向到其他页面或显示错误消息。
    console.error("Invalid userType.");
  }
  }


const startChat = async () => {
  try {
    const userString = localStorage.getItem('user');
    let initiatorId;
    if (userString) {
      const user = JSON.parse(userString);
      if (user && user.id) {
        initiatorId = user.id;
      }
    }
    await axios.post('http://localhost:8080/api/chat/start', {}, {
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
const goToChat = (chat) => {
  nowChatName.value = `${chat.initiatorName} 与 ${chat.recipientName} 的聊天`
  router.push(`/mychat/mymessage/${chat.id}`);
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
  width: 330px;
  padding: 20px;
  border-right: 1px solid #e4e7ed;
  /*height: 100%;*/
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