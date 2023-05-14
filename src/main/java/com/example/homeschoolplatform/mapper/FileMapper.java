package com.example.homeschoolplatform.mapper;

import com.example.homeschoolplatform.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author user
* @description 针对表【file】的数据库操作Mapper
* @createDate 2023-05-08 17:54:22
* @Entity com.example.homeschoolplatform.entity.File
*/
@Mapper
public interface FileMapper {

    int deleteByPrimaryKey(Long id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
    List<File> selectByUserId(Long userId);

    List<File> selectAllFiles();

}
