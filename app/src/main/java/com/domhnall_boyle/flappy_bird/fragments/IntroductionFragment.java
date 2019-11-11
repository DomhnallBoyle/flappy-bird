package com.domhnall_boyle.flappy_bird.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.screens.IntroductionScreen;

public class IntroductionFragment extends Fragment {

    private Game game;
    private IntroductionScreen introductionScreen;

    public IntroductionFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.game = new Game(this.getContext());
        this.introductionScreen = new IntroductionScreen(this.getActivity(), this.game);

        return this.game.getView();
    }

    @Override
    public void onResume() {
        super.onResume();
        game.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        game.pause();
    }
}
