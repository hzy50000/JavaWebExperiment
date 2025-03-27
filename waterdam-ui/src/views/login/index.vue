<template>
  <div class="login-container">
    <div class="login-form">
      <h2>用户登录</h2>
      <div class="form-item">
        <label>用户名:</label>
        <input type="text" v-model="username" placeholder="请输入用户名">
      </div>
      <div class="form-item">
        <label>密码:</label>
        <input type="password" v-model="password" placeholder="请输入密码">
      </div>
      <div class="error-message" v-if="errorMsg">{{ errorMsg }}</div>
      <button class="login-button" 
              @click="handleLogin" 
              :disabled="!username || !password || isLoading">
        {{ isLoading ? '登录中...' : '登录' }}
      </button>
    </div>
  </div>
</template>

<script>
import { userApi } from '@/api/api'

export default {
  name: 'LoginView',
  data() {
    return {
      username: '',
      password: '',
      errorMsg: '',
      isLoading: false
    }
  },
  methods: {
    async handleLogin() {
      // 前端参数校验
      if (!this.username.trim()) {
        this.errorMsg = '用户名不能为空';
        return;
      }
      if (!this.password.trim()) {
        this.errorMsg = '密码不能为空';
        return;
      }

      this.isLoading = true;
      this.errorMsg = '';

      try {
        const response = await userApi.login(this.username, this.password);
        console.log('登录响应:', response);
        
        if (response && response.success) {
          // 验证响应中是否包含用户信息
          if (response.data) {
            // 可以将用户信息存储在 localStorage 中
            localStorage.setItem('user', JSON.stringify(response.data));
            // 跳转到留言板页面
            this.$router.push('/messageboard');
          } else {
            this.errorMsg = '登录响应缺少用户信息';
          }
        } else {
          // 显示错误信息
          this.errorMsg = response.message || '登录失败';
        }
      } catch (error) {
        console.error('登录请求失败:', error);
        this.errorMsg = error.message || '网络错误，请检查后端服务是否启动';
      } finally {
        this.isLoading = false;
      }
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.login-form {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.form-item {
  margin-bottom: 1rem;
}

.form-item label {
  display: block;
  margin-bottom: 0.5rem;
  color: #333;
}

.form-item input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

.error-message {
  color: #ff4444;
  margin-bottom: 1rem;
  font-size: 0.9rem;
}

.login-button {
  width: 100%;
  padding: 0.8rem;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s;
}

.login-button:hover:not(:disabled) {
  background-color: #45a049;
}

.login-button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}
</style>