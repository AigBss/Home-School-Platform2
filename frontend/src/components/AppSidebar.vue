<template>
  <el-menu :default-openeds="['1']">
    <el-submenu index="1">
      <template #title><i class="el-icon-chat"></i>聊天</template>
      <el-menu-item index="1-1" @click="$router.push('/start-chat')">发起聊天</el-menu-item>
      <el-menu-item index="1-2" @click="$router.push('/my-chats')">我的聊天</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: 'AppSidebar', // 更新组件名称
  data() {
    return {
      isCollapse: false,
    };
  },
};
</script>
