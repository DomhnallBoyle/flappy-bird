package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.utilities.PersonalBest;

public class GameoverMenu extends GameObject {

    private final int BRONZE = 10, SILVER = 20, GOLD = 40;

    private StaticObject medal, title, newPersonalBest;
    private ScoreCounter gameScoreCounter, personalBestCounter;
    private PersonalBest personalBest;
    private Button start;
    private int score;

    public GameoverMenu() {
        super("GAMEOVER_MENU", Scale.getX(12), Scale.getY(25), Scale.getX(96), Scale.getY(60));
        this.visible = false;
        this.title = new StaticObject("GAMEOVER", Scale.getX(25), Scale.getY(15), Scale.getX(80), Scale.getY(25));
        this.medal = new StaticObject(Scale.getX(21), Scale.getY(39), Scale.getX(40), Scale.getY(51), false);
        this.gameScoreCounter = new ScoreCounter(Scale.getX(77), Scale.getY(36), Scale.getX(5), Scale.getY(5));
        this.personalBestCounter = new ScoreCounter(Scale.getX(77), Scale.getY(48), Scale.getX(5), Scale.getY(5));
        this.newPersonalBest = new StaticObject("NEW_PERSONAL_BEST", Scale.getX(65), Scale.getY(43), Scale.getX(73), Scale.getY(47), false);
        this.personalBest = new PersonalBest();
        this.start = new Button("START", Scale.getX(40), Scale.getY(65), Scale.getX(70), Scale.getY(75));
    }

    public void setScore(int score) {
        this.score = score;

        // needed for an initial update of the counters
        this.gameScoreCounter.setScore(this.score);
        this.gameScoreCounter.update();

        this.personalBest.update(this.score);
        this.personalBestCounter.setScore(this.personalBest.getPersonalBest());
        this.personalBestCounter.update();

        if (this.personalBest.isNewPersonalBest()) {
            this.newPersonalBest.setIsShowing(true);
        }

        if (this.score >= GOLD) {
            this.medal.setBitmap("GOLD");
        } else if (this.score >= SILVER) {
            this.medal.setBitmap("SILVER");
        } else if (this.score >= BRONZE){
            this.medal.setBitmap("BRONZE");
        }

        if (this.medal.getBitmap() != null) {
            this.medal.setIsShowing(true);
        }
    }

    @Override
    public void draw(IGraphics2D graphics2D) {
        if (this.visible) {
            super.draw(graphics2D);
            this.title.draw(graphics2D);
            this.gameScoreCounter.draw(graphics2D);
            this.personalBestCounter.draw(graphics2D);
            this.newPersonalBest.draw(graphics2D);
            this.medal.draw(graphics2D);
            this.start.draw(graphics2D);
        }
    }

    public Button getStartButton() {
        return this.start;
    }
}
