package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.Grades;
import com.example.homeschoolplatform.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    private GradesService gradesService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadGrades(@RequestParam("file") MultipartFile file) {
        try {
            gradesService.saveGradesFromExcel(file);
            return new ResponseEntity<>("Grades uploaded successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error uploading grades: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Grades>> getGradesByUserId(@PathVariable int userId) {
        try {
            List<Grades> gradesList = gradesService.getGradesByUserId(userId);
            return new ResponseEntity<>(gradesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
