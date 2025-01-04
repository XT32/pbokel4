package model;

import java.sql.Timestamp;

public class admin {
    private String username = "a";
    private String password = "a";

    public admin() {
    }
    
    public admin(int idUSer,String tipePengguna){
    }

    public boolean isValidAdmin(String enteredUsername, String enteredPassword) {
    return enteredUsername.equals(username) && enteredPassword.equals(password);
    }
    
}