package com.domhnall_boyle.flappy_bird.engine.io;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TouchHandler implements View.OnTouchListener {

    private final int MAX_TOUCHPOINTS = 10;

    private final boolean[] existsTouch = new boolean[MAX_TOUCHPOINTS];
    private final float[] touchX = new float[MAX_TOUCHPOINTS];
    private final float[] touchY = new float[MAX_TOUCHPOINTS];

    private final List<TouchEvent> touchEvents = new ArrayList<>();
    private final List<TouchEvent> unconsumedTouchEvents = new ArrayList<>();

    private float scaleX = 1.0f, scaleY = 1.0f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Update the locations of all occurring touch points
        for (int ptrIdx = 0; ptrIdx < event.getPointerCount(); ptrIdx++) {
            // Update the relevant touch point location
            int pointerId = event.getPointerId(ptrIdx);
            this.touchX[pointerId] = event.getX(ptrIdx) * this.scaleX;
            this.touchY[pointerId] = event.getY(ptrIdx) * this.scaleY;
        }

        // Extract details of this event
        int eventType = event.getActionMasked();
        int pointerId = event.getPointerId(event.getActionIndex());

        // Retrieve and populate a touch event
        TouchEvent touchEvent = new TouchEvent();
        touchEvent.setPointer(pointerId);
        touchEvent.setX(this.touchX[pointerId]);
        touchEvent.setY(this.touchY[pointerId]);

        switch (eventType) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchEvent.setType(TouchType.TOUCH_DOWN);
                this.existsTouch[pointerId] = true;
                break;
        }

        // Add this touch events to the list of unconsumed events (to be
        // returned at the start of the next update). It is added in a
        // synchronized manner as a non-GUI threads may be acquiring
        // the unconsumed touch events.
        synchronized (this) {
            if(touchEvent.getType() == TouchType.TOUCH_DOWN)
                this.unconsumedTouchEvents.add(touchEvent);
        }

        return true;
    }

    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            return this.touchEvents;
        }
    }

    public void resetAccumulator() {
        synchronized (this) {
            // Release all existing touch events
            this.touchEvents.clear();

            // Copy across accumulated events
            this.touchEvents.addAll(this.unconsumedTouchEvents);
            this.unconsumedTouchEvents.clear();
        }
    }
}
