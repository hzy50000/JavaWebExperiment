package com.andy.waterdam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.andy.waterdam.pojo.MessageBoard;

@Mapper
public interface MessageBoardMapper {
    public MessageBoard selectMessageBoardById(Long id);
    public int insertMessageBoard(MessageBoard messageBoard);
    public int updateMessageBoard(MessageBoard messageBoard);
    public List<MessageBoard> selectMessageBoardList(MessageBoard messageBoard);
    public int deleteMessageBoardById(Long id);
    public int deleteMessageBoardByIds(Long[] ids);
}
