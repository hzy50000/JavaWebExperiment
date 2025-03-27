<template>
  <div class="messageboard-container">
    <div class="header">
      <h1>留言板</h1>
      <button class="logout-btn" @click="handleLogout">退出登录</button>
    </div>

    <div class="message-form">
      <textarea
        v-model="newMessage"
        placeholder="请输入留言内容..."
        rows="3"
      ></textarea>
      <button class="submit-btn"
              @click="submitMessage"
              :disabled="!newMessage.trim() || isSubmitting">
        {{ isSubmitting ? '发送中...' : '发送留言' }}
      </button>
    </div>

    <div v-if="isLoading" class="loading">
      加载中...
    </div>
    <div v-else class="message-list">
      <div v-for="message in messages" :key="message.id" class="message-item">
        <div class="message-header">
          <span class="username">{{ 'user' }}</span>
        </div>
        <div class="message-content">{{ message.message }}</div>
      </div>
<!--      <div v-if="messages.length === 0" class="no-messages">-->
<!--        暂无留言-->
<!--      </div>-->
    </div>
  </div>
</template>

<script>
import { userApi, messageApi } from '@/api/api'

export default {
  name: 'MessageBoard',
  data() {
    return {
      messages: [],
      newMessage: '',
      isLoading: false,
      isSubmitting: false
    }
  },
  created() {
    this.fetchMessages()
  },
  methods: {
    async fetchMessages() {
      this.isLoading = true
      try {
        const response = await messageApi.getMessages()
        console.log('获取留言响应:', response)

        this.messages = Array.isArray(response) ? response : (response?.data || [])
      } catch (error) {
        console.error('获取留言失败:', error)
      } finally {
        this.isLoading = false
      }
    },
    async submitMessage() {
      if (!this.newMessage.trim() || this.isSubmitting) {
        return
      }

      this.isSubmitting = true
      try {
        const response = await messageApi.sendMessage(this.newMessage)
        console.log('发送留言响应:', response)

        this.newMessage = '' // 清空输入框
        await this.fetchMessages() // 刷新留言列表
      } catch (error) {
        console.error('发送留言失败:', error)
      } finally {
        this.isSubmitting = false
      }
    },
    async handleLogout() {
      try {
        const response = await userApi.logout()
        console.log('退出登录响应:', response)

        // 清除本地存储的用户信息
        localStorage.removeItem('user')
        this.$router.push('/login')
      } catch (error) {
        console.error('退出登录失败:', error)
      }
    },
    formatTime(time) {
      if (!time) return ''
      return new Date(time).toLocaleString()
    }
  }
}
</script>

<style scoped>
.messageboard-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.logout-btn {
  padding: 8px 16px;
  background-color: #ff4444;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.logout-btn:hover {
  background-color: #ff3333;
}

.message-form {
  margin-bottom: 30px;
}

.message-form textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: vertical;
  margin-bottom: 10px;
}

.submit-btn {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.submit-btn:hover:not(:disabled) {
  background-color: #45a049;
}

.submit-btn:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.loading {
  text-align: center;
  padding: 20px;
  color: #666;
}

.message-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.message-item {
  background-color: white;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.message-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  color: #666;
}

.username {
  font-weight: bold;
  color: #333;
}

.time {
  font-size: 0.9em;
}

.message-content {
  color: #333;
  line-height: 1.5;
  word-break: break-all;
}

.no-messages {
  text-align: center;
  color: #666;
  padding: 20px;
}
</style>
