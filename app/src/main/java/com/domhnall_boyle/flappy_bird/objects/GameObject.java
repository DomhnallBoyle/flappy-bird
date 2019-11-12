package com.domhnall_boyle.flappy_bird.objects;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.managers.AssetManager;
import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.utilities.Vect;

public abstract class GameObject {

    protected AssetManager assetManager = AssetManager.getInstance();
    protected Rect rect = new Rect();
    protected Bitmap bitmap;
    protected int initialX1, initialY1, initialX2, initialY2;
    protected Vect centre= new Vect();

    public GameObject() {}

    public GameObject(Bitmap bitmap, int x1, int y1, int x2, int y2) {
        this.bitmap = bitmap;
        this.initialX1 = x1;
        this.initialY1 = y1;
        this.initialX2 = x2;
        this.initialY2 = y2;
        this.setPosition(x1, y1, x2, y2);
    }

    public GameObject(String bitmap, int x1, int y1, int x2, int y2) {
        this.bitmap = this.assetManager.getBitmap(bitmap);
        this.initialX1 = x1;
        this.initialY1 = y1;
        this.initialX2 = x2;
        this.initialY2 = y2;
        this.setPosition(x1, y1, x2, y2);
    }

    public void draw(IGraphics2D graphics2D) {
        graphics2D.drawBitmap(this.bitmap, null, this.rect, null);
    }

    public void update() {}

    public Vect getCentre() {
        return this.centre;
    }

    public void setPosition(float x, float y) {
        this.centre.update(x, y);
        this.rect.set((int) x - this.rect.width() / 2,
                (int) y - this.rect.height() / 2,
                (int) x + this.rect.width() / 2,
                (int) y + this.rect.height() / 2);
    }

    public void setPosition(int x1, int y1, int x2, int y2) {
        this.centre.update(x1 + ((x2 - x1) / 2), y1 + ((y2 - y1) / 2));
        this.rect.set(x1, y1, x2, y2);
    }

    public Rect getRect() {
        return this.rect;
    }
}
