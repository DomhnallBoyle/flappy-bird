package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.game.Game;

public class Background extends StaticObject {

    public Background(String bitmap) {
        super(bitmap, 0, 0, Game.getScreenWidth(), Game.getScreenHeight());
    }
}
