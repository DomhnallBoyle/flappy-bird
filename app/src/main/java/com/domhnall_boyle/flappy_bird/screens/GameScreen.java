package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameScreen {

    // TODO: protected List<GameObject> gameObjects; ???
    private DisplayMetrics displayMetrics;
    protected int width, height;
    protected Activity activity;
    protected Game game;
    protected List<GameObject> gameObjects;

    public GameScreen(Activity activity, Game game) {
        this.activity = activity;
        this.game = game;
        this.displayMetrics = new DisplayMetrics();
        this.gameObjects = new ArrayList<>();

        // TODO: Not sure I need this - getWidth() and getHeight() functions
        activity.getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        this.width = this.displayMetrics.widthPixels;
        this.height = this.displayMetrics.heightPixels;
        ScreenManager.getInstance().setCurrentScreen(this);
    }

    public void draw(IGraphics2D graphics2D) {
        for (GameObject gameObject: this.gameObjects) {
            gameObject.draw(graphics2D);
        }
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

    protected void addGameObjects(GameObject[] gameObjects) {
        this.gameObjects.addAll(Arrays.asList(gameObjects));
    }

    abstract void _update();
}
