package service;

import dao.UserDAO;
import model.User;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        return userDAO.addUser(user);
    }

    public User loginUser(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }
}
