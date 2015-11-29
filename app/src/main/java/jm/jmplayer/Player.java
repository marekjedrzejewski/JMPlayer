package jm.jmplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.ImageButton;

import java.io.IOException;

/**
 * Created by marek on 29.11.15.
 */
public class Player extends MediaPlayer {

    MainActivity mainActivity;
    ImageButton btPlayPause;

    public Player(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        btPlayPause = (ImageButton) mainActivity.findViewById(R.id.playButton);
        setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void loadtrack(Uri trackuri) throws IOException {
        setDataSource(mainActivity, trackuri);
        prepare();
    }

    public void playOrPause() {
        if (isPlaying()) {
            pause();
            btPlayPause.setImageResource(android.R.drawable.ic_media_play);
        }
        else {
            start();
            btPlayPause.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

}


