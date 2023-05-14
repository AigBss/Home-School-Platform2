package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.GroupChat;
import org.apache.ibatis.annotations.Mapper;

/**
* @author user
* @description 针对表【group_chat】的数据库操作Mapper
* @createDate 2023-05-10 12:12:55
* @Entity com.example.homeschoolplatform.entity.GroupChat
*/
@Mapper
public interface GroupChatMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GroupChat record);

    int insertSelective(GroupChat record);

    GroupChat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupChat record);

    int updateByPrimaryKey(GroupChat record);

    int selectIdByClassId(int classId);

}
