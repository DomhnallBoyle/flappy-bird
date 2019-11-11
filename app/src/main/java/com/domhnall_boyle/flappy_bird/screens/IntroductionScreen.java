package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;

import com.domhnall_boyle.flappy_bird.engine.graphics.IGraphics2D;
import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.io.TouchType;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.Background;
import com.domhnall_boyle.flappy_bird.objects.StaticObject;
import com.domhnall_boyle.flappy_bird.objects.Surface;

import java.util.List;

public class IntroductionScreen extends GameScreen {

    private Background background;
    private Surface surface1, surface2;
    private StaticObject intro;

    public IntroductionScreen(Activity activity, Game game) {
        super(activity, game);

        this.background = new Background("BACKGROUND_DAY");
        this.surface1 = new Surface();
        this.surface2 = new Surface(this.width, Scale.getY(85), this.width * 2, this.height);
        this.intro = new StaticObject("INTRO", Scale.getX(25), Scale.getY(25), Scale.getX(80), Scale.getY(75));
    }

    @Override
    void _draw(IGraphics2D graphics2D) {
        this.background.draw(graphics2D);
        this.surface1.draw(graphics2D);
        this.surface2.draw(graphics2D);
        this.intro.draw(graphics2D);
    }

    @Override
    void _update() {
        this.surface1.update();
        this.surface2.update();

        // get touch events
        List<TouchEvent> touchEvents = this.game.getTouchEvents();
        if (touchEvents.size() > 0) {
            TouchEvent touchEvent = touchEvents.get(0);
            if (touchEvent.getType() == TouchType.TOUCH_DOWN) {
                new PlayScreen(this.activity, this.game);
            }
        }

        this.game.resetAccumulators();
    }
}
