package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.utilities.Vect;

public class Logo extends GameObject {

    private final int MAX_MOVEMENT_UPDATES = 50;

    private Player player;
    private GameObject logo;
    private int movementUpdates = 0;
    private boolean up = false;

    public Logo() {
        this.player = new Player("YELLOW", Scale.getX(71), Scale.getY(27), Scale.getX(81), Scale.getY(32));
        this.logo = new GameObject("LOGO", Scale.getX(16), Scale.getY(25), Scale.getX(71), Scale.getY(40));
    }

    @Override
    public void update() {
        Vect playerCenter = this.player.getCentre();
        Vect logoCenter = this.logo.getCentre();

        if (this.up) {
            this.player.setPosition(playerCenter.getX(), playerCenter.getY() - 1);
            this.logo.setPosition(logoCenter.getX(), logoCenter.getY() - 1);

            this.movementUpdates += 1;
            if (this.movementUpdates == MAX_MOVEMENT_UPDATES) {
                this.up = false;
                this.movementUpdates = 0;
            }
        } else {
            this.player.setPosition(playerCenter.getX(), playerCenter.getY() + 1);
            this.logo.setPosition(logoCenter.getX(), logoCenter.getY() + 1);

            this.movementUpdates += 1;
            if (this.movementUpdates == MAX_MOVEMENT_UPDATES) {
                this.up = true;
                this.movementUpdates = 0;
            }
        }

        this.player.update(null, null, false, false);
    }

    @Override
    public void draw(IGraphics2D graphics2D) {
        this.player.draw(graphics2D);
        this.logo.draw(graphics2D);
    }

    public Player getPlayer() {
        return this.player;
    }
}
