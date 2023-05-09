package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.File;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    File uploadFile(MultipartFile file, Long userId) throws IOException;
    java.io.File downloadFile(Long fileId);
    boolean deleteFile(Long fileId, Long userId);

//    @Select("SELECT * FROM file WHERE userId = #{userId}")
//    List<File> getUserFiles(Long userId);
}
