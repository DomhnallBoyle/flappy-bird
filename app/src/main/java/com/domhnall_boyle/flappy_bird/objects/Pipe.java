package com.domhnall_boyle.flappy_bird.objects;

public class Pipe extends GameObject {

    public Pipe(String bitmap, int x1, int y1, int x2, int y2) {
        super(bitmap, x1, y1, x2, y2);
    }

    @Override
    public void update() {
        this.setPosition(this.centre.getX() - 1, this.centre.getY());
        if (this.rect.right < 0) {
            // put back to initial position
            this.setPosition(this.initialX1, this.initialY1, this.initialX2, this.initialY2);
        }
    }
}
