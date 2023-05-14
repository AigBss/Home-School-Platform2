package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.Class;
import com.example.homeschoolplatform.entity.ClassMember;
import com.example.homeschoolplatform.mapper.ClassMemberMapper;
import com.example.homeschoolplatform.service.ClassMemberService;
import com.example.homeschoolplatform.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassMemberServiceImpl implements ClassMemberService {

    @Autowired
    private ClassMemberMapper classMemberMapper;
    @Autowired
    private ClassService classService;


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

    @Override
    public List<Class> findClassesByUserId(Long userId) {
        return classMemberMapper.selectClassesByUserId(userId);
    }

    @Override
    public boolean removeClassMember(Long classId, Long userId) {
        Class cls = classService.findClassById(classId);
        if (cls.getCreatorId().equals(userId)) {
            // The member is the creator of the class, can't remove
            return false;
        }
        return classMemberMapper.deleteByClassIdAndUserId(classId, userId) > 0;
    }

    @Override
    public List<ClassMember> findMembersByClassId(Long classId) {
        return classMemberMapper.selectMembersByClassId(classId);
    }

}
