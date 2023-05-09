package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.File;
import com.example.homeschoolplatform.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        try {
            File uploadedFile = fileService.uploadFile(file, userId);
            return new ResponseEntity<>(uploadedFile, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fileId}")
    public void downloadFile(@PathVariable("fileId") Long fileId, HttpServletResponse response) {
        java.io.File file = fileService.downloadFile(fileId);
        if (file != null && file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            try {
                Files.copy(file.toPath(), response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable("fileId") Long fileId, @RequestParam("userId") Long userId) {
        boolean result = fileService.deleteFile(fileId, userId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Failed to delete the file", HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<File>> getUserFiles(@PathVariable("userId") Long userId) {
//        List<File> files = fileService.getUserFiles(userId);
//        return ResponseEntity.ok(files);
//    }
}
