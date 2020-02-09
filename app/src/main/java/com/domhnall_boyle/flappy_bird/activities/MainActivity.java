package com.domhnall_boyle.flappy_bird.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.domhnall_boyle.flappy_bird.R;
import com.domhnall_boyle.flappy_bird.fragments.IntroductionFragment;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Game game = new Game(getApplicationContext());

        MyApplication.changeFragment(new IntroductionFragment(game), false);
    }
}
