package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;

public abstract class GameScreen extends SurfaceView {

    // TODO: protected List<GameObject> gameObjects; ???

    private SurfaceHolder surfaceHolder;
    private DisplayMetrics displayMetrics;
    protected int width, height;

    public GameScreen(Activity activity) {
        super(activity.getApplicationContext());
        this.surfaceHolder = getHolder();
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

    public int getWidthPixels() {
        return this.width;
    }

    public int getHeightPixels() {
        return this.height;
    }

    abstract void _draw(IGraphics2D iGraphics2D);
    abstract void _update();
}
