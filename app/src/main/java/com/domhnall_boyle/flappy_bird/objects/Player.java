package com.domhnall_boyle.flappy_bird.objects;

import android.graphics.Bitmap;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.io.TouchType;
import com.domhnall_boyle.flappy_bird.engine.managers.AssetManager;

import java.util.List;

public class Player extends GameObject {

    private final int UP_SKIP = -75;
    private final int DOWN_SKIP = 3;
    private final int FLAP_DELAY = 25;

    private int delay = 0, flapDelay = 0;
    private int bitmapFlag = 0;
    private String playerColour;

    public Player(String playerColour) {
        super(playerColour + "BIRD_MIDFLAP",
                Scale.getX(50), Scale.getY(40),
                Scale.getX(60), Scale.getY(45));
        this.playerColour = playerColour;
    }

    public void update(List<TouchEvent> touchEvents, Surface[] surfaces, boolean gameOver) {
        if (!gameOver) {
            // update player based on touch events
            if (touchEvents.size() > 0) {
                TouchEvent touchEvent = touchEvents.get(0);
                if (touchEvent.getType() == TouchType.TOUCH_DOWN) {
                    this.setPosition(this.centre.getX(), this.centre.getY() + UP_SKIP);
                    this.delay = 25;
                }
            } else {
                if (this.delay == 0) {
                    this.setPosition(this.centre.getX(), this.centre.getY() + DOWN_SKIP);
                } else {
                    this.delay -= 1;
                }
            }
        } else {
            this.setPosition(this.centre.getX(), this.centre.getY() + DOWN_SKIP);
        }

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

        // make sure player can't go above/below screen
        if (this.rect.top < 0) {
            setPosition(this.rect.left, 0,
                    this.rect.left + this.rect.width(), this.rect.height());
        }
        for (Surface surface: surfaces) {
            if (this.rect.bottom > surface.rect.top) {
                setPosition(this.rect.left, surface.rect.top - this.rect.height(),
                        this.rect.right, surface.rect.top);
            }
        }
    }

    private Bitmap getBitmap(String flapType) {
        return AssetManager.getInstance().getBitmap(this.playerColour + "BIRD_" + flapType);
    }
}
