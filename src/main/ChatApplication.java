package main;


import main.controller.MessageController;
import main.controller.UserController;
import main.dao.MessageDao;
import main.dao.UserDao;
import main.model.Message;
import main.model.User;
import main.service.MessageService;
import main.service.UserService;
import main.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ChatApplication {

    public static void main(String[] args) {
        // Initialize the database connection
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (Exception e) {
            System.out.println("Error while establishing the database connection: " + e.getMessage());
            return; // Exit the application if connection fails
        }

        // Initialize DAOs, Services, and Controllers
        UserDao userDao = new UserDao(connection);
        MessageDao messageDao = new MessageDao(connection);

        UserService userService = new UserService(userDao);
        MessageService messageService = new MessageService(messageDao);

        UserController userController = new UserController(userService);
        MessageController messageController = new MessageController(messageService);

        Scanner scanner = new Scanner(System.in);

        // Application Menu
        while (true) {
            System.out.println("\n===== Chat Application Menu =====");
            System.out.println("1. Create User");
            System.out.println("2. List All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Send Message");
            System.out.println("8. Delete a Message");
            System.out.println("9. View All Users");
            System.out.println("10. Send Global Message");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            try {
                switch (choice) {
                    case 1: // Create User
                        System.out.println("Enter user details: name, email, password, enroll, gender, course, yop");
                        String name = scanner.nextLine();
                        String email = scanner.nextLine();
                        String password = scanner.nextLine();
                        String enroll = scanner.nextLine();
                        String gender = scanner.nextLine();
                        String course = scanner.nextLine();
                        int yop = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        userController.addUser(name, email, password, enroll, gender, course, yop);
                        System.out.println("User added successfully.");
                        break;

                    case 2: // List All Users
                        System.out.println("All Users:");
                        userController.listUsers().forEach(System.out::println);
                        break;

                    case 3: // Update User
                        System.out.println("Enter the user ID to update:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter updated details: name, email, password, enroll, gender, course, yop");
                        String updatedName = scanner.nextLine();
                        String updatedEmail = scanner.nextLine();
                        String updatedPassword = scanner.nextLine();
                        String updatedEnroll = scanner.nextLine();
                        String updatedGender = scanner.nextLine();
                        String updatedCourse = scanner.nextLine();
                        int updatedYop = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        userController.updateUser(updateId, updatedName, updatedEmail, updatedPassword, updatedEnroll, updatedGender, updatedCourse, updatedYop);
                        System.out.println("User updated successfully.");
                        break;

                    case 4: // Delete User
                        System.out.println("Enter the user ID to delete:");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        userController.deleteUser(deleteId);
                        System.out.println("User deleted successfully.");
                        break;

                    case 5: // Send Message
                        System.out.println("Enter sender ID, receiver ID (or 0 for global message), message content, and yop:");
                        int senderId = scanner.nextInt();
                        int receiverId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        String messageContent = scanner.nextLine();
                        int messageYop = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Message newMessage = new Message();
                        newMessage.setSenderId(senderId);
                        newMessage.setReceiverId(receiverId == 0 ? null : receiverId);
                        newMessage.setMessage(messageContent);
                        newMessage.setYop(messageYop);
                        messageService.addMessage(newMessage);
                        System.out.println("Message sent successfully!");
                        break;


                    case 8: // Delete a Message
                        System.out.println("Enter message ID to delete:");
                        int deleteMessageId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        messageController.deleteMessage(deleteMessageId);
                        System.out.println("Message deleted successfully.");
                        break;

                    case 9:
                        System.out.println("Fetching all users...");
                        List<User> allUsers = userService.getAllUsers();
                        for (User u : allUsers) {
                            System.out.println(u);
                        }
                        break;

                    case 10: // Send Global Message
                        System.out.println("Enter message details: senderId, message, yop");
                        int globalSenderId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        String globalMessage = scanner.nextLine();
                        int globalYop = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        messageController.sendMessage(globalSenderId, null, globalMessage, globalYop);
                        System.out.println("Global message sent successfully.");
                        break;

                    case 11: // Exit
                        System.out.println("Exiting application. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("An error occurred while processing your request: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
