<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-row>
          <el-col :span="24">
            <AdBoard></AdBoard>
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :xs="{span:24,offset:0}"
            :sm="{span:24,offset:0}"
            :md="{span:22,offset:1}"
            :lg="{span:20,offset:2}"
            :xl="{span:18,offset:3}"
          >
            <news-board></news-board>
          </el-col>
        </el-row>
        <el-divider></el-divider>
        <ButtonBoard></ButtonBoard>
        <el-divider></el-divider>
        <el-row>
          <el-col
            :xs="{span:24,offset:0}"
            :sm="{span:24,offset:0}"
            :md="{span:22,offset:1}"
            :lg="{span:20,offset:2}"
            :xl="{span:18,offset:3}"
          >
            <MajorFunction></MajorFunction>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import { getArticles } from "@/api/article";
import { mapState } from "vuex";
import 'element-ui/lib/theme-chalk/display.css';
export default {
  name: "Home",
  data() {
    return {
      pageInfo: {},
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
    };
  },
  computed: mapState(["blogInfo"]),
  components: {
    //ArticleItem: () => import("@/components/ArticleItem.vue"),
    MajorFunction: () => import("@/components/MajorFunction.vue"),
    NewsBoard: () => import("@/components/NewsBoard.vue"),
    AdBoard: () => import("@/components/AdBoard"),
    ButtonBoard: () => import("@/components/ButtonBoard"),
  },
  created() {
    this.getPageArticles(1, 5);
  },
  methods: {
    handleCurrentChange(page) {
      this.getPageArticles(page, 5);
    },
    getPageArticles(page, limit) {
      getArticles(page, limit)
        .then((res) => {
          if (res.code === 0) {
            this.pageInfo = res.data;
          } else {
            this.$notify.error({
              title: "提示",
              message: res.msg,
            });
          }
        })
        .catch((err) => {
          console.log(err);
          this.$notify.error({
            title: "提示",
            message: "网络忙，文章获取失败",
          });
        });
    },
  },
};
</script>
<style scoped>
.none-article {
  margin-top: 20px;
}

.item-box {
  position: relative;
  width: 100%;
  height: 100%;
}

.carimg {
  width: 100%;
  height: 100%;
  overflow: hidden;
  object-fit: cover;
}

.desc-box {
  position: absolute;
  bottom: 0;
  left: 50%;
  top: 30%;
  width: 800px;
  height: 40px;
  margin-left: -400px;
  text-align: center;
}

.desc-box h1 {
  font-size: 40px;
  color: #ffffff;
  font-weight: normal;
}

.desc-box p {
  font-size: 25px;
  margin-top: 30px;
  color: #ffffff;
}

.el-pagination {
  margin: 20px 0;
  text-align: center;
}
</style>
