package com.domhnall_boyle.flappy_bird.engine.rest.bodies;

public class LoginBody {

    private String username;
    private String password;

    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }
}
