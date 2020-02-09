package com.domhnall_boyle.flappy_bird.engine.rest.bodies;

import com.google.gson.annotations.SerializedName;

public class HighScoreBody {

    @SerializedName("high_score")
    public int highScore;

    public HighScoreBody(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return this.highScore;
    }
}
