package com.domhnall_boyle.flappy_bird.engine.managers;

import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;

import com.domhnall_boyle.flappy_bird.engine.audio.Sound;
import com.domhnall_boyle.flappy_bird.engine.io.FileIO;
import com.domhnall_boyle.flappy_bird.utilities.Logger;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import java.io.IOException;
import java.util.HashMap;

import static android.content.Context.AUDIO_SERVICE;

public class AssetManager {

    private static AssetManager assetManager;
    private HashMap<String, Bitmap> bitmaps;
    private HashMap<String, Sound> sounds;
    private SoundPool soundPool;
    private FileIO fileIO;

    private AssetManager() {
        this.bitmaps = new HashMap<>();
        this.sounds = new HashMap<>();
        this.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        this.fileIO = new FileIO(MyApplication.getCurrentActivity());

        this.addBitmaps();
        this.addSounds();
    }

    public static synchronized AssetManager getInstance() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }

        return assetManager;
    }

    private boolean loadAndAddBitmap(String assetName, String assetPath) {
        try {
            Bitmap bitmap = this.fileIO.loadBitmap("img/" + assetPath);

            return addBitmap(assetName, bitmap);
        } catch(IOException e) {
            Logger.error(e.getMessage());

            return false;
        }
    }

    private boolean loadAndAddSound(String assetName, String assetPath) {
        try {
            Sound sound = this.fileIO.loadSound("audio/" + assetPath, this.soundPool);

            return addSound(assetName, sound);
        } catch(IOException e) {
            Logger.error(e.getMessage());

            return false;
        }
    }

    public Bitmap getBitmap(String assetName) {
        return this.bitmaps.get(assetName);
    }

    public void playSound(String assetName) {
        AudioManager audioManager = (AudioManager)
                MyApplication.getCurrentActivity().getApplicationContext().getSystemService(AUDIO_SERVICE);
        float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = actualVolume / maxVolume;

        Sound sound = this.sounds.get(assetName);
        this.soundPool.play(sound.getSoundId(), volume, volume, 0, 0, 1);
    }

    private boolean addBitmap(String assetName, Bitmap bitmap) {
        if (!this.bitmaps.containsKey(assetName)) {
            this.bitmaps.put(assetName, bitmap);

            return true;
        }

        return false;
    }

    private boolean addSound(String assetName, Sound sound) {
        if (!this.sounds.containsKey(assetName)) {
            this.sounds.put(assetName, sound);

            return true;
        }

        return false;
    }

    private void addBitmaps() {
        loadAndAddBitmap("BACKGROUND_DAY", "background_day.png");
        loadAndAddBitmap("BACKGROUND_NIGHT", "background_night.png");
        loadAndAddBitmap("BASE", "base.png");
        loadAndAddBitmap("INTRO", "message.png");
        for (int i = 0; i < 10; i++) {
            loadAndAddBitmap("COUNTER_" + i, "counter_" + i + ".png");
        }
        String[] birds = new String[] {"redbird", "yellowbird", "bluebird"};
        for (String bird: birds) {
            loadAndAddBitmap(bird.toUpperCase() + "_DOWNFLAP",
                    bird + "_downflap.png");
            loadAndAddBitmap(bird.toUpperCase() + "_MIDFLAP",
                    bird + "_midflap.png");
            loadAndAddBitmap(bird.toUpperCase() + "_UPFLAP",
                    bird + "_upflap.png");
        }
        String[] pipes = new String[] {"green", "red"};
        for (String pipe: pipes) {
            loadAndAddBitmap("PIPE_" + pipe.toUpperCase() + "_UP",
                    "pipe_" + pipe + "_up.png");
            loadAndAddBitmap("PIPE_" + pipe.toUpperCase() + "_DOWN",
                    "pipe_" + pipe + "_down.png");
        }
        loadAndAddBitmap("START", "start.png");
        loadAndAddBitmap("LEADERBOARD", "leaderboard.png");
        loadAndAddBitmap("LOGO", "logo.png");
        loadAndAddBitmap("PAUSE", "pause.png");
        loadAndAddBitmap("PLAY", "play.png");
        loadAndAddBitmap("GAMEOVER_MENU", "gameover_menu.png");
        loadAndAddBitmap("GAMEOVER", "gameover.png");
        loadAndAddBitmap("NEW_PERSONAL_BEST", "new.png");
        loadAndAddBitmap("GOLD", "gold.png");
        loadAndAddBitmap("SILVER", "silver.png");
        loadAndAddBitmap("BRONZE", "bronze.png");
        loadAndAddBitmap("OK", "ok.png");
    }

    private void addSounds() {
        loadAndAddSound("HIT", "hit.wav");
        loadAndAddSound("DIE", "die.wav");
        loadAndAddSound("POINT", "point.wav");
        loadAndAddSound("FLAP", "wing.wav");
        loadAndAddSound("SWOOSH", "swoosh.wav");
    }
}
