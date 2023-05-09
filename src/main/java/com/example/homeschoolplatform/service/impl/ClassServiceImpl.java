package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.Class;
import com.example.homeschoolplatform.mapper.ClassMapper;
import com.example.homeschoolplatform.mapper.ClassMemberMapper;
import com.example.homeschoolplatform.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMemberMapper classMemberMapper;
    @Autowired
    private ClassMapper classMapper;

    @Override
    public int createClass(Class cls) {
        return classMapper.insert(cls);
    }

    @Override
    public int deleteClass(Long id) {
        return classMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Class findClassById(Long id) {
        return classMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Class> findAllClasses() {
        return classMapper.selectAll();
    }

    @Override
    public int updateClass(Class cls) {
        return classMapper.updateByPrimaryKey(cls);
    }

    @Override
    public int deleteClassByIdAndCreatorId(Long id, Long creatorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("creatorId", creatorId);
        return classMapper.deleteClassByIdAndCreatorId(params);
    }

}
