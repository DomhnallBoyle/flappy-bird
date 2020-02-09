package com.domhnall_boyle.flappy_bird.game;

import android.content.Context;
import android.view.View;

import com.domhnall_boyle.flappy_bird.engine.graphics.CanvasRenderSurface;
import com.domhnall_boyle.flappy_bird.engine.io.Input;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;

import java.util.List;

public class Game {

    private GameLoop gameLoop;
    private CanvasRenderSurface canvasRenderSurface;
    private Input input;

    public Game(Context context) {
        this.canvasRenderSurface = new CanvasRenderSurface(context);
        this.gameLoop = new GameLoop(this.canvasRenderSurface);
        this.input = new Input(this.canvasRenderSurface);
    }

    public void pause() {
        this.gameLoop.pause();
    }

    public void resume() {
        this.gameLoop.resume();
    }

    public View getView() {
        return this.canvasRenderSurface;
    }

    public static int getScreenWidth() {
        return ScreenManager.getInstance().getCurrentScreen().getWidth();
    }

    public static int getScreenHeight() {
        return ScreenManager.getInstance().getCurrentScreen().getHeight();
    }

    public List<TouchEvent> getTouchEvents() {
        return this.input.getTouchEvents();
    }

    public void resetAccumulators() {
        this.input.resetAccumulators();
    }
}
