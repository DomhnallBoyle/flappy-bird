package com.domhnall_boyle.flappy_bird.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.domhnall_boyle.flappy_bird.game.Game;

public class GameFragment extends Fragment {

    protected Game game;

    public GameFragment(Game game) {
        this.game = game;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return this.game.getView();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.game.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.game.pause();
    }
}
