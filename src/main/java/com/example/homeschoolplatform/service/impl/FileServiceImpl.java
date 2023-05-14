package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.File;
import com.example.homeschoolplatform.mapper.FileMapper;
import com.example.homeschoolplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    private final String storageDirectory = "./";

    @Override
    public File uploadFile(MultipartFile file, Long userId) throws IOException {
        String filename = file.getOriginalFilename();
        Path filePath = Paths.get(storageDirectory, filename);
        Files.copy(file.getInputStream(), filePath);

        File newFile = new File();
        newFile.setFilename(filename);
        newFile.setUserid(userId);
        newFile.setPath(filePath.toString());

        fileMapper.insert(newFile);

        return newFile;
    }

    @Override
    public java.io.File downloadFile(Long fileId) {
        File file = fileMapper.selectByPrimaryKey(fileId);
        if (file != null) {
            return new java.io.File(file.getPath());
        }
        return null;
    }

    @Override
    public boolean deleteFile(Long fileId, Long userId) {
        File file = fileMapper.selectByPrimaryKey(fileId);
        if (file != null && file.getUserid().equals(userId)) {
            fileMapper.deleteByPrimaryKey(fileId);
            return true;
        }
        return false;
    }
    @Override
    public List<File> getUserFiles(Long userId) {
        return fileMapper.selectByUserId(userId);
    }
    @Override
    public List<File> getAllFiles() {
        return fileMapper.selectAllFiles();
    }

}
