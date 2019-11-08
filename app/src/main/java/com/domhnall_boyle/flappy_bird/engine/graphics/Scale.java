package com.domhnall_boyle.flappy_bird.engine.graphics;

import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;

public class Scale {

    public static int getX(int widthPercentage) {
        return (ScreenManager.getInstance().getCurrentScreen().getWidthPixels() / 100) * widthPercentage;
    }

    public static int getY(int heightPercentage) {
        return (ScreenManager.getInstance().getCurrentScreen().getHeightPixels() / 100) * heightPercentage;
    }
}
