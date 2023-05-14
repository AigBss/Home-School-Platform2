package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Chat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author user
* @description 针对表【chat】的数据库操作Mapper
* @createDate 2023-05-10 19:35:44
* @Entity com.example.homeschoolplatform.entity.Chat
*/
@Mapper
public interface ChatMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);
    List<Chat> selectChatsByUserId(int userId);

}
