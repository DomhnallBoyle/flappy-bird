package com.domhnall_boyle.flappy_bird.game;

import com.domhnall_boyle.flappy_bird.engine.graphics.CanvasRenderSurface;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;
import com.domhnall_boyle.flappy_bird.screens.GameScreen;

public class GameLoop implements Runnable {

    private long fps = 60;
    private boolean mIsRunning;
    private CanvasRenderSurface canvasRenderSurface;
    private ScreenManager screenManager;
    private Thread runner;

    public GameLoop(CanvasRenderSurface canvasRenderSurface) {
        this.canvasRenderSurface = canvasRenderSurface;
        this.mIsRunning = true;
        this.screenManager = ScreenManager.getInstance();
    }

    @Override
    public void run() {
        while(this.mIsRunning) {
            long startFrameTime = System.currentTimeMillis();

            draw();
            update();

            long timeTookThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeTookThisFrame >= 1) {
                fps = 1000 / timeTookThisFrame;
            }
        }
    }

    private void draw() {
        GameScreen gameScreen = this.screenManager.getCurrentScreen();
        this.canvasRenderSurface.render(gameScreen);
    }

    private void update() {
        GameScreen gameScreen = this.screenManager.getCurrentScreen();
        gameScreen.update();
    }

    public void resume() {
        this.mIsRunning = true;
        this.runner = new Thread(this);
        this.runner.start();
    }

    public void pause() {
        this.mIsRunning = false;
    }
}
