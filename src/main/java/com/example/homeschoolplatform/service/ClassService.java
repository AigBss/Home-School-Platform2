package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.Class;

import java.util.List;

public interface ClassService {
    int createClass(Class cls);
    int deleteClass(Long id);
    Class findClassById(Long id);
    List<Class> findAllClasses();
    int updateClass(Class cls);
    int deleteClassByIdAndCreatorId(Long id, Long creatorId);

}
