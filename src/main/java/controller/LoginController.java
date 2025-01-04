package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.UserService;
import model.User;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserService userService = new UserService();

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = userService.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful, redirecting...");
        } else {
            System.out.println("Invalid credentials");
        }
    }
}
