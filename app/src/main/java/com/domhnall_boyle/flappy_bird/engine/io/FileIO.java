package com.domhnall_boyle.flappy_bird.engine.io;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.SoundPool;

import com.domhnall_boyle.flappy_bird.engine.audio.Sound;
import com.domhnall_boyle.flappy_bird.utilities.Logger;

import java.io.IOException;
import java.io.InputStream;

public class FileIO {

    private Context context;
    private AssetManager assetManager;

    public FileIO(Context context) {
        this.context = context;
        this.assetManager = this.context.getAssets();
    }

    public Bitmap loadBitmap(String fileName) throws IOException {
        Options options = new Options();
        options.inPreferredConfig = null;
        InputStream in = null;
        Bitmap bitmap;
        try {
            in = this.assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null) {
                String message = "Could not load Bitmap: " + fileName;
                throw new IOException(message);
            }
        } catch(IOException e) {
            String message = "Could not load Bitmap: " + fileName + " - " + e.getMessage();
            throw new IOException(message);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch(IOException e) {
                    Logger.debug(e.getMessage());
                }
            }
        }

        return bitmap;
    }

    public Sound loadSound(String fileName, SoundPool soundPool) throws IOException
    {
        try
        {
            AssetFileDescriptor assetFileDescriptor = this.assetManager.openFd(fileName);
            int soundId = soundPool.load(assetFileDescriptor, 0);

            return new Sound(soundId);
        }
        catch(IOException e)
        {
            String message = "Could not load Sound: " + fileName + " - " + e.getMessage();
            throw new IOException(message);
        }
    }
}
