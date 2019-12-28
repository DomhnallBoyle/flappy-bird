package com.domhnall_boyle.flappy_bird.objects;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.io.TouchType;

import java.util.List;

public class Player extends GameObject {

    private final int UP_SKIP = -150;
    private final int DOWN_SKIP = 4;
    private final int FLAP_DELAY = 25;

    private int delay = 0, flapDelay = 0;
    private int bitmapFlag = 0;
    private String playerColour;
    private int rotation;
    private float falling_delta = 0;
    private boolean onSurface = false;

    // TODO: Smoother player movement on tap

    public Player(String playerColour) {
        super(playerColour + "BIRD_MIDFLAP",
                Scale.getX(20), Scale.getY(40),
                Scale.getX(30), Scale.getY(45));
        this.playerColour = playerColour;
    }

    public Player(String playerColour, int x1, int y1, int x2, int y2) {
        super(playerColour + "BIRD_MIDFLAP", x1, y1, x2, y2);
        this.playerColour = playerColour;
    }

    @Override
    public void draw(IGraphics2D graphics2D) {
        graphics2D.drawBitmap(this.bitmap, this.rotateBitmap(), null);
    }

    public void update(List<TouchEvent> touchEvents, Surface[] surfaces, boolean started, boolean gameOver) {
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

            if (started) {
                // update player based on touch events
                if (touchEvents.size() > 0) {
                    TouchEvent touchEvent = touchEvents.get(0);
                    if (touchEvent.getType() == TouchType.TOUCH_DOWN) {
                        this.setPosition(this.centre.getX(), this.centre.getY() + UP_SKIP);
                        this.rotation = -35;
                        this.delay = 25;
                        this.assetManager.playSound("FLAP");
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
            }
        } else {
            if (!this.onSurface) {
                this.setPosition(this.centre.getX(), this.centre.getY() + DOWN_SKIP + this.falling_delta);

                if (this.rotation <= 90) {
                    this.rotation += 10;
                }

                falling_delta += 0.1;
            }
        }

        // make sure player can't go above/below screen
        if (this.rect.top <= 0) {
            setPosition(this.rect.left, 0,
                    this.rect.left + this.rect.width(), this.rect.height());
        }
        if (surfaces != null) {
            for (Surface surface: surfaces) {
                if (this.rect.bottom > surface.rect.top) {
                    this.onSurface = true;
                }
            }
        }
    }

    private Bitmap getBitmap(String flapType) {
        return this.assetManager.getBitmap(this.playerColour + "BIRD_" + flapType);
    }

    private Matrix rotateBitmap() {
        Matrix m = new Matrix();
        m.setScale(3, 3);
        m.postRotate(this.rotation, this.bitmap.getWidth() / 2, this.bitmap.getHeight() / 2);
        m.postTranslate(this.centre.getX(), this.centre.getY());

        return m;
    }

    public boolean isDead() {
        return this.onSurface;
    }

    public String getPlayerColour() {
        return this.playerColour;
    }

    public void setPlayerColour(String playerColour) {
        this.playerColour = playerColour;
    }
}
