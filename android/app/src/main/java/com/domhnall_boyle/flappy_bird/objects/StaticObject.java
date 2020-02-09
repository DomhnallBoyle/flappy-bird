package com.domhnall_boyle.flappy_bird.objects;

public class StaticObject extends GameObject {

    public StaticObject(String bitmap, int x1, int y1, int x2, int y2) {
        super(bitmap, x1, y1, x2, y2);
    }

    public StaticObject(String bitmap, int x1, int y1, int x2, int y2, boolean visible) {
        super(bitmap, x1, y1, x2, y2);
        this.setIsShowing(visible);
    }

    public StaticObject(int x1, int y1, int x2, int y2, boolean visible) {
        super(x1, y1, x2, y2, visible);
    }
}
