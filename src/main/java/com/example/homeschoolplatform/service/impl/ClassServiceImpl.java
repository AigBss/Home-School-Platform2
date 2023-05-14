package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.Class;
import com.example.homeschoolplatform.entity.GroupChat;
import com.example.homeschoolplatform.mapper.ClassMapper;
import com.example.homeschoolplatform.mapper.ClassMemberMapper;
import com.example.homeschoolplatform.mapper.GroupChatMapper;
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

    @Autowired
    private GroupChatMapper groupChatMapper; // Add this line

    @Override
    public int createClass(Class cls) {
        int result = classMapper.insert(cls);
        GroupChat groupChat = new GroupChat();
        groupChat.setGroupName(cls.getName()); // Use setGroupName instead of setName
        groupChat.setClassId(cls.getId()); // Assuming GroupChat has a classId field
        groupChatMapper.insert(groupChat); // Create a new GroupChat when a new Class is created
        return result;
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

    @Override
    public List<Class> findClassesCreatedByUserId(Long userId) {
        return classMapper.selectClassesCreatedByUserId(userId);
    }


    @Override
    public boolean updateClassName(Integer id, String newName) {
        Class cls = new Class();
        cls.setId(id);
        cls.setName(newName);
        return classMapper.updateByPrimaryKeySelective(cls) > 0;
    }

    @Override
    public boolean removeClassMember(Long classId, Long memberId) {
        return classMemberMapper.deleteByClassIdAndUserId(classId, memberId) > 0;
    }


}
