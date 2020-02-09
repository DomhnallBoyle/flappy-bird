package com.domhnall_boyle.flappy_bird.engine.io;

import android.view.View;

import java.util.List;

public class Input {

    private TouchHandler touchHandler;

    public Input(View view) {
        this.touchHandler = new TouchHandler();
        view.setOnTouchListener(this.touchHandler);
    }

    public void resetAccumulators() {
        this.touchHandler.resetAccumulator();
    }

    public List<TouchEvent> getTouchEvents() {
        return this.touchHandler.getTouchEvents();
    }
}
