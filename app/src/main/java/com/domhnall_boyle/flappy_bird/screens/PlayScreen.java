package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.Background;
import com.domhnall_boyle.flappy_bird.objects.DualPipe;
import com.domhnall_boyle.flappy_bird.objects.GameObject;
import com.domhnall_boyle.flappy_bird.objects.Player;
import com.domhnall_boyle.flappy_bird.objects.ScoreCounter;
import com.domhnall_boyle.flappy_bird.objects.Surface;

public class PlayScreen extends GameScreen {

    private final int X_OFFSET = 1;

    private Background background;
    private ScoreCounter scoreCounter;
    private Player player;
    private Surface[] surfaces;
    private DualPipe[] dualPipes;
    private boolean gameOver = false;

    public PlayScreen(Activity activity, Game game) {
        super(activity, game);

        this.scoreCounter = new ScoreCounter();
        this.background = new Background("DAY");
        this.surfaces = new Surface[] {
                new Surface(),
                new Surface(this.width, Scale.getY(85), this.width * 2, this.height)
        };
        this.player = new Player("YELLOW");
        this.dualPipes = new DualPipe[] {
                new DualPipe("GREEN"),
                null
        };

        // null == DualPipe 2
        this.addGameObjects(new GameObject[] {this.background, this.dualPipes[0], null,
                this.surfaces[0], this.surfaces[1], this.scoreCounter, this.player});
    }

    @Override
    void _update() {
        this.player.update(this.game.getTouchEvents(), this.surfaces, gameOver);

        if (!this.gameOver) {
            for (Surface surface: this.surfaces) {
                surface.update();
            }

            this.scoreCounter.update();

            // if player passes first dual pipe, create a new dual pipe
            if (this.player.getCentre().getX() + X_OFFSET == dualPipes[0].getCentre().getX()) {
                DualPipe dualPipe = new DualPipe("GREEN");
                this.dualPipes[1] = dualPipe;
                this.gameObjects.set(2, dualPipe);
            }

            for (DualPipe dualPipe: this.dualPipes) {
                if (dualPipe != null) {
                    dualPipe.update();

                    // update score if past a dual pipe
                    if (this.player.getCentre().getX() + X_OFFSET == dualPipe.getCentre().getX()) {
                        this.scoreCounter.updateScore();
                    }

                    // check for collisions
                    if (dualPipe.intersects(this.player)) {
                        this.gameOver = true;
                    }
                }
            }
        } else {
            // show gameover screen with buttons
        }

        this.game.resetAccumulators();
    }
}
