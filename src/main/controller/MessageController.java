package main.controller;
import main.model.Message;
import main.service.MessageService;

import java.sql.SQLException;
import java.util.List;

public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    public void sendMessage(int senderId, Integer receiverId, String messageContent, int yop) throws SQLException {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setMessage(messageContent);
        message.setYop(yop);
        messageService.addMessage(message);
    }
    public void sendMessage(Message message) throws SQLException {
        messageService.sendMessage(message);
    }

    public Message getMessageById(int messageId) throws SQLException {
        return messageService.getMessageById(messageId);
    }

    public List<Message> getAllMessages() throws SQLException {
        return messageService.getAllMessages();
    }

    public void updateMessage(Message message) throws SQLException {
        messageService.updateMessage(message);
    }

    public void deleteMessage(int messageId) throws SQLException {
        messageService.deleteMessage(messageId);
    }
}
