package com.domhnall_boyle.flappy_bird.objects;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.utilities.Vect;

public class DualPipe extends GameObject {

    private final int MID_SPACING = Scale.getY(10);
    private final int MAX_POSITION_PERCENT = 60;
    private final int MIN_POSITION_PERCENT = 15;
    private final int PIPE_WIDTH = Scale.getX(15);
    private final int PIPE_HEIGHT = Scale.getY(60);

    private Pipe upPipe, downPipe;

    public DualPipe(String pipeColour) {
        super();
        this.downPipe = new Pipe("PIPE_" + pipeColour + "_DOWN",
                Game.getScreenWidth(),
                0,
                Game.getScreenWidth() + PIPE_WIDTH,
                0);

        this.upPipe = new Pipe("PIPE_" + pipeColour + "_UP",
                Game.getScreenWidth(),
                0,
                Game.getScreenWidth() + PIPE_WIDTH,
                0);

        this.updatePipeSpacing();
    }

    public DualPipe(String pipeColour, int startX) {
        super();
        this.downPipe = new Pipe("PIPE_" + pipeColour + "_DOWN",
                startX,
                0,
                startX + PIPE_WIDTH,
                0);

        this.upPipe = new Pipe("PIPE_" + pipeColour + "_UP",
                startX,
                0,
                startX + PIPE_WIDTH,
                0);

        this.updatePipeSpacing();
    }

    @Override
    public void update() {
        this.upPipe.update();
        this.downPipe.update();

        // TODO: Refactor: use the setupPipes() method somehow

        if (this.upPipe.rect.right <= 0) {
            this.updatePipeSpacing();
        }

        // update position of dual pipe object for reference
        this.setPosition(this.upPipe.centre.getX(), this.upPipe.rect.top + MID_SPACING);

        // check for collisions
    }

    @Override
    public void draw(IGraphics2D graphics2D) {
        this.upPipe.draw(graphics2D);
        this.downPipe.draw(graphics2D);
    }

    private void updatePipeSpacing() {
        // update spacing between pipes
        int startingPosition = this.getRandomY();

        this.downPipe.setPosition(
                this.downPipe.initialX1,
                (startingPosition - MID_SPACING) - PIPE_HEIGHT,
                this.downPipe.initialX2,
                startingPosition - MID_SPACING
        );

        this.upPipe.setPosition(
                this.upPipe.initialX1,
                startingPosition + MID_SPACING,
                this.upPipe.initialX2,
                (startingPosition + MID_SPACING) + PIPE_HEIGHT
        );
    }

    private int getRandomY() {
        int randomPercentage = (int) (Math.random() * MAX_POSITION_PERCENT + MIN_POSITION_PERCENT);

        return Scale.getY(randomPercentage);
    }
}
