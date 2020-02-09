package com.domhnall_boyle.flappy_bird.engine.rest.responses;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    private String username;

    @SerializedName("high_score")
    private int highscore;

    public String getUsername() {
        return this.username;
    }

    public String getHighscore() {
        return String.valueOf(this.highscore);
    }
}
