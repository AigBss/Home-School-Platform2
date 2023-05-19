package com.example.homeschoolplatform.service.impl;

import com.example.homeschoolplatform.entity.Chat;
import com.example.homeschoolplatform.entity.Message;
import com.example.homeschoolplatform.mapper.ChatMapper;
import com.example.homeschoolplatform.mapper.MessageMapper;
import com.example.homeschoolplatform.mapper.UserMapper;
import com.example.homeschoolplatform.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Chat startChat(int initiatorId, int recipientId) {
        Chat chat = new Chat();
        chat.setInitiatorId(initiatorId);
        chat.setRecipientId(recipientId);
        chat.setInitiatorOnline(0);
        chat.setRecipientOnline(0);
        chatMapper.insert(chat);
        return chat;
    }

    @Override
    public List<Message> getMessages(Long chatId) {
        return messageMapper.selectMessagesByChatId(chatId);
    }

    @Override
    public Message sendMessage(Message message) {
        messageMapper.insert(message);
        return message;
    }

    @Override
    public void updateOnlineStatus(Long userId, Integer status) {
        userMapper.updateOnlineStatus(userId, status);
    }

    @Override
    public void endChat(Long chatId) {
        chatMapper.deleteByPrimaryKey(chatId);
        messageMapper.deleteMessagesByChatId(chatId);
    }

    @Override
    public List<Chat> getChatsByUserId(int userId) {
        return chatMapper.selectChatsByUserId(userId);//需要的类型:Long提供的类型:Integer
    }

    @Override
    public Chat getChatInfoByChatId(int chatId) {
        return chatMapper.selectByPrimaryKey(Long.valueOf(chatId));
    }
}
