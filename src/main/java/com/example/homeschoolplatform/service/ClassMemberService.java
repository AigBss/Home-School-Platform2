package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.ClassMember;

public interface ClassMemberService {
    boolean joinClass(Integer classId, Integer userId);
    boolean createClass(Integer classId, Integer userId);
}
