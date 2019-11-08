package com.domhnall_boyle.flappy_bird.engine.graphics;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public interface IGraphics2D {

    int getSurfaceWidth();
    int getSurfaceHeight();

    void clipRect(Rect clipRegion);
    void clear(int colour);
    void drawRect(Rect rect, Paint paint);
    void drawText(String text, float x, float y, Paint paint);
    void drawBitmap(Bitmap bitmap, float x, float y, Paint paint);
    void drawBitmap(Bitmap bitmap, Rect srcRect, Rect desRect, Paint paint);
    void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint);
}
