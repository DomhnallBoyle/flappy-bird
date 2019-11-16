package com.domhnall_boyle.flappy_bird.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.io.TouchType;
import com.domhnall_boyle.flappy_bird.engine.managers.AssetManager;

import java.util.List;

public class Player extends GameObject {

    private final int UP_SKIP = -75;
    private final int DOWN_SKIP = 4;
    private final int FLAP_DELAY = 25;

    private int delay = 0, flapDelay = 0;
    private int bitmapFlag = 0;
    private String playerColour;
    private int rotation;
    private boolean onSurface = false;

    public Player(String playerColour) {
        super(playerColour + "BIRD_MIDFLAP",
                Scale.getX(50), Scale.getY(40),
                Scale.getX(60), Scale.getY(45));
        this.playerColour = playerColour;
    }

    @Override
    public void draw(IGraphics2D graphics2D) {
        graphics2D.drawBitmap(this.bitmap, this.rotateBitmap(), null);
    }

    public void update(List<TouchEvent> touchEvents, Surface[] surfaces, boolean gameOver) {
        if (!gameOver) {
            // update the player's bitmap to show flapping
            // only updates every 25 updates
            if (this.flapDelay == FLAP_DELAY) {
                switch (this.bitmapFlag) {
                    case 0:
                        this.bitmapFlag += 1;
                        this.bitmap = getBitmap("UPFLAP");
                        break;
                    case 1:
                        this.bitmapFlag += 1;
                        this.bitmap = getBitmap("MIDFLAP");
                        break;
                    case 2:
                        this.bitmapFlag = 0;
                        this.bitmap = getBitmap("DOWNFLAP");
                        break;
                }
                this.flapDelay = 0;
            } else {
                this.flapDelay += 1;
            }

            // update player based on touch events
            if (touchEvents.size() > 0) {
                TouchEvent touchEvent = touchEvents.get(0);
                if (touchEvent.getType() == TouchType.TOUCH_DOWN) {
                    this.setPosition(this.centre.getX(), this.centre.getY() + UP_SKIP);
                    this.rotation = -35;
                    this.delay = 25;
                }
            } else {
                if (this.delay <= 0) {
                    this.setPosition(this.centre.getX(), this.centre.getY() + DOWN_SKIP);
                }

                this.delay -= 1;

                if (this.delay <= -50) {
                    if (this.rotation <= 90) {
                        this.rotation += 2;
                    }
                }
            }
        } else {
            // TODO: Multiply by increasing delta to speed up death drop
            if (!this.onSurface) {
                this.setPosition(this.centre.getX(), this.centre.getY() + DOWN_SKIP);

                if (this.rotation <= 90) {
                    this.rotation += 10;
                }
            }
        }

        // make sure player can't go above/below screen
        if (this.rect.top <= 0) {
            setPosition(this.rect.left, 0,
                    this.rect.left + this.rect.width(), this.rect.height());
        }
        for (Surface surface: surfaces) {
            if (this.rect.bottom > surface.rect.top) {
                this.onSurface = true;
            }
        }
    }

    private Bitmap getBitmap(String flapType) {
        return AssetManager.getInstance().getBitmap(this.playerColour + "BIRD_" + flapType);
    }

    private Matrix rotateBitmap() {
        Matrix m = new Matrix();
        m.setScale(3, 3);
        m.postRotate(this.rotation, this.bitmap.getWidth() / 2, this.bitmap.getHeight() / 2);
        m.postTranslate(this.centre.getX(), this.centre.getY());

        return m;
    }
}
