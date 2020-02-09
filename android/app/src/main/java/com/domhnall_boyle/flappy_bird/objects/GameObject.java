package com.domhnall_boyle.flappy_bird.objects;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.io.TouchType;
import com.domhnall_boyle.flappy_bird.engine.managers.AssetManager;
import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.utilities.Vect;

import java.util.List;

public class GameObject {

    protected final int X_SKIP = 2;

    protected AssetManager assetManager = AssetManager.getInstance();
    protected Rect rect = new Rect();
    protected Bitmap bitmap;
    protected int initialX1, initialY1, initialX2, initialY2;
    protected Vect centre = new Vect();
    protected boolean visible = true;

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

    public GameObject(int x1, int y1, int x2, int y2, boolean visible) {
        this.initialX1 = x1;
        this.initialY1 = y1;
        this.initialX2 = x2;
        this.initialY2 = y2;
        this.setPosition(x1, y1, x2, y2);
        this.visible = visible;
    }

    public void draw(IGraphics2D graphics2D) {
        if (this.visible) {
            graphics2D.drawBitmap(this.bitmap, null, this.rect, null);
        }
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

    public boolean intersects(GameObject gameObject) {
        return Rect.intersects(this.rect, gameObject.rect);
    }

    public void setIsShowing(boolean isShowing) {
        this.visible = isShowing;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = this.assetManager.getBitmap(bitmap);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public boolean isPressed(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            TouchEvent touchEvent = touchEvents.get(0);
            if (touchEvent.getType() == TouchType.TOUCH_DOWN) {
                return this.rect.contains((int) touchEvent.getX(), (int) touchEvent.getY());
            }
        }

        return false;
    }
}
