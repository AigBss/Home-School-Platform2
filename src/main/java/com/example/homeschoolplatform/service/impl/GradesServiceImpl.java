package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.Grades;
import com.example.homeschoolplatform.mapper.GradesMapper;
import com.example.homeschoolplatform.service.GradesService;
import com.example.homeschoolplatform.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GradesServiceImpl implements GradesService {

    @Autowired
    private GradesMapper gradesMapper;

    @Override
    public void saveGradesFromExcel(MultipartFile file) throws Exception {
        List<Grades> gradesList = ExcelUtil.readGradesFromExcel(file.getInputStream());
        List<Grades> existingGrades = gradesMapper.selectAll();

        for (Grades grade : gradesList) {
            if (isGradeUnique(grade, existingGrades)) {
                gradesMapper.insert(grade);
            }
        }
    }

    @Override
    public List<Grades> getGradesByUserId(int userId) {
        return gradesMapper.listByUserId(userId);
    }

    public boolean isGradeUnique(Grades grade, List<Grades> existingGrades) {
        for (Grades existingGrade : existingGrades) {
            if (existingGrade.getUserId() == grade.getUserId() &&
                    existingGrade.getSubject().equals(grade.getSubject()) &&
                    existingGrade.getTerm().equals(grade.getTerm())) {
                return false;
            }
        }
        return true;
    }
}
