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
    protected int x1, y1, x2, y2;
    protected Vect position = new Vect();

    public GameObject(Bitmap bitmap, int x1, int y1, int x2, int y2) {
        this.bitmap = bitmap;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.setPosition(x1, y1, x2, y2);
    }

    public GameObject(String bitmap, int x1, int y1, int x2, int y2) {
        this.bitmap = this.assetManager.getBitmap(bitmap);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.setPosition(x1, y1, x2, y2);
    }

    public void draw(IGraphics2D graphics2D) {
        graphics2D.drawBitmap(this.bitmap, null, this.rect, null);
    }

    public void update() {}

    public Vect getPosition() {
        return this.position;
    }

    public void setPosition(float x, float y) {
        this.position.update(x, y);
        this.rect.set((int) x - this.rect.width() / 2,
                (int) y - this.rect.height() / 2,
                (int) x + this.rect.width() / 2,
                (int) y + this.rect.height() / 2);
    }

    public void setPosition(int x1, int y1, int x2, int y2) {
        this.position.update(x1 + ((x2 - x1) / 2), y1 + ((y2 - y1) / 2));
        this.rect.set(x1, y1, x2, y2);
    }

    public Rect getRect() {
        return this.rect;
    }
}
