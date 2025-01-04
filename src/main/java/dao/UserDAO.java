package dao;

import utils.baseDAO;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    // Register a new user
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (nama_lengkap, username, alamat, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = baseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getNamaLengkap());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getAlamat());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User registered successfully: " + user.getUsername());
                return true;
            } else {
                logger.error("User registration failed: " + user.getUsername());
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error during user registration: " + e.getMessage(), e);
            return false;
        }
    }

    // Login a user
    public User loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = baseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("nama_lengkap"),
                        rs.getString("username"),
                        rs.getString("alamat"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            logger.error("Error during user login: " + e.getMessage(), e);
        }
        return null;
    }

    // Add a new user (same as registerUser in this case)
    public boolean addUser(User user) {
        return registerUser(user);
    }

    // Get user by username and password (same as loginUser)
    public User getUserByUsernameAndPassword(String username, String password) {
        return loginUser(username, password);
    }

    // Check if a username already exists
    public boolean isUsernameExists(String username) {
        String query = "SELECT 1 FROM users WHERE username = ?";

        try (Connection conn = baseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.error("Error checking username existence: " + e.getMessage(), e);
        }
        return false;
    }

    // Check if an email already exists
    public boolean isEmailExists(String email) {
        String query = "SELECT 1 FROM users WHERE email = ?";

        try (Connection conn = baseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.error("Error checking email existence: " + e.getMessage(), e);
        }
        return false;
    }

    // Update the user profile (functionality not yet supported)
    public boolean updateUserProfile(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
