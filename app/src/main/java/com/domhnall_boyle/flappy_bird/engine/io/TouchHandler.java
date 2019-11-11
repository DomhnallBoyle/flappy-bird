package com.domhnall_boyle.flappy_bird.engine.io;

import android.view.MotionEvent;
import android.view.View;

import com.domhnall_boyle.flappy_bird.utilities.Pool;

import java.util.ArrayList;
import java.util.List;

public class TouchHandler implements View.OnTouchListener {

    private final int MAX_TOUCHPOINTS = 10;
    private final int TOUCH_POOL_SIZE = 100;

    private final boolean[] existsTouch = new boolean[MAX_TOUCHPOINTS];
    private final float[] touchX = new float[MAX_TOUCHPOINTS];
    private final float[] touchY = new float[MAX_TOUCHPOINTS];

    private final Pool<TouchEvent> pool;
    private final List<TouchEvent> touchEvents = new ArrayList<>();
    private final List<TouchEvent> unconsumedTouchEvents = new ArrayList<>();

    private float scaleX, scaleY;

    public TouchHandler(View view) {
        this.pool = new Pool<>(new Pool.ObjectFactory<TouchEvent>() {
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        }, TOUCH_POOL_SIZE);
        this.scaleX = this.scaleY = 1.0f;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

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
        TouchEvent touchEvent = this.pool.get();
        touchEvent.setPointer(pointerId);
        touchEvent.setX(this.touchX[pointerId]);
        touchEvent.setY(this.touchY[pointerId]);

        switch (eventType) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchEvent.setType(TouchType.TOUCH_DOWN);
                this.existsTouch[pointerId] = true;
                break;

            case MotionEvent.ACTION_MOVE:
                touchEvent.setType(TouchType.TOUCH_DOWN);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                touchEvent.setType(TouchType.TOUCH_DOWN);
                this.existsTouch[pointerId] = false;
                break;
        }

        // Add this touch events to the list of unconsumed events (to be
        // returned at the start of the next update). It is added in a
        // synchronized manner as a non-GUI threads may be acquiring
        // the unconsumed touch events.
        synchronized (this) {
            if(touchEvent.getType() == TouchType.TOUCH_DOWN || touchEvent.getType() == TouchType.TOUCH_UP)
                this.unconsumedTouchEvents.add(touchEvent);
        }

        return true;
    }

    public boolean existsTouch(int pointerId) {
        synchronized (this) {
            return this.existsTouch[pointerId];
        }
    }

    public float getTouchX(int pointerId) {
        synchronized (this) {
            // Assumes the user will ensure correct range checking - for speed
            if (this.existsTouch[pointerId])
                return this.touchX[pointerId];
            else
                return Float.NaN;
        }
    }

    public float getTouchY(int pointerId) {
        synchronized (this) {
            // Assumes the user will ensure correct range checking - for speed
            if (this.existsTouch[pointerId])
                return this.touchY[pointerId];
            else
                return Float.NaN;
        }
    }

    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            return this.touchEvents;
        }
    }

    public void resetAccumulator() {
        synchronized (this) {
            // Release all existing touch events
            int len = this.touchEvents.size();
            for (int i = 0; i < len; i++)
                this.pool.add(this.touchEvents.get(i));
            this.touchEvents.clear();
            // Copy across accumulated events
            this.touchEvents.addAll(this.unconsumedTouchEvents);
            this.unconsumedTouchEvents.clear();
        }
    }
}
