package com.domhnall_boyle.flappy_bird.engine.managers;

import android.graphics.Bitmap;

import com.domhnall_boyle.flappy_bird.engine.io.FileIO;
import com.domhnall_boyle.flappy_bird.utilities.Logger;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import java.io.IOException;
import java.util.HashMap;

public class AssetManager {

    private static AssetManager assetManager;
    private HashMap<String, Bitmap> bitmaps;
    private FileIO fileIO;

    private AssetManager() {
        this.bitmaps = new HashMap<>();
        this.fileIO = new FileIO(MyApplication.getCurrentActivity());

        this.addBitmaps();
    }

    public static synchronized AssetManager getInstance() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }

        return assetManager;
    }

    public boolean loadAndAddBitmap(String assetName, String assetPath) {
        try {
            Bitmap bitmap = this.fileIO.loadBitmap("img/" + assetPath);

            return addBitmap(assetName, bitmap);
        } catch(IOException e) {
            Logger.error(e.getMessage());

            return false;
        }
    }

    public Bitmap getBitmap(String assetName) {
        if (this.bitmaps.containsKey(assetName)) {
            return this.bitmaps.get(assetName);
        }

        return null;
    }

    private boolean addBitmap(String assetName, Bitmap bitmap) {
        if (!this.bitmaps.containsKey(assetName)) {
            this.bitmaps.put(assetName, bitmap);

            return true;
        }

        return false;
    }

    private void addBitmaps() {
        loadAndAddBitmap("BACKGROUND_DAY", "background_day.png");
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
    }
}
