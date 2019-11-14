package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;

import java.util.ArrayList;
import java.util.List;

public class ScoreCounter extends GameObject {

    private final int START_X = Scale.getX(50);
    private final int START_Y = Scale.getY(10);
    private final int DIGIT_WIDTH = Scale.getX(10);
    private final int DIGIT_HEIGHT = Scale.getY(10);

    private int score;
    private List<DigitCounter> digitCounters;
    private final Object lock;

    public ScoreCounter() {
        super();
        this.score = 0;
        this.digitCounters = new ArrayList<>();
        this.lock = new Object();
    }

    public void updateScore() {
        this.score += 1;
    }

    @Override
    public void update() {
        synchronized (this.lock) {
            this.digitCounters.clear();
            String stringScore = String.valueOf(this.score);

            int spacing = 0, startX = START_X;
            if (stringScore.length() > 1) {
                startX = START_X - ((stringScore.length() * DIGIT_WIDTH) / 2) + (DIGIT_WIDTH / 2);
            }

            for (int i = 0; i < stringScore.length(); i++) {
                char c = stringScore.charAt(i);
                DigitCounter digitCounter = new DigitCounter("COUNTER_" + c,
                        startX + spacing,
                        START_Y,
                        startX + DIGIT_WIDTH + spacing,
                        START_Y + DIGIT_HEIGHT);

                this.digitCounters.add(digitCounter);
                spacing += DIGIT_WIDTH;
            }
        }
    }

    @Override
    public void draw(IGraphics2D graphics2D) {
        synchronized (this.lock) {
            for (DigitCounter digitCounter: this.digitCounters) {
                digitCounter.draw(graphics2D);
            }
        }
    }
}
