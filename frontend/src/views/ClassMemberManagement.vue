<template>
  <el-container>
    <el-main>
      <div class="class-member-management">
        <h1>班级成员管理</h1>
        <div class="member-list">
          <el-table :data="members" style="width: 100%" border>
            <el-table-column prop="userId" label="成员ID" width="180"></el-table-column>
            <el-table-column prop="username" label="姓名" width="180"></el-table-column>
            <el-table-column label="操作" width="180">
              <template #default="{ row }">
                <el-button type="danger" size="small" @click="removeClassMember(row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import axios from "axios";
import {useRoute} from "vue-router"

export default {
  data() {
    return {
      members: [],
      classId: 1
    };
  },
  async created() {
    const route = useRoute();
    this.classId = route.params.classId
    await this.fetchMembers();
  },
  methods: {
    async fetchMembers() {
      try {
        const response = await axios.get(`http://localhost:8080/class/${this.classId}/members`);
        this.members = response.data;
        for (let i = 0; i < this.members.length; i++){
          let c = this.members[i];
          console.log(c.userId)
          const initiatorName = await axios.get(`http://localhost:8080/api/users/${c.userId}`);
          console.log(initiatorName)
          c.username = initiatorName.data.username
          this.members[i] = c;
        }
        console.log(this.members)

      } catch (error) {
        console.error("获取班级成员失败", error);
      }
    },
    async removeClassMember(row) {
      try {
        const response = await axios.delete(`http://localhost:8080/class/${this.classId}/member/${row.userId}`);
        if (response.status === 204) {
          this.fetchMembers();
        } else {
          console.error("删除班级成员失败");
        }
      } catch (error) {
        console.error("删除班级成员失败", error);
      }
    }
  }
};
</script>

<style scoped>
.class-member-management {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
}
</style>
