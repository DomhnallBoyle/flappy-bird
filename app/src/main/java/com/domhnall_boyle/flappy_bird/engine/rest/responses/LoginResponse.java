package com.domhnall_boyle.flappy_bird.engine.rest.responses;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("high_score")
    private int highScore;

    public String getUserId() { return this.userId; }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public int getHighScore() { return this.highScore; }
}
