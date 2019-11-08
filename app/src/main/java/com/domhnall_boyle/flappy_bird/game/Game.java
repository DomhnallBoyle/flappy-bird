package com.domhnall_boyle.flappy_bird.game;

import android.content.Context;
import android.view.View;

import com.domhnall_boyle.flappy_bird.engine.graphics.CanvasRenderSurface;
import com.domhnall_boyle.flappy_bird.engine.graphics.IRenderSurface;
import com.domhnall_boyle.flappy_bird.engine.io.FileIO;
import com.domhnall_boyle.flappy_bird.engine.managers.AssetManager;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;

public class Game {

    private GameLoop gameLoop;
    private CanvasRenderSurface canvasRenderSurface;

    public Game(Context context) {
        this.canvasRenderSurface = new CanvasRenderSurface(context);
        this.gameLoop = new GameLoop(this.canvasRenderSurface);
    }

    public void pause() {
        this.gameLoop.pause();
    }

    public void resume() {
        this.gameLoop.resume();
    }

    public View getView() {
        return this.canvasRenderSurface.getAsView();
    }

    public static int getScreenWidth() {
        return ScreenManager.getInstance().getCurrentScreen().getWidthPixels();
    }

    public static int getScreenHeight() {
        return ScreenManager.getInstance().getCurrentScreen().getHeightPixels();
    }
}
