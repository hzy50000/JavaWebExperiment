import { createRouter, createWebHistory } from 'vue-router'
import { userApi } from '@/api/api'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/index.vue')
  },
  {
    path: '/waterdam',
    name: 'waterdam',
    component: () => import('../views/waterdam/index.vue'),
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 导航守卫
router.beforeEach(async (to, from, next) => {
  // 如果页面需要登录验证
  if (to.matched.some(record => record.meta.requiresAuth)) {
    try {
      // 检查登录状态
      const response = await userApi.check();
      console.log('路由守卫检查登录状态:', response);
      
      if (response && response.success) {
        // 已登录，允许访问
        next();
      } else {
        // 未登录，重定向到登录页
        next({
          path: '/login',
          query: { redirect: to.fullPath }
        });
      }
    } catch (error) {
      console.error('检查登录状态失败:', error);
      // 检查失败，重定向到登录页
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      });
    }
  } else {
    // 不需要登录验证的页面直接通过
    next();
  }
})

export default router