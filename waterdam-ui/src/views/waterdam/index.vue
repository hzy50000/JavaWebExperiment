<template>
  <div class="dam-container">
    <div class="header">
      <h1>水库大坝管理</h1>
      <button class="logout-btn" @click="handleLogout">退出登录</button>
    </div>

    <!-- 添加新大坝的表单 -->
    <div class="add-form">
      <h2>{{ isEditing ? '编辑大坝' : '添加新大坝' }}</h2>
      <div class="form-group">
        <label>名称</label>
        <input v-model="damForm.name" placeholder="输入大坝名称" />
      </div>
      <div class="form-group">
        <label>河流</label>
        <input v-model="damForm.river" placeholder="输入所在河流" />
      </div>
      <div class="form-group">
        <label>容量(立方米)</label>
        <input v-model="damForm.capacity" type="number" placeholder="输入容量" />
      </div>
      <div class="form-group">
        <label>完工日期</label>
        <input v-model="damForm.completeDate" type="date" />
      </div>
      <div class="form-buttons">
        <button class="submit-btn" @click="submitDam" :disabled="isSubmitting">
          {{ isSubmitting ? '提交中...' : (isEditing ? '更新' : '添加') }}
        </button>
        <button v-if="isEditing" class="cancel-btn" @click="cancelEdit">取消</button>
      </div>
    </div>

    <!-- 大坝列表 -->
    <div v-if="isLoading" class="loading">
      加载中...
    </div>
    <div v-else class="dam-list">
      <h2>大坝列表</h2>
      <table>
        <thead>
        <tr>
          <th>名称</th>
          <th>河流</th>
          <th>容量(立方米)</th>
          <th>完工日期</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="dam in dams" :key="dam.id">
          <td>{{ dam.name }}</td>
          <td>{{ dam.river }}</td>
          <td>{{ formatCapacity(dam.capacity) }}</td>
          <td>{{ formatDate(dam.completeDate) }}</td>
          <td>
            <button class="edit-btn" @click="editDam(dam)">编辑</button>
            <button class="delete-btn" @click="deleteDam(dam.id)">删除</button>
          </td>
        </tr>
        <tr v-if="dams.length === 0">
          <td colspan="5" class="no-data">暂无数据</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { userApi, damApi } from '@/api/api'

export default {
  name: 'DamManagement',
  data() {
    return {
      dams: [],
      isLoading: false,
      isSubmitting: false,
      isEditing: false,
      editingId: null,
      damForm: {
        name: '',
        river: '',
        capacity: '',
        completeDate: ''
      }
    }
  },
  created() {
    this.fetchDams()
  },
  methods: {
    async fetchDams() {
      this.isLoading = true
      try {
        const response = await damApi.getDamList()
        this.dams = Array.isArray(response) ? response : (response?.data || [])
      } catch (error) {
        console.error('获取大坝列表失败:', error)
      } finally {
        this.isLoading = false
      }
    },

    async submitDam() {
      if (!this.validateForm() || this.isSubmitting) {
        return
      }

      this.isSubmitting = true
      try {
        const formData = {
          name: this.damForm.name,
          river: this.damForm.river,
          capacity: this.damForm.capacity,
          complete: new Date(this.damForm.completeDate).getTime().toString()
        }

        let response
        if (this.isEditing) {
          formData.id = this.editingId
          response = await damApi.updateDam(formData)
        } else {
          response = await damApi.addDam(formData)
        }

        console.log(this.isEditing ? '更新大坝响应:' : '添加大坝响应:', response)
        this.resetForm()
        await this.fetchDams()
      } catch (error) {
        console.error(this.isEditing ? '更新大坝失败:' : '添加大坝失败:', error)
      } finally {
        this.isSubmitting = false
      }
    },

    async deleteDam(id) {
      if (!confirm('确定要删除该大坝吗？')) {
        return
      }

      try {
        const response = await damApi.deleteDam(id)
        console.log('删除大坝响应:', response)
        await this.fetchDams()
      } catch (error) {
        console.error('删除大坝失败:', error)
      }
    },

    editDam(dam) {
      this.isEditing = true
      this.editingId = dam.id
      this.damForm = {
        name: dam.name,
        river: dam.river,
        capacity: dam.capacity,
        completeDate: this.formatDateForInput(dam.completeDate)
      }
    },

    cancelEdit() {
      this.resetForm()
    },

    resetForm() {
      this.isEditing = false
      this.editingId = null
      this.damForm = {
        name: '',
        river: '',
        capacity: '',
        completeDate: ''
      }
    },

    validateForm() {
      if (!this.damForm.name.trim()) {
        alert('请输入大坝名称')
        return false
      }
      if (!this.damForm.river.trim()) {
        alert('请输入所在河流')
        return false
      }
      if (!this.damForm.capacity || isNaN(this.damForm.capacity)) {
        alert('请输入有效的容量')
        return false
      }
      if (!this.damForm.completeDate) {
        alert('请选择完工日期')
        return false
      }
      return true
    },

    formatCapacity(capacity) {
      return Number(capacity).toLocaleString() + ' m³'
    },

    formatDate(timestamp) {
      if (!timestamp) return '-'
      const date = new Date(timestamp)
      return date.toLocaleDateString()
    },

    formatDateForInput(timestamp) {
      if (!timestamp) return ''
      const date = new Date(timestamp)
      return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`
    },

    async handleLogout() {
      try {
        const response = await userApi.logout()
        console.log('退出登录响应:', response)
        localStorage.removeItem('user')
        this.$router.push('/login')
      } catch (error) {
        console.error('退出登录失败:', error)
      }
    }
  }
}
</script>

<style scoped>
.dam-container {
  max-width: 1000px;
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

.add-form {
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 30px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.add-form h2 {
  margin-top: 0;
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.form-buttons {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}

.submit-btn, .cancel-btn {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.submit-btn {
  background-color: #4CAF50;
  color: white;
}

.submit-btn:hover:not(:disabled) {
  background-color: #45a049;
}

.submit-btn:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.cancel-btn {
  background-color: #f44336;
  color: white;
}

.cancel-btn:hover {
  background-color: #e53935;
}

.loading {
  text-align: center;
  padding: 20px;
  color: #666;
}

.dam-list {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.dam-list h2 {
  margin-top: 0;
  margin-bottom: 20px;
  color: #333;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.edit-btn, .delete-btn {
  padding: 6px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 5px;
}

.edit-btn {
  background-color: #2196F3;
  color: white;
}

.edit-btn:hover {
  background-color: #0b7dda;
}

.delete-btn {
  background-color: #f44336;
  color: white;
}

.delete-btn:hover {
  background-color: #e53935;
}

.no-data {
  text-align: center;
  color: #666;
  padding: 20px;
}
</style>