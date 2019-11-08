package com.domhnall_boyle.flappy_bird.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.domhnall_boyle.flappy_bird.R;
import com.domhnall_boyle.flappy_bird.fragments.IntroductionFragment;
import com.domhnall_boyle.flappy_bird.screens.IntroductionScreen;

public class IntroductionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_introduction, new IntroductionFragment());
        fragmentTransaction.commit();
    }

}
