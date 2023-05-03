package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.Chat;
import com.example.homeschoolplatform.entity.Message;
import com.example.homeschoolplatform.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/start")
    public ResponseEntity<Chat> startChat(@RequestParam("initiatorId") Long initiatorId, @RequestParam("recipientId") Long recipientId) {
        Chat chat = chatService.startChat(initiatorId, recipientId);
        if (chat != null) {
            return new ResponseEntity<>(chat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("chatId") Long chatId) {
        List<Message> messages = chatService.getMessages(chatId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message sentMessage = chatService.sendMessage(message);
        if (sentMessage != null) {
            return new ResponseEntity<>(sentMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Void> updateOnlineStatus(@RequestParam("userId") Long userId, @RequestParam("status") Integer status) {
        chatService.updateOnlineStatus(userId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/end/{chatId}")
    public ResponseEntity<Void> endChat(@PathVariable("chatId") Long chatId) {
        chatService.endChat(chatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
