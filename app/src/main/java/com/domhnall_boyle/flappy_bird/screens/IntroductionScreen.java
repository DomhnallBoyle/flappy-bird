package com.domhnall_boyle.flappy_bird.screens;

import android.app.Activity;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.engine.io.TouchType;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.Background;
import com.domhnall_boyle.flappy_bird.objects.GameObject;
import com.domhnall_boyle.flappy_bird.objects.StaticObject;
import com.domhnall_boyle.flappy_bird.objects.Surface;

import java.util.List;

public class IntroductionScreen extends GameScreen {

    private Background background;
    private Surface surface1, surface2;
    private StaticObject intro;

    public IntroductionScreen(Activity activity, Game game) {
        super(activity, game);

        this.background = new Background("DAY");
        this.surface1 = new Surface();
        this.surface2 = new Surface(this.width, Scale.getY(85), this.width * 2, this.height);
        this.intro = new StaticObject("INTRO", Scale.getX(25), Scale.getY(25), Scale.getX(80), Scale.getY(75));

        this.addGameObjects(new GameObject[] {this.background, this.surface1, this.surface2, this.intro});
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
