package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author user
* @description 针对表【notification】的数据库操作Mapper
* @createDate 2023-05-08 14:02:52
* @Entity com.example.homeschoolplatform.entity.Notification
*/
@Mapper
public interface NotificationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);
    List<Notification> getNotificationsByUserId(Integer userId);

    List<Notification> getNotificationsByClassId(Integer classId);

}
