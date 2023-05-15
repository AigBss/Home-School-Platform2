package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.Class;
import com.example.homeschoolplatform.entity.ClassMember;
import com.example.homeschoolplatform.service.ClassMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class ClassMemberController {

    @Autowired
    private ClassMemberService classMemberService;

    @PostMapping("/class/join")
    public ResponseEntity<?> joinClass(@RequestParam("userId") Integer userId, @RequestParam("classId") Integer classId) {
        if (classMemberService.joinClass(classId, userId)) {
            return ResponseEntity.ok("加入班级成功");
        } else {
            return ResponseEntity.badRequest().body("加入班级失败");
        }
    }

    @GetMapping("/member/{userId}")
    public ResponseEntity<List<Class>> findClassesByUserId(@PathVariable("userId") Long userId) {
        try {
            List<Class> classes = classMemberService.findClassesByUserId(userId);
            return new ResponseEntity<>(classes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/class/{classId}/members")
    public ResponseEntity<List<ClassMember>> findMembersByClassId(@PathVariable("classId") Long classId) {
        try {
            List<ClassMember> members = classMemberService.findMembersByClassId(classId);
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/class/{classId}/member/{memberId}")
    public ResponseEntity<?> removeClassMember(@PathVariable("classId") Long classId, @PathVariable("memberId") Long memberId) {
        try {
            if (classMemberService.removeClassMember(classId, memberId)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Failed to remove the class member", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
