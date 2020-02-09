package com.domhnall_boyle.flappy_bird.models;

public class User {

    private static User user;
    private String id;
    private String username;
    private String accessToken;
    private String refreshToken;
    private int highScore;
    private boolean loggedIn;

    private User() {
        this.loggedIn = false;
    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }

        return user;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
