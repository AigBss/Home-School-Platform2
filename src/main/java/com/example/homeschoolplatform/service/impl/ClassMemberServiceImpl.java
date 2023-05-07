package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.ClassMember;
import com.example.homeschoolplatform.mapper.ClassMemberMapper;
import com.example.homeschoolplatform.service.ClassMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassMemberServiceImpl implements ClassMemberService {

    @Autowired
    private ClassMemberMapper classMemberMapper;

    @Override
    public boolean joinClass(Integer classId, Integer userId) {
        ClassMember classMember = new ClassMember();
        classMember.setClassId(classId);
        classMember.setUserId(userId);
        return classMemberMapper.insert(classMember) > 0;
    }

    @Override
    public boolean createClass(Integer classId, Integer userId) {
        ClassMember classMember = new ClassMember();
        classMember.setClassId(classId);
        classMember.setUserId(userId);
        return classMemberMapper.insert(classMember) > 0;
    }
}
