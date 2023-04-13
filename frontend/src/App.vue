<template>
  <div id="app">
    <el-menu mode="horizontal" @select="handleSelect">
      <el-menu-item index="/login">登录</el-menu-item>
      <!-- 在此处添加其他导航菜单项 -->
    </el-menu>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  methods: {
    handleSelect(index) {
      this.$router.push(index)
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
