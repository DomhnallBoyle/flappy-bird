package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;
import com.domhnall_boyle.flappy_bird.game.Game;

public abstract class GameScreen {

    // TODO: protected List<GameObject> gameObjects; ???
    private DisplayMetrics displayMetrics;
    protected int width, height;
    protected Activity activity;
    protected Game game;

    public GameScreen(Activity activity, Game game) {
        this.activity = activity;
        this.game = game;
        this.displayMetrics = new DisplayMetrics();

        // TODO: Not sure I need this - getWidth() and getHeight() functions
        activity.getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        this.width = this.displayMetrics.widthPixels;
        this.height = this.displayMetrics.heightPixels;
        ScreenManager.getInstance().setCurrentScreen(this);
    }

    public void draw(IGraphics2D graphics2D) {
        this._draw(graphics2D);
    }

    public void update() {
        this._update();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    abstract void _draw(IGraphics2D iGraphics2D);
    abstract void _update();
}
