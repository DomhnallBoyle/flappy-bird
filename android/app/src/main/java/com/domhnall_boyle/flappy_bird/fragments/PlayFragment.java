package com.domhnall_boyle.flappy_bird.fragments;

import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.screens.PlayScreen;

public class PlayFragment extends GameFragment {

    private String backgroundType, playerColour;

    public PlayFragment(Game game, String backgroundType, String playerColour) {
        super(game);
        this.backgroundType = backgroundType;
        this.playerColour = playerColour;
    }

    @Override
    public void onStart() {
        super.onStart();
        new PlayScreen(this, this.game, this.backgroundType, this.playerColour);
    }
}
