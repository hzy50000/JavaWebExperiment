import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000,
  withCredentials: true
})

request.interceptors.request.use(
  config => {
    config.headers = {
      ...config.headers,
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }

    console.log('Request:', {
      url: config.url,
      method: config.method,
      data: config.data,
      headers: config.headers
    })
    return config
  },
  error => {
    console.error('Request Error:', error)
    return Promise.reject(error)
  }
)

request.interceptors.response.use(
  response => {
    console.log('Response:', {
      url: response.config.url,
      status: response.status,
      data: response.data
    })

    return response.data
  },
  error => {
    console.error('Response Error:', error)

    if (error.response) {
      switch (error.response.status) {
        case 401:
          return Promise.reject({
            success: false,
            message: '请先登录'
          })
        case 403:
          return Promise.reject({
            success: false,
            message: '没有权限执行此操作'
          })
        case 404:
          return Promise.reject({
            success: false,
            message: '请求的资源不存在'
          })
        case 500:
          return Promise.reject({
            success: false,
            message: '服务器错误，请稍后重试'
          })
        default:
          return Promise.reject({
            success: false,
            message: error.response.data.message || '请求失败'
          })
      }
    } else if (error.request) {
      return Promise.reject({
        success: false,
        message: '服务器未响应，请检查网络连接'
      })
    } else {
      // 请求配置出错
      return Promise.reject({
        success: false,
        message: '请求配置错误'
      })
    }
  }
)

export default request
