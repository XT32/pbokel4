package service;

import dao.UserDAO;
import model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    /**
     * Login user dengan username dan password.
     *
     * @param username Username pengguna.
     * @param password Password pengguna.
     * @return User jika berhasil login, null jika tidak.
     */
    public User loginUser(String username, String password) {
        try {
            User user = userDAO.loginUser(username, password);
            if (user == null) {
                throw new IllegalArgumentException("Invalid username or password.");
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during login: " + e.getMessage());
        }
    }

    /**
     * Registrasi user baru.
     *
     * @param user Objek User yang akan diregistrasi.
     * @return true jika registrasi berhasil.
     */
    public boolean registerUser(User user) {
        try {
            // Validasi apakah username sudah digunakan
            if (userDAO.isUsernameExists(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists.");
            }

            // Validasi apakah email sudah digunakan
            if (userDAO.isEmailExists(user.getEmail())) {
                throw new IllegalArgumentException("Email already exists.");
            }

            // Lakukan registrasi
            userDAO.registerUser(user);
            return true; // Jika berhasil, kembalikan true
        } catch (IllegalArgumentException e) {
            // Menangani error validasi input (misalnya username atau email sudah digunakan)
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during registration: " + e.getMessage());
        }
    }
}
