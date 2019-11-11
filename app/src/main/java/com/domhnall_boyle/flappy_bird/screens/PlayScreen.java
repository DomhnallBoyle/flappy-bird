package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.Background;

public class PlayScreen extends GameScreen {

    private Background background;

    public PlayScreen(Activity activity, Game game) {
        super(activity, game);

        this.background = new Background("BACKGROUND_DAY");
    }

    @Override
    void _draw(IGraphics2D iGraphics2D) {
        this.background.draw(iGraphics2D);
    }

    @Override
    void _update() {

    }
}
