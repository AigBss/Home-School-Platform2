package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper

public interface ClassMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    List<Class> selectAll();

    int deleteClassByIdAndCreatorId(Map<String, Object> params);


}
