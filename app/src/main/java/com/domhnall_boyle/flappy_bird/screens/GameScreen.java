package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;
import android.util.DisplayMetrics;

import androidx.fragment.app.Fragment;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.managers.AssetManager;
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
    protected Fragment fragment;
    protected Game game;
    protected List<GameObject> gameObjects;
    protected AssetManager assetManager = AssetManager.getInstance();
    protected boolean initialised = false;

    public GameScreen(Fragment fragment, Game game) {
        this.fragment = fragment;
        this.activity = fragment.getActivity();
        this.game = game;
        this.displayMetrics = new DisplayMetrics();
        this.gameObjects = new ArrayList<>();

        // TODO: Not sure I need this - getWidth() and getHeight() functions
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        this.width = this.displayMetrics.widthPixels;
        this.height = this.displayMetrics.heightPixels;
        ScreenManager.getInstance().setCurrentScreen(this);
    }

    public void draw(IGraphics2D graphics2D) {
        synchronized (this.gameObjects) {
            for (GameObject gameObject: this.gameObjects) {
                if (gameObject != null) {
                    gameObject.draw(graphics2D);
                }
            }
        }
    }

    public void update() {
        if (this.initialised) {
            this._update();
        }
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
