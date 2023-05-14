package com.example.homeschoolplatform.service;

import com.example.homeschoolplatform.entity.Message;
import com.example.homeschoolplatform.entity.MessageVo;

import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);

    List<MessageVo> getMessagesByChatId(Long chatId);

}
