package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.managers.ScreenManager;
import com.domhnall_boyle.flappy_bird.game.Game;

import java.util.ArrayList;
import java.util.List;

public class ScoreCounter extends GameObject {

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

            for (int i = 0; i < stringScore.length(); i++) {
                char c = stringScore.charAt(i);
                DigitCounter digitCounter = new DigitCounter("COUNTER_" + c,
                        Scale.getX(47), Scale.getY(10),
                        Scale.getX(57), Scale.getY(20));
                this.digitCounters.add(digitCounter);
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
