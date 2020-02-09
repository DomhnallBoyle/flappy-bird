package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.game.Game;

public class Background extends StaticObject {

    private String backgroundType;

    public Background(String backgroundType) {
        super("BACKGROUND_" + backgroundType,
                0, 0,
                Game.getScreenWidth(), Game.getScreenHeight());
        this.backgroundType = backgroundType;
    }

    public String getBackgroundType() {
        return this.backgroundType;
    }

    public void setBackgroundType(String backgroundType) {
        this.backgroundType = backgroundType;
        this.setBitmap("BACKGROUND_" + this.backgroundType);
    }
}
