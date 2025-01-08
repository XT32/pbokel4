package service;

import dao.UserDAO;
import java.sql.SQLException;
import model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User loginUser(String username, String password) {
        try {
            User user = userDAO.loginUser(username, password);
            if (user == null) {
                throw new IllegalArgumentException("Invalid username or password.");
            }
            return user;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during login: " + e.getMessage());
        }
    }

    public boolean registerUser(User user) {
        try {
            validateUserRegistration(user);
            userDAO.registerUser(user);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during registration: " + e.getMessage());
        }
    }

    private void validateUserRegistration(User user) throws SQLException {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (userDAO.isUsernameExists(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (userDAO.isEmailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
    }
}
