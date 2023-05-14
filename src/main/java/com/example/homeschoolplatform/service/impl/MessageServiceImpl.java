package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.Message;
import com.example.homeschoolplatform.entity.MessageVo;
import com.example.homeschoolplatform.mapper.MessageMapper;
import com.example.homeschoolplatform.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message sendMessage(Message message) {
        if (message.getChatId() == null || message.getSenderId() == null) {
            throw new IllegalArgumentException("ChatId and SenderId cannot be null");
        }

        message.setCreatedAt(new Date());
        messageMapper.insert(message);
        return message;
    }

    @Override
    public List<MessageVo> getMessagesByChatId(Long chatId) {
        return messageMapper.selectMessagesFullByChatId(chatId);
    }


}
