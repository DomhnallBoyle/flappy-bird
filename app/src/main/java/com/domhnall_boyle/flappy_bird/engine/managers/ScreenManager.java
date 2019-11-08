package com.domhnall_boyle.flappy_bird.engine.managers;

import com.domhnall_boyle.flappy_bird.screens.GameScreen;

public class ScreenManager {

    private static ScreenManager screenManager;
    private GameScreen currentScreen;

    private ScreenManager() {}

    public static synchronized ScreenManager getInstance() {
        if (screenManager == null) {
            screenManager = new ScreenManager();
        }

        return screenManager;
    }

    public GameScreen getCurrentScreen() {
        return this.currentScreen;
    }

    public void setCurrentScreen(GameScreen newScreen) {
        this.currentScreen = newScreen;
    }

}
