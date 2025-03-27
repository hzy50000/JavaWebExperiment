package com.andy.messageboard.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        
        // 检查session中是否有用户信息
        if (session.getAttribute("user") != null) {
            return true;
        }
        
        // 如果未登录，返回错误信息
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("message", "请先登录");
        
        response.getWriter().write(objectMapper.writeValueAsString(result));
        return false;
    }
}