package com.andy.waterdam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andy.waterdam.pojo.LoginRequest;
import com.andy.waterdam.pojo.User;
import com.andy.waterdam.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest,
                                    HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        // 参数校验
        if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "密码不能为空");
            return result;
        }
        
        // 调用service层的登录方法
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        
        if (user != null) {
            // 登录成功，将用户信息存入session
            session.setAttribute("user", user);
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("data", user);
        } else {
            // 登录失败
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        
        return result;
    }

    @GetMapping("/check")
    public Map<String, Object> checkLoginStatus(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object user = session.getAttribute("user");
        
        if (user != null) {
            result.put("success", true);
            result.put("message", "已登录");
            result.put("data", user);
        } else {
            result.put("success", false);
            result.put("message", "未登录");
        }
        
        return result;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        if (session.getAttribute("user") != null) {
            // 清除会话
            session.removeAttribute("user");
            result.put("success", true);
            result.put("message", "退出登录成功");
        } else {
            result.put("success", false);
            result.put("message", "您尚未登录");
        }

        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查用户名是否已存在
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return result;
        }
        
        // 注册新用户
        int rows = userService.insertUser(user);
        if (rows > 0) {
            result.put("success", true);
            result.put("message", "注册成功");
        } else {
            result.put("success", false);
            result.put("message", "注册失败");
        }
        
        return result;
    }
}
