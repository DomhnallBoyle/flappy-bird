package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.game.Game;

public class Surface extends GameObject {

    public Surface() {
        super("BASE", 0, Scale.getY(85),
                Game.getScreenWidth(), Game.getScreenHeight());
    }

    public Surface(int x1, int y1, int x2, int y2) {
        super("BASE", x1, y1, x2, y2);
    }

    @Override
    public void update() {
        this.setPosition(this.centre.getX() - 1, this.centre.getY());
        if (this.rect.right < 0) {
            int screenWidth = Game.getScreenWidth();
            int screenHeight = Game.getScreenHeight();
            this.setPosition(screenWidth, Scale.getY(85),
                    screenWidth * 2, screenHeight);
        }
    }
}
