package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.service.ClassMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


}
