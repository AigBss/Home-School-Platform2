package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Grades;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author user
* @description 针对表【grades】的数据库操作Mapper
* @createDate 2023-05-06 23:43:19
* @Entity com.example.homeschoolplatform.entity.Grades
*/
@Mapper
public interface GradesMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Grades record);

    int insertSelective(Grades record);

    Grades selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Grades record);

    int updateByPrimaryKey(Grades record);
    List<Grades> listByUserId(Integer userId);
    List<Grades> selectAll();

}
