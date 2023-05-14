package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.GroupMessage;
import com.example.homeschoolplatform.mapper.GroupChatMapper;
import com.example.homeschoolplatform.mapper.GroupMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/group-chat")
public class GroupChatController {

    @Autowired
    private GroupMessageMapper groupMessageMapper;

    @Autowired
    private GroupChatMapper groupChatMapper;

    @PostMapping("/message")
    public ResponseEntity<GroupMessage> postMessage(@RequestBody GroupMessage message) {
        groupMessageMapper.insertSelective(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{groupId}/messages")
    public ResponseEntity<List<GroupMessage>> getGroupMessages(@PathVariable Long groupId) {
        List<GroupMessage> messages = groupMessageMapper.selectByGroupId(groupId);
        return ResponseEntity.ok(messages);
    }

    @DeleteMapping("/message/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        groupMessageMapper.deleteByPrimaryKey(messageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findGroupChatIdByClassId/{classId}")
    public ResponseEntity<Integer> getGroupMessages(@PathVariable int classId) {
        int groupChatId = groupChatMapper.selectIdByClassId(classId);
        return ResponseEntity.ok(groupChatId);
    }

}
