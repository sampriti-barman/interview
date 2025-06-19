package com.example.interview.model;

public class AuthenticationResponse {

    private String userName;
    private String password;
    private String token;

    public AuthenticationResponse(String token, String password, String userName) {
        this.token = token;
        this.password = password;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
