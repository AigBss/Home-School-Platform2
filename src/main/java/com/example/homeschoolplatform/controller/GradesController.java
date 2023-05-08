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
    @GetMapping
    public ResponseEntity<List<Grades>> getAllGrades() {
        List<Grades> gradesList = gradesService.getAllGrades();
        return new ResponseEntity<>(gradesList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Grades> createGrades(@RequestBody Grades grades) {
        Grades createdGrades = gradesService.createGrades(grades);
        return new ResponseEntity<>(createdGrades, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Grades> updateGrades(@PathVariable Integer id, @RequestBody Grades grades) {
        grades.setId(id);
        Grades updatedGrades = gradesService.updateGrades(grades);
        return new ResponseEntity<>(updatedGrades, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGrades(@PathVariable Long id) {
        gradesService.deleteGrades(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<Grades>> getGradesBySubject(@PathVariable String subject) {
        List<Grades> gradesList = gradesService.getGradesBySubject(subject);
        return new ResponseEntity<>(gradesList, HttpStatus.OK);
    }
}

