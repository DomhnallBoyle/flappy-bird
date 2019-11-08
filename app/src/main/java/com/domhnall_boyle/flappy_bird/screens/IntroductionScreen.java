package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.objects.Background;
import com.domhnall_boyle.flappy_bird.objects.Surface;

public class IntroductionScreen extends GameScreen {

    private Background background;
    private Surface surface1, surface2;

    public IntroductionScreen(Activity activity) {
        super(activity);

        this.background = new Background("BACKGROUND_DAY");
        this.surface1 = new Surface();
        this.surface2 = new Surface(this.width, Scale.getY(85), this.width * 2, this.height);
    }

    @Override
    void _draw(IGraphics2D graphics2D) {
        this.background.draw(graphics2D);
        this.surface1.draw(graphics2D);
        this.surface2.draw(graphics2D);
    }

    @Override
    void _update() {
        this.surface1.update();
        this.surface2.update();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
