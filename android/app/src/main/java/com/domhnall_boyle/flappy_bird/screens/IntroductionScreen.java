package com.domhnall_boyle.flappy_bird.screens;

import androidx.fragment.app.Fragment;

import com.domhnall_boyle.flappy_bird.engine.graphics.Scale;
import com.domhnall_boyle.flappy_bird.engine.io.TouchEvent;
import com.domhnall_boyle.flappy_bird.fragments.LeaderboardFragment;
import com.domhnall_boyle.flappy_bird.fragments.PlayFragment;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.objects.Background;
import com.domhnall_boyle.flappy_bird.objects.Button;
import com.domhnall_boyle.flappy_bird.objects.GameObject;
import com.domhnall_boyle.flappy_bird.objects.Logo;
import com.domhnall_boyle.flappy_bird.objects.Player;
import com.domhnall_boyle.flappy_bird.objects.Surface;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import java.util.List;

public class IntroductionScreen extends GameScreen {

    // TODO: Polymorphism as GameObject
    //  Fix touch events overlapping e.g. selecting player and background colours

    private Background background;
    private Surface surface1, surface2;
    private Logo logo;
    private Button start, leaderboard;
    private Player player;

    public IntroductionScreen(Fragment fragment, Game game) {
        super(fragment, game);

        this.background = new Background("DAY");
        this.surface1 = new Surface();
        this.surface2 = new Surface(this.width, Scale.getY(85), this.width * 2, this.height);
        this.logo = new Logo();
        this.start = new Button("START", Scale.getX(15), Scale.getY(50), Scale.getX(45), Scale.getY(60));
        this.leaderboard = new Button("LEADERBOARD", Scale.getX(60), Scale.getY(50), Scale.getX(90), Scale.getY(60));

        this.addGameObjects(new GameObject[] {this.background, this.surface1, this.surface2,
                this.logo, this.start, this.leaderboard});
        this.player = this.logo.getPlayer();
        this.initialised = true;
    }

    @Override
    void _update() {
        this.surface1.update();
        this.surface2.update();
        this.logo.update();

        // get touch events
        List<TouchEvent> touchEvents = this.game.getTouchEvents();

        // check if buttons pressed
        if (this.start.isPressed(touchEvents)) {
            MyApplication.changeFragment(new PlayFragment(this.game,
                    this.background.getBackgroundType(), this.player.getPlayerColour()),
                    true);
        }

        if (this.leaderboard.isPressed(touchEvents)) {
            MyApplication.changeFragment(new LeaderboardFragment(this.game),
                    true);
        }

        if (this.player.isPressed(touchEvents)) {
            String playerColour = this.player.getPlayerColour();

            switch (playerColour) {
                case "YELLOW":
                    this.player.setPlayerColour("RED");
                    break;
                case "RED":
                    this.player.setPlayerColour("BLUE");
                    break;
                case "BLUE":
                    this.player.setPlayerColour("YELLOW");
                    break;
            }
        } else if (this.background.isPressed(touchEvents)) {
            if (this.background.getBackgroundType().equalsIgnoreCase("DAY")) {
                this.background.setBackgroundType("NIGHT");
            } else {
                this.background.setBackgroundType("DAY");
            }
        }

        this.game.resetAccumulators();
    }
}
