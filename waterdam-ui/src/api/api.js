import request from './request'

// 用户相关接口
export const userApi = {
  // 用户登录
  login: (username, password) => {
    return request({
      url: '/api/user/login',
      method: 'post',
      data: { username, password }
    })
  },
  // 退出登录
  logout: () => {
    return request({
      url: '/api/user/logout',
      method: 'post'
    })
  },
  // 检查登录状态
  check: () => {
    return request({
      url: '/api/user/check',
      method: 'get'
    })
  }
}

// 留言板相关接口
export const messageApi = {
  // 获取留言列表
  getMessages: () => {
    return request({
      url: '/api/message/board',
      method: 'get'
    })
  },
  // 发送新留言
  sendMessage: (content) => {
    return request({
      url: '/api/message/send',
      method: 'post',
      data: {
        message: content 
      }
    })
  }
}

export const damApi = {
  // 获取水库列表
  getDamList: () => {
    return request({
      url: '/api/dam/list',
      method: 'get'
    })
  },
  //修改水库信息
  updateDam: (dam) => {
    return request({
      url: '/api/dam/update',
      method: 'post',
      data: dam
    })
  },

  //删除水库
  deleteDam: (id) => {
    return request({
      url: '/api/dam/delete',
      method: 'post',
      data: { id }
    })
  },
  //添加水库
  addDam: (dam) => {
    return request({
      url: '/api/dam/add',
      method: 'post',
      data: dam
    })
  }
}