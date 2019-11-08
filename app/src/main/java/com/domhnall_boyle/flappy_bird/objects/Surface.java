package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;
import com.domhnall_boyle.flappy_bird.game.Game;

public class Surface extends GameObject {

    public Surface() {
        super("BASE", 0, Scale.getY(85), Game.getScreenWidth(),
                Game.getScreenHeight());
    }

    public Surface(int x1, int y1, int x2, int y2) {
        super("BASE", x1, y1, x2, y2);
    }

    @Override
    public void update() {
        setPosition(getPosition().getX() - 1, getPosition().getY());
        if (getRect().right < 0) {
            int screenWidth = Game.getScreenWidth();
            int screenHeight = Game.getScreenHeight();
            setPosition(screenWidth, Scale.getY(85), screenWidth * 2, screenHeight);
        }
    }
}
