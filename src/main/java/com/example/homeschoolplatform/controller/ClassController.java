package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.Class;
import com.example.homeschoolplatform.service.ClassMemberService;
import com.example.homeschoolplatform.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private ClassMemberService classMemberService;


    @PostMapping("/create")
    public ResponseEntity<?> createClass(@RequestBody Class clazz) {
        try {
            classService.createClass(clazz);
            classMemberService.joinClass(clazz.getId().intValue(), clazz.getCreatorId().intValue());
            return new ResponseEntity<>(clazz, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable Long id, @RequestParam Long creatorId) {
        try {
            int result = classService.deleteClassByIdAndCreatorId(id, creatorId);
            if (result > 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Failed to delete the class", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public Class findClassById(@PathVariable("id") Long id) {
        return classService.findClassById(id);
    }

    @GetMapping("/all")
    public List<Class> findAllClasses() {
        return classService.findAllClasses();
    }

    @PutMapping("/update")
    public int updateClass(@RequestBody Class cls) {
        return classService.updateClass(cls);
    }

    @GetMapping("/created/{userId}")
    public ResponseEntity<List<Class>> findClassesCreatedByUserId(@PathVariable("userId") Long userId) {
        try {
            List<Class> classes = classService.findClassesCreatedByUserId(userId);
            return new ResponseEntity<>(classes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<?> updateClassName(@PathVariable("id") int id, @RequestParam("newName") String newName) {
        try {
            if (classService.updateClassName(id, newName)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update the class name", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}


