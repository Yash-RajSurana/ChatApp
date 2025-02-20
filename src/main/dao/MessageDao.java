package main.dao;
import main.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    private Connection connection;

    public MessageDao(Connection connection) {
        this.connection = connection;
    }

    public void sendMessage(Message message) throws SQLException {
        String sql = "INSERT INTO messages (sender_id, receiver_id, message, yop, is_seen, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, message.getSenderId());
            stmt.setObject(2, message.getReceiverId(), Types.INTEGER);
            stmt.setString(3, message.getMessage());
            stmt.setInt(4, message.getYop());
            stmt.setBoolean(5, message.isSeen());
            stmt.setTimestamp(6, message.getTimestamp());
            stmt.executeUpdate();
        }
    }

    public Message getMessageById(int messageId) throws SQLException {
        String sql = "SELECT * FROM messages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, messageId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Message(
                            rs.getInt("id"),
                            rs.getInt("sender_id"),
                            rs.getObject("receiver_id") != null ? rs.getInt("receiver_id") : null,
                            rs.getString("message"),
                            rs.getInt("yop"),
                            rs.getBoolean("is_seen"),
                            rs.getTimestamp("timestamp")
                    );
                }
            }
        }
        return null;
    }

    public List<Message> getAllMessages() throws SQLException {
        String sql = "SELECT * FROM messages";
        List<Message> messages = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                messages.add(new Message(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getObject("receiver_id") != null ? rs.getInt("receiver_id") : null,
                        rs.getString("message"),
                        rs.getInt("yop"),
                        rs.getBoolean("is_seen"),
                        rs.getTimestamp("timestamp")
                ));
            }
        }
        return messages;
    }

    public void updateMessage(Message message) throws SQLException {
        String sql = "UPDATE messages SET sender_id = ?, receiver_id = ?, message = ?, yop = ?, is_seen = ?, timestamp = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, message.getSenderId());
            stmt.setObject(2, message.getReceiverId(), Types.INTEGER);
            stmt.setString(3, message.getMessage());
            stmt.setInt(4, message.getYop());
            stmt.setBoolean(5, message.isSeen());
            stmt.setTimestamp(6, message.getTimestamp());
            stmt.setInt(7, message.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteMessage(int messageId) throws SQLException {
        String sql = "DELETE FROM messages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, messageId);
            stmt.executeUpdate();
        }
    }

    public void createMessage(Message message) throws SQLException {
        String sql = "INSERT INTO messages (sender_id, receiver_id, message, yop, is_seen) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, message.getSenderId());
            if (message.getReceiverId() != null) {
                stmt.setInt(2, message.getReceiverId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, message.getMessage());
            stmt.setInt(4, message.getYop());
            stmt.setBoolean(5, message.isSeen());
            stmt.executeUpdate();
        }
    }
}
