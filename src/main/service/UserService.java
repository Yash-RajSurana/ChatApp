package main.service;

import main.dao.UserDao;
import main.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) throws SQLException {
        userDao.createUser(user);
    }

    public User getUserById(int userId) throws SQLException {
        return userDao.getUserById(userId);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void updateUser(User user) throws SQLException {
        userDao.updateUser(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userDao.deleteUser(userId);
    }
}
