package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Message;
import com.example.homeschoolplatform.entity.MessageVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
* @author user
* @description 针对表【message】的数据库操作Mapper
* @createDate 2023-04-28 22:36:54
* @Entity com.example.homeschoolplatform.entity.Message
*/
@Mapper
public interface MessageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    @Select("SELECT * FROM message WHERE chat_id = #{chatId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "chatId", column = "chat_id"),
            @Result(property = "senderId", column = "sender_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createdAt", column = "created_at"),
    })
    List<Message> selectMessagesByChatId(Long chatId);

    @Select("SELECT * FROM message join user on message.sender_id = user.id WHERE chat_id = #{chatId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "chatId", column = "chat_id"),
            @Result(property = "senderId", column = "sender_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "username", column = "username"),
    })
    List<MessageVo> selectMessagesFullByChatId(Long chatId);

    @Delete("DELETE FROM message WHERE chat_id = #{chatId}")
    void deleteMessagesByChatId(Long chatId);

}
