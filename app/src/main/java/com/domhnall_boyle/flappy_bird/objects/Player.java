package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.game.Game;

public class Player extends GameObject {

    public Player(String bitmap) {
        super(bitmap, Scale.getX(50), Scale.getY(40),
                Scale.getX(60), Scale.getY(45));
    }
}
