package com.domhnall_boyle.flappy_bird.utilities;

public class Vect {

    private float x, y;

    public Vect() {}

    public Vect(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
