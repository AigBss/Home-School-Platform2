package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author user
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-12 20:10:26
* @Entity com.example.homeschoolplatform.entity.User
*/
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(String username);

    int insertUser(User user);
}
