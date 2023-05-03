<template>
  <el-container>
    <el-aside v-if="isLoggedIn" width="200px">
      <app-sidebar />
    </el-aside>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<!--<script>-->
<!--import AppSidebar from "@/components/AppSidebar";-->

<!--export default {-->
<!--  components: {-->
<!--    AppSidebar,-->
<!--  },-->
<!--  computed: {-->
<!--    isLoggedIn() {-->
<!--      // 根据实际情况判断用户是否已登录-->
<!--      // 您可以使用 Vuex 状态管理库 或 其他方法来判断用户是否已登录-->
<!--      return !!localStorage.getItem('user');-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script>-->


<!--<style lang="scss">-->
<!--#app {-->
<!--  font-family: Avenir, Helvetica, Arial, sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  text-align: center;-->
<!--  color: #2c3e50;-->
<!--}-->

<!--nav {-->
<!--  padding: 30px;-->

<!--  a {-->
<!--    font-weight: bold;-->
<!--    color: #2c3e50;-->

<!--    &.router-link-exact-active {-->
<!--      color: #42b983;-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</style>-->
