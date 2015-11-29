package jm.jmplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by marek on 29.11.15.
 */
public class Player extends MediaPlayer {

    MainActivity mainActivity;

    public Player(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void loadtrack(Uri trackuri) throws IOException {
        setDataSource(mainActivity, trackuri);
        prepare();
    }

    public void playOrPause() {
        if (isPlaying())
            pause();
        else start();
    }

}


