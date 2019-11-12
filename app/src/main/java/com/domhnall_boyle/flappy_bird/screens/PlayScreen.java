package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.Background;
import com.domhnall_boyle.flappy_bird.objects.DualPipe;
import com.domhnall_boyle.flappy_bird.objects.GameObject;
import com.domhnall_boyle.flappy_bird.objects.Player;
import com.domhnall_boyle.flappy_bird.objects.ScoreCounter;
import com.domhnall_boyle.flappy_bird.objects.Surface;

import java.util.ArrayList;

public class PlayScreen extends GameScreen {

    private Background background;
    private ScoreCounter scoreCounter;
    private Player player;
    private Surface surface1, surface2;
    private DualPipe dualPipe;

    public PlayScreen(Activity activity, Game game) {
        super(activity, game);

        this.scoreCounter = new ScoreCounter();
        this.background = new Background("BACKGROUND_DAY");
        this.surface1 = new Surface();
        this.surface2 = new Surface(this.width, Scale.getY(85), this.width * 2, this.height);
        this.player = new Player("YELLOWBIRD_MIDFLAP");
        this.dualPipe = new DualPipe("GREEN");

        this.addGameObjects(new GameObject[] {this.background, this.dualPipe, this.surface1,
                this.surface2, this.scoreCounter, this.player});
    }

    @Override
    void _update() {
        this.surface1.update();
        this.surface2.update();
        this.scoreCounter.update();
        this.dualPipe.update();
    }
}
