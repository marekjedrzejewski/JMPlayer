package jm.jmplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by marek on 29.11.15.
 */
public class Player extends MediaPlayer {

    private MainActivity mainActivity;
    private ImageButton btPlayPause;
    private SeekBar seekBar;
    private Handler seekBarHandler;
    private TextView trackInfo;
    private int currentTrackPosition;
    private ArrayList<Track> trackList;

    public Player(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        btPlayPause = (ImageButton) mainActivity.findViewById(R.id.playButton);
        seekBar = (SeekBar) mainActivity.findViewById(R.id.seekBar);
        trackInfo = (TextView) mainActivity.findViewById(R.id.titleText);
        setAudioStreamType(AudioManager.STREAM_MUSIC);
        enableSeekBarInput();
        enableOnCompletion();

        currentTrackPosition = 0;
        trackList = Search.getAudioFiles();

        try {
            this.loadtrack(trackList.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadtrack(Track track) throws IOException {
        reset();
        setDataSource(mainActivity, track.getUri());
        prepare();
        seekBar.setMax(getDuration());
        trackInfo.setText(track.getTitle());
        enableSeekBarTracking();
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

    private void enableOnCompletion() {
        setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPlayPause.setImageResource(android.R.drawable.ic_media_play);
                // Looping for now, when buttons get implemented it will play next track.
                btPlayPause.callOnClick();
            }
        });
    }

    public void exit() {
        seekBarHandler = null;
        stop();
        release();
    }

    private void enableSeekBarTracking() {
        seekBarHandler = new Handler();
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (seekBarHandler != null) {
                    seekBar.setProgress(getCurrentPosition());
                    seekBarHandler.postDelayed(this, 100);
                }
            }
        });
    }

    private void enableSeekBarInput() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean userInput) {
                if(userInput){
                    seekTo(progress);
                }
            }
        });
    }

    public ArrayList<Track> getTrackList(){ return trackList; }

    public void setTrackPosition(int newPosition){
        currentTrackPosition = newPosition;
    }
}


