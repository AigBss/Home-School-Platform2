package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Class;

/**
* @author user
* @description 针对表【class】的数据库操作Mapper
* @createDate 2023-05-06 18:22:08
* @Entity com.example.homeschoolplatform.entity.Class
*/
public interface ClassMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

}
