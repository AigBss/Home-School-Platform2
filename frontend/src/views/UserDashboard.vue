<template>
  <div class="dashboard">
    <h1>Welcome, {{ user.nickname }}!</h1>
    <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
      <!-- 根据 user.userType 动态生成导航菜单 -->
      <el-submenu v-if="user.userType === 1" index="1">
        <template #title>Admin</template>
        <el-menu-item index="1-1">Admin Option 1</el-menu-item>
        <el-menu-item index="1-2">Admin Option 2</el-menu-item>
      </el-submenu>
      <el-submenu v-if="user.userType === 2" index="2">
        <template #title>Teacher</template>
        <el-menu-item index="2-1">Teacher Option 1</el-menu-item>
        <el-menu-item index="2-2">Teacher Option 2</el-menu-item>
      </el-submenu>
      <el-submenu v-if="user.userType === 3" index="3">
        <template #title>Student</template>
        <el-menu-item index="3-1">Student Option 1</el-menu-item>
        <el-menu-item index="3-2">Student Option 2</el-menu-item>
      </el-submenu>
      <el-menu-item index="4" @click="viewProfile">Profile</el-menu-item>
    </el-menu>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        userType: 1, // 示例数据，请使用实际数据替换
        nickname: "John Doe",
      },
      activeIndex: "1",
    };
  },
  methods: {
    handleSelect(key) {
      console.log(key);
    },
    viewProfile() {
      this.$router.push("/profile");
    },
  },
};
</script>

<style scoped>
.dashboard {
  text-align: center;
}
.content {
  margin-top: 20px;
}
</style>
