package com.domhnall_boyle.flappy_bird.engine.io;

public class TouchEvent {

    // TODO: Change this to enum and private public methods

    private TouchType type;
    private float x, y;
    private int pointer;

    public TouchType getType() {
        return type;
    }

    public void setType(TouchType type) {
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
}
