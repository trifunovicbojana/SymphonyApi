package apimodels.login;

import apimodels.User;

public class LoginResponse {
    private String token;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    private User user;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }









}
