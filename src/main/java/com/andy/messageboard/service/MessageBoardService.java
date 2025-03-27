package com.andy.messageboard.service;

import com.andy.messageboard.pojo.MessageBoard;

import java.util.List;

public interface MessageBoardService {
    public MessageBoard getMessageBoardById(Long id);
    public int insertMessageBoard(MessageBoard messageBoard);
    public int updateMessageBoard(MessageBoard messageBoard);
    public int deleteMessageBoardById(Long id);
    public int deleteMessageBoardByIds(Long[] ids);
    public List<MessageBoard> getAllMessageBoards(MessageBoard messageBoard);

}
