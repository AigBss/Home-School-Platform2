package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Chat;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;




/**
* @author user
* @description 针对表【chat】的数据库操作Mapper
* @createDate 2023-04-28 22:51:36
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

    @Delete("DELETE FROM chat WHERE id = #{chatId}")
    void deleteById(Long chatId);

}
