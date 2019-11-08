package com.domhnall_boyle.flappy_bird.engine.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class CanvasGraphics2D implements IGraphics2D {

    private Canvas canvas;
    private int width, height;

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
    }

    @Override
    public int getSurfaceWidth() {
        return this.width;
    }

    @Override
    public int getSurfaceHeight() {
        return this.height;
    }

    @Override
    public void clipRect(Rect clipRegion) {
        this.canvas.clipRect(clipRegion);
    }

    @Override
    public void clear(int colour) {
        this.canvas.drawRGB((colour & 0xff0000) >> 16, (colour & 0xff00) >> 8, (colour & 0xff));
    }

    @Override
    public void drawRect(Rect rect, Paint paint) {
        this.canvas.drawRect(rect, paint);
    }

    @Override
    public void drawText(String text, float x, float y, Paint paint) {
        this.canvas.drawText(text, x, y, paint);
    }

    @Override
    public void drawBitmap(Bitmap bitmap, float x, float y, Paint paint) {
        this.canvas.drawBitmap(bitmap, x, y, paint);
    }

    @Override
    public void drawBitmap(Bitmap bitmap, Rect srcRect, Rect desRect, Paint paint) {
        this.canvas.drawBitmap(bitmap, srcRect, desRect, paint);
    }

    @Override
    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        this.canvas.drawBitmap(bitmap, matrix, paint);
    }
}
