package com.example.homeschoolplatform.controller;

import com.example.homeschoolplatform.entity.Message;
import com.example.homeschoolplatform.entity.MessageVo;
import com.example.homeschoolplatform.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private WebSocket webSocket;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message sentMessage = messageService.sendMessage(message);

        // 找到接收者的ws会话，从服务端推请求给网页
        webSocket.sendMessageToSomeOne(message.getChatId());
        return new ResponseEntity<>(sentMessage, HttpStatus.OK);
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageVo>> getMessagesByChatId(@PathVariable("chatId") Long chatId) {
        List<MessageVo> messages = messageService.getMessagesByChatId(chatId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}

