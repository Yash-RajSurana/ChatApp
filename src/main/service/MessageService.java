package main.service;

import main.dao.MessageDao;
import main.model.Message;

import java.sql.SQLException;
import java.util.List;


public class MessageService {
    private MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void sendMessage(Message message) throws SQLException {
        messageDao.sendMessage(message);
    }

    public Message getMessageById(int messageId) throws SQLException {
        return messageDao.getMessageById(messageId);
    }

    public List<Message> getAllMessages() throws SQLException {
        return messageDao.getAllMessages();
    }

    public void updateMessage(Message message) throws SQLException {
        messageDao.updateMessage(message);
    }

    public void deleteMessage(int messageId) throws SQLException {
        messageDao.deleteMessage(messageId);
    }
    public void addMessage(Message message) throws SQLException {
        messageDao.createMessage(message);
    }


}
