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