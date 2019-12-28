package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.io.TouchType;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.Background;
import com.domhnall_boyle.flappy_bird.objects.Button;
import com.domhnall_boyle.flappy_bird.objects.DualPipe;
import com.domhnall_boyle.flappy_bird.objects.GameObject;
import com.domhnall_boyle.flappy_bird.objects.GameoverMenu;
import com.domhnall_boyle.flappy_bird.objects.Player;
import com.domhnall_boyle.flappy_bird.objects.ScoreCounter;
import com.domhnall_boyle.flappy_bird.objects.StaticObject;
import com.domhnall_boyle.flappy_bird.objects.Surface;

import java.util.List;

public class PlayScreen extends GameScreen {

    private final int X_OFFSET = 1;

    private Background background;
    private ScoreCounter scoreCounter;
    private Player player;
    private Surface[] surfaces;
    private DualPipe[] dualPipes;
    private StaticObject intro;
    private GameoverMenu gameoverMenu;
    private Button pause;
    private boolean started = false, gameOver = false, paused = false;

    public PlayScreen(Activity activity, Game game, String backgroundType, String playerColour) {
        super(activity, game);

        this.scoreCounter = new ScoreCounter();
        this.background = new Background(backgroundType);
        this.surfaces = new Surface[] {
                new Surface(),
                new Surface(this.width, Scale.getY(85), this.width * 2, this.height)
        };
        this.player = new Player(playerColour);
        this.dualPipes = new DualPipe[] {
                new DualPipe("GREEN"),
                null
        };
        this.intro = new StaticObject("INTRO", Scale.getX(25), Scale.getY(25), Scale.getX(80), Scale.getY(60));
        this.pause = new Button("PAUSE", Scale.getX(5), Scale.getY(5), Scale.getX(15), Scale.getY(10));
        this.pause.setIsShowing(false);

        this.gameoverMenu = new GameoverMenu();

        // null == DualPipe 2
        this.addGameObjects(new GameObject[] {this.background, this.dualPipes[0], null,
            this.surfaces[0], this.surfaces[1], this.scoreCounter, this.player, this.intro,
            this.pause, this.gameoverMenu
        });
    }

    @Override
    void _update() {
        // get the game touch events
        List<TouchEvent> touchEvents = this.game.getTouchEvents();

        // check if paused pressed
        if (this.pause.isPressed(touchEvents)) {
            if (this.paused) {
                this.pause.setBitmap("PAUSE");
                this.paused = false;
                touchEvents.clear();
            } else {
                this.pause.setBitmap("PLAY");
                this.paused = true;
            }
        }

        if (!this.paused) {
            this.player.update(touchEvents, this.surfaces, this.started, this.gameOver);

            // update surfaces and check if collisions
            if (!this.gameOver) {
                for (Surface surface: this.surfaces) {
                    surface.update();

                    if (surface.intersects(this.player)) {
                        this.gameOver = true;
                        this.assetManager.playSound("HIT");
                    }
                }
            }

            if (this.started) {
                if (!this.gameOver) {
                    this.scoreCounter.update();

                    // if player passes first dual pipe, create a new dual pipe
                    if (dualPipes[0].getCentre().getX() + X_OFFSET == Scale.getX(55)) {
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
                                this.assetManager.playSound("POINT");
                            }

                            // check for collisions
                            if (dualPipe.intersects(this.player)) {
                                this.assetManager.playSound("HIT");
                                this.assetManager.playSound("DIE");
                                this.gameOver = true;
                            }
                        }
                    }
                } else {
                    if (this.player.isDead()) {
                        // show game over menu with buttons
                        this.pause.setIsShowing(false);
                        this.scoreCounter.setIsShowing(false);

                        this.gameoverMenu.setScore(this.scoreCounter.getScore());
                        this.gameoverMenu.setIsShowing(true);
                        if (this.gameoverMenu.getStartButton().isPressed(touchEvents)) {
                            new PlayScreen(this.activity, this.game, this.background.getBackgroundType(), this.player.getPlayerColour());
                        }
                    }
                }
            } else {
                // game hasn't started yet, check for touches to screen
                if (this.background.isPressed(touchEvents)) {
                    this.started = true;
                    this.intro.setIsShowing(false);
                    this.pause.setIsShowing(true);
                }
            }
        }

        this.game.resetAccumulators();
    }
}
