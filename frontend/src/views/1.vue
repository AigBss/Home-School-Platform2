<template>
  <el-container>
    <div style="height: 60px; line-height: 50px; border-bottom: 1px solid  rgb(48,65,86); background-color: aliceblue">
      <div style="display: flex;height: 59px">
        <div style="width: 180px; background-color: rgb(48,65,86);color: white; font-weight: bold; padding-left: 20px; font-size: 20px">

          <img   src="../../assets/logo.png"  style="width: 20px; position: relative;top:5px;margin-right: 5px" /><b style="color:white">可视化工厂</b>
        </div>
        <div style="flex: 1; display: flex;background-color: white" @mouseout="leave">
          <div style="flex: 1">

          </div>
          <el-descriptions title="用户1" :column="3" border v-show="seen" >
            <el-descriptions-item
                label="Username"
                label-align="right"
                align="center"
                label-class-name="my-label"
                class-name="my-content"
                width="150px"
            >kooriookami</el-descriptions-item
            >
            <el-descriptions-item label="Telephone" label-align="right" align="center"
            >18100000000</el-descriptions-item
            >
            <el-descriptions-item label="Place" label-align="right" align="center"
            >Suzhou</el-descriptions-item
            >
            <el-descriptions-item label="Remarks" label-align="right" align="center">
              <el-tag size="small">School</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="Address" label-align="right" align="center"
            >No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu
              Province</el-descriptions-item
            >
          </el-descriptions>
          <div style="width: 30px; text-align: right; padding-right: 20px">
            <el-avatar @mouseenter="enter" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </div>
        </div>
      </div>
    </div>
    <el-container >
      <el-aside width="200px" style="background-color: rgb(48,65,86)">
        <el-menu style="background-color: rgb(48,65,86)" text-color="#fff">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><Location /></el-icon>
              <span><font size="4px" color="white">工厂</font></span>
            </template>
            <el-menu-item index="1-1">
              <router-link to="/factoryintroduction">工厂介绍</router-link>
            </el-menu-item>
            <el-menu-item index="1-2">
              <span><router-link to="/bdmap">工厂地图</router-link></span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="2" text-color="#fff">
            <template #title>
              <el-icon><tools/></el-icon>
              <span><font size="4px" color="white" >设备</font></span>
            </template>
            <el-menu-item index="2-1" >
              <span ><router-link to="/equipmentechart" >可视化呈现</router-link></span>
            </el-menu-item>
            <el-menu-item index="2-3">
              <span><router-link to="/equipment">设备管理</router-link></span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><management /></el-icon>
              <span><font size="4px" color="white">订单</font></span>
            </template>
            <el-menu-item index="3-1">
              <router-link to="/mydept">订单统计</router-link>
            </el-menu-item>
            <el-menu-item index="3-2">
              <span><router-link to="/ordermanage">订单管理</router-link></span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu  index="4">
            <template #title>
              <el-icon><DeleteFilled /></el-icon>
              <span><router-link to="/myshop"><font size="4px" color="white">排污</font></router-link></span>
            </template>
            <el-menu-item index="4-1">
              <router-link to="/mydept">污染预警</router-link>
            </el-menu-item>
            <el-menu-item index="4-2">
              <span><router-link to="/ordermanage">污染管理</router-link></span>
            </el-menu-item>
          </el-sub-menu >
          <el-sub-menu  index="5">
            <template #title>
              <el-icon><Box /></el-icon>
              <span><router-link to="/myshop"><font size="4px" color="white">库存</font></router-link></span>
            </template>
            <el-menu-item index="5-1">
              <router-link to="/ingredient">原料管理</router-link>
            </el-menu-item>
            <el-menu-item index="5-2">
              <span><router-link to="/ordermanage">产品管理</router-link></span>
            </el-menu-item>
          </el-sub-menu >

        </el-menu>
      </el-aside>
      <el-main class="main">
        <router-view></router-view>
      </el-main>
    </el-container>
    <el-footer class="el-footer"></el-footer>
  </el-container>
</template>

<script>

export default {
  name: "MyMain",

  data(){
    return{
      seen:false,
    }
  },
  methods:{
    enter(){
      this.seen = true
    },
    leave(){
      this.seen = false
    }
  }

}
</script>

<style scoped>

.el-header,
.el-footer {
  height: 60px;
  background-color: grey;
}

a {
  text-decoration: none;
}

.main_title {
  font-size: 22px;
  color: #fff;
  height: 60px;
  line-height: 60px;
}

.main {
  min-height: 790px;
}

.user_title {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.my-label {
  background: var(--el-color-success-light-9);
}
.my-content {
  background: var(--el-color-danger-light-9);
}
</style>
