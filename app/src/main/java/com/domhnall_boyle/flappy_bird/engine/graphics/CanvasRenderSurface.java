package com.domhnall_boyle.flappy_bird.engine.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.domhnall_boyle.flappy_bird.screens.GameScreen;

public class CanvasRenderSurface extends View implements IRenderSurface {

    private CanvasGraphics2D canvasGraphics2D;
    private GameScreen screenToRender;

    public CanvasRenderSurface(Context context) {
        super(context);
        this.canvasGraphics2D = new CanvasGraphics2D();
    }

    @Override
    public void render(GameScreen gameScreen) {
        this.screenToRender = gameScreen;
        postInvalidate(); // invalidate the View from non-UI threads to redraw the view again
    }

//    @Override
//    public View getAsView() {
//        return this;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvasGraphics2D.setCanvas(canvas);
        this.screenToRender.draw(canvasGraphics2D);
    }
}
