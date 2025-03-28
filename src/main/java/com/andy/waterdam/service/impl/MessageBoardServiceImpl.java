package com.andy.waterdam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.waterdam.mapper.MessageBoardMapper;
import com.andy.waterdam.pojo.MessageBoard;
import com.andy.waterdam.service.MessageBoardService;

@Service
public class MessageBoardServiceImpl implements MessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;

    @Override
    public MessageBoard getMessageBoardById(Long id) {
        return messageBoardMapper.selectMessageBoardById(id);
    }

    @Override
    public int insertMessageBoard(MessageBoard messageBoard) {
        return messageBoardMapper.insertMessageBoard(messageBoard);
    }

    @Override
    public int updateMessageBoard(MessageBoard messageBoard) {
        return messageBoardMapper.updateMessageBoard(messageBoard);
    }

    @Override
    public int deleteMessageBoardById(Long id) {
        return messageBoardMapper.deleteMessageBoardById(id);
    }

    @Override
    public int deleteMessageBoardByIds(Long[] ids) {
        return messageBoardMapper.deleteMessageBoardByIds(ids);
    }

    @Override
    public List<MessageBoard> getAllMessageBoards(MessageBoard messageBoard) {
        return messageBoardMapper.selectMessageBoardList(messageBoard);
    }
}
