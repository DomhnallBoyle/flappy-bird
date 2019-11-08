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
    }
}
