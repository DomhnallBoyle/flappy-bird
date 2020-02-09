package com.domhnall_boyle.flappy_bird.engine.graphics;

import com.domhnall_boyle.flappy_bird.game.Game;

public class Scale {

    public static int getX(int widthPercentage) {
        return (Game.getScreenWidth() / 100) * widthPercentage;
    }

    public static int getY(int heightPercentage) {
        return (Game.getScreenHeight() / 100) * heightPercentage;
    }
}
