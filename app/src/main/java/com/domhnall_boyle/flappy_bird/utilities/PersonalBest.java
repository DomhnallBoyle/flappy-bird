package com.domhnall_boyle.flappy_bird.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.domhnall_boyle.flappy_bird.engine.rest.bodies.HighScoreBody;
import com.domhnall_boyle.flappy_bird.engine.rest.repositories.UserRespository;
import com.domhnall_boyle.flappy_bird.models.User;

public class PersonalBest {

    private SharedPreferences sharedPreferences;
    private int personalBest;
    private boolean newPersonalBest;

    public PersonalBest() {
        this.sharedPreferences = MyApplication.getCurrentActivity().getPreferences(Context.MODE_PRIVATE);

        // read from shared preferences
        this.personalBest = this.sharedPreferences.getInt("personalBest", -1);
        this.newPersonalBest = false;
    }

    public void update(int score) {
        if (score > this.personalBest) {
            this.personalBest = score;
            this.newPersonalBest = true;

            // write to shared preferences
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt("personalBest", this.personalBest);
            editor.apply();

            if (User.getInstance().isLoggedIn()) {
                new UserRespository().updateHighScore(new HighScoreBody(this.personalBest));
            }
        } else {
            if (User.getInstance().isLoggedIn()) {
                if (this.personalBest > User.getInstance().getHighScore()) {
                    new UserRespository().updateHighScore(new HighScoreBody(this.personalBest));
                    User.getInstance().setHighScore(this.personalBest);
                }
            }
            this.newPersonalBest = false;
        }
    }

    public int getPersonalBest() {
        return this.personalBest;
    }

    public boolean isNewPersonalBest() {
        return this.newPersonalBest;
    }
}
