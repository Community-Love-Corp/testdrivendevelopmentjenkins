package com.blessing.tdd4.model;

public class Credential {
    
    private String username;
 
    private String password;

    public Credential() {
    }
 
    public Credential(String username, String Password) {
        this.username = username;
        this.password = Password;
    }

       public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Credential [Username=" + username + ", Password=" + password + "]";
    }


}
