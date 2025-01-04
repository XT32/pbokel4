package service;

import dao.UserDAO;
import model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User loginUser(String username, String password) {
        try {
            return userDAO.loginUser(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during login: " + e.getMessage());
        }
    }

    public boolean registerUser(User user) {
        try {
            if (userDAO.isUsernameExists(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists.");
            }
            if (userDAO.isEmailExists(user.getEmail())) {
                throw new IllegalArgumentException("Email already exists.");
            }
            return userDAO.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during registration: " + e.getMessage());
        }
    }
}
