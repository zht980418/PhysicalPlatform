<template>
  <el-row>
    <el-backtop></el-backtop>
    <el-container style="border: 1px solid #eee">
      <el-aside
        width="200px"
        style="background-color: rgb(238, 241, 246)"
      >
        <el-menu router>
          <el-menu-item
            v-for="(item,i) in navList"
            :key="i"
            :index="item.name"
          >
            {{ item.navItem }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-row>

</template>

<script>
export default {
  components: {
  },
  data() {
    return {
      navList: [
        { name: '/quiz/quiz1', navItem: '青少年自我价值感量表' },
        { name: '/quiz/quiz2', navItem: 'PTSR压力测试表' },
        { name: '/quiz/quiz3', navItem: 'SAS自评表' },
        { name: '/quiz/quiz4', navItem: '心理测试4' },
      ]
    }
  }
};
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
