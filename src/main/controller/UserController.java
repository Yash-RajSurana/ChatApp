package main.controller;
import java.sql.SQLException;
import java.util.List;

import main.model.User;
import main.service.UserService;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(String name, String email, String password, String enroll, String gender, String course, int yop) throws SQLException {
        // Creating a User object to pass to the service layer
        User newUser = new User(0, name, email, password, enroll, gender, course, yop);
        userService.addUser(newUser);
    }

    public List<User> listUsers() throws SQLException {
        return userService.getAllUsers();
    }

    public void updateUser(int userId, String name, String email, String password, String enroll, String gender, String course, int yop) throws SQLException {
        User updatedUser = new User(userId, name, email, password, enroll, gender, course, yop);
        userService.updateUser(updatedUser);
    }

    public void deleteUser(int userId) throws SQLException {
        userService.deleteUser(userId);
    }

//    public List<User> listUsersByYOP(int yop) throws SQLException {
//        return userService.getUsersByYOP(yop);
//    }
}
