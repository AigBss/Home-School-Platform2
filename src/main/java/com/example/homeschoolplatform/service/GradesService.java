package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.Grades;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GradesService {

    void saveGradesFromExcel(MultipartFile file) throws Exception;

    List<Grades> getGradesByUserId(int userId);

}
