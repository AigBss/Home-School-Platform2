<template>
  <div class="profile">
    <h1>Profile</h1>
    <el-form :model="user">
      <el-form-item label="Nickname">
        <el-input v-model="user.nickname" />
      </el-form-item>
      <el-form-item label="Age">
        <el-input v-model="user.age" />
      </el-form-item>
      <el-form-item label="Class">
        <el-input v-model="user.class" />
      </el-form-item>
      <el-form-item label="Email">
        <el-input v-model="user.email" />
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        nickname: "John Doe",
        age: 25,
        class: "3A",
        email: "john.doe@example.com",
      },
    };
  },
};
</script>

<style scoped>
.profile {
  width: 400px;
  margin: 0 auto;
}
</style>
