package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.Chat;
import com.example.homeschoolplatform.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ChatService  {
    Chat startChat(Long initiatorId, Long recipientId);
    List<Message> getMessages(Long chatId);
    Message sendMessage(Message message);
    void updateOnlineStatus(Long userId, Integer status);
    void endChat(Long chatId);
}
