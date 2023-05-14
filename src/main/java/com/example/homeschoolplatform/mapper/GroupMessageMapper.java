package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.GroupMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author user
* @description 针对表【group_message】的数据库操作Mapper
* @createDate 2023-05-10 12:13:00
* @Entity com.example.homeschoolplatform.entity.GroupMessage
*/
@Mapper
public interface GroupMessageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GroupMessage record);

    int insertSelective(GroupMessage record);

    GroupMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupMessage record);

    int updateByPrimaryKey(GroupMessage record);

    @Select("SELECT * FROM group_message WHERE group_id = #{groupId} ORDER BY created_at DESC")
    @Results(value ={
            @Result(id = true,column = "id", property = "id") ,
            @Result(column = "group_id", property = "groupId") ,
            @Result(column = "sender_id",property = "senderId") ,
            @Result(column = "content",property = "content"),
            @Result(column = "created_at", property = "createdAt")
    })
    List<GroupMessage> selectByGroupId(Long groupId);

}
