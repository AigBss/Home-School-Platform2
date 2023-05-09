package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.Class;
import com.example.homeschoolplatform.entity.ClassMember;

import java.util.List;

public interface ClassMemberService {
    boolean joinClass(Integer classId, Integer userId);
    boolean createClass(Integer classId, Integer userId);
    List<Class> findClassesByUserId(Long userId);
}
