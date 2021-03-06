import VueRouter from "vue-router";
import Vue from 'vue';

import Layout from '@/layout/index'

Vue.use(VueRouter);

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: "/",
    name: "Home",
    redirect: '/home',
    component: Layout,
    meta: { title: "首页" },
    children: [{
      path: '/home',
      component: () => import("@/views/Home.vue")
    }],
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/Login.vue"),
    meta: { title: "登录注册" },
    hidden: true
  },
  {
    path: '/education',
    name: 'Education',
    meta: { title: "心理教育" },
    component: Layout,
    children: [
      {
        path: "/category/:name",
        name: "Category",
        component: () => import("@/views/Category.vue"),
        meta: { title: "分类浏览" },
      },
      {
        path: "/tag/:name",
        name: "Tag",
        component: () => import("@/views/Tag.vue"),
        meta: { title: "标签浏览" },
      },
    ]
  },
  {
    path: "/article",
    redirect: '/article/page',
    component: Layout,
    hidden: true,
    children: [{
      path: 'page/:id',
      name: 'Article',
      component: () => import("@/views/Article.vue"),
      meta: { title: "文章详情" },
    }],
  },
  {
    path: "/quiz",
    name: "Quiz",
    redirect: '/quiz',
    component: Layout,
    meta: { title: "心理测量问卷" },
    children: [{
      path: '/quiz',
      component: () => import("@/views/Quiz.vue"),
      redirect: "/quiz/quiz1",
      children: [
        { path: "quiz1", component: () => import("@/views/quiz/Quiz1.vue") },
        { path: "quiz2", component: () => import("@/views/quiz/Quiz2.vue") },
        { path: "quiz3", component: () => import("@/views/quiz/Quiz3.vue") },
        { path: "quiz4", component: () => import("@/views/quiz/Quiz4.vue") },
      ]
    }],
  },
  {
    path: "/order",
    name: "Order",
    component: Layout,
    meta: { title: "心理咨询预约" },
    children: [
      {
        path: '/order',
        redirect: '/order/doctorlist',
        component: () => import("@/views/Order.vue"),
        children: [
          {
            path: "doctorlist",
            name: 'DocList',
            component: () => import("@/views/order/DoctorList.vue")
          },
          {
            name: "ordertable",
            path: "ordertable/:doctorId",
            component: () => import("@/views/order/OrderTable.vue")
          },
          // {
          //   path: "orderform",
          //   component: () => import("@/views/order/OrderForm.vue")
          // },
        ]
      }],
  },
  {
    path: '/course',
    name: 'Course',
    component: Layout,
    redirect: '/course/courseList',
    meta: { title: '心理课程' },
    children: [
      {
        path: 'courseList',
        name: 'CourseList',
        component: () => import('@/views/course/courseList'),
        meta: { title: '课程培训', icon: 'el-icon-s-management' }
      },
      {
        path: 'coursePage/:courseId',
        name: 'CoursePage',
        component: () => import('@/views/course/coursePage'),
        meta: { title: '课程培训', noCache: true },
        hidden: true
      }
    ]

  },
  {
    path: "/info",
    name: "Info",
    component: Layout,
    hidden: true,
    meta: { title: "个人中心" },
    children: [{
      path: '/info',
      component: () => import("@/views/info/index.vue"),
    }],
  },
]

const router = new VueRouter({
  mode: "history",
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});

export default router;
