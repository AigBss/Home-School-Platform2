package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    List<Message> selectMessagesByChatId(Long chatId);

    @Delete("DELETE FROM message WHERE chat_id = #{chatId}")
    void deleteMessagesByChatId(Long chatId);

}
