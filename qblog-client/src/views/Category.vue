<template>
  <el-row>
    <el-row
      :gutter="0"
      style="height: 800px"
    >
      <el-col
        :span="14"
        :offset="5"
      >
        <router-link
          :to="'/category/' + category.name"
          :class="category.name === $route.params.name ? 'category-item category-selected' : 'category-item'"
          v-for="(category,index) in categories"
          :key="index"
        >
          {{category.name}}
          <el-tag
            type="success"
            size="small"
            effect="dark"
          >{{category.count}}</el-tag>
        </router-link>
        <article-item
          v-for="article in pageInfo.records"
          :key="article.id"
          :article="article"
        ></article-item>
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page.sync="pageInfo.current"
          :page-size="pageInfo.size"
          layout="prev, pager, next, jumper"
          :total="pageInfo.total"
          :hide-on-single-page="true"
        ></el-pagination>
      </el-col>
    </el-row>
  </el-row>
</template>

<script>
import { getArticles, getAllCategories, getArticlesByCategory} from "@/api/article";

export default {
  name: "Category",
  data() {
    return {
      pageInfo: [],
      categories: [],
    };
  },
  components: {
    ArticleItem: () => import("@/components/ArticleItem.vue"),
  },
  methods: {
    getArticlesInCategoryView(page, limit) {
      const category = this.$route.params.name;
      if (category === ":name") {
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
      } else {
        getArticlesByCategory(category, page, limit)
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
      }
    },
    loadData() {
      getAllCategories()
        .then((res) => {
          if (res.code === 0) {
            this.categories = res.data;
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
            message: "网络忙，分类获取失败",
          });
        });
      this.getArticlesInCategoryView(1, 5);
    },
    handleCurrentChange(page) {
      this.getArticlesInCategoryView(page, 5);
    },
  },
  beforeRouteUpdate(to, from, next) {
    next();
    console.log(to);
    console.log(from);
    this.loadData();
  },
  created() {
    this.loadData();
  },
};
</script>

<style scoped>
.category-item {
  display: inline-block;
  width: 20%;
  text-decoration: none;
  font-size: 14px;
  color: #606266;
  margin-top: 10px;
  margin-right: 10px;
  padding: 8px;
  transition: all 0.5s;
  border-radius: 5px;
  border: 1px solid #ebeef5;
}
.category-item:hover {
  box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 8px 0px;
  cursor: pointer;
  transform: scale(1.03);
  color: #303133;
}
.category-selected {
  box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 8px 0px;
  cursor: pointer;
  transform: scale(1.03);
  color: #303133;
}
.category-item .el-tag {
  float: right;
}
.el-pagination {
  margin: 20px 0;
  text-align: center;
}
</style>
