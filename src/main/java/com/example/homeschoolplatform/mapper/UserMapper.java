package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

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

    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(column="user_type", property="userType", jdbcType= JdbcType.INTEGER),
    })
    User getUserByUsername(String username);

    int insertUser(User user);
    @Update("UPDATE user SET online_status = #{status} WHERE id = #{userId}")
    void updateOnlineStatus(Long userId, Integer status);
    @Update("UPDATE user SET username = #{username} WHERE id = #{id}")
    int updateUsername(@Param("id") Long id, @Param("username") String username);

    @Update("UPDATE user SET password = #{password} WHERE id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);

    List<User> getAllUsers();
    int updateUserType(@Param("id") Long id, @Param("userType") int userType);

}
