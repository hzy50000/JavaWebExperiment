package com.andy.messageboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andy.messageboard.pojo.MessageBoard;
import com.andy.messageboard.pojo.User;
import com.andy.messageboard.service.MessageBoardService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/message")
public class MessagBoardController {

    @Autowired
    private MessageBoardService messageBoardService;

    @GetMapping("/board")
    public List<MessageBoard> getAllMessages(MessageBoard messageBoard) {
        return messageBoardService.getAllMessageBoards(messageBoard);
    }

    @PostMapping("/send")
    public Map<String, Object> sendMessage(@RequestBody MessageBoard messageBoard, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        // 从 session 获取当前登录用户
        User user = (User) session.getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }

        // 设置当前用户ID
        messageBoard.setUserId(user.getId());
        
        int rows = messageBoardService.insertMessageBoard(messageBoard);
        if (rows > 0) {
            result.put("success", true);
            result.put("message", "发送成功");
        } else {
            result.put("success", false);
            result.put("message", "发送失败");
        }
        return result;
    }
}
