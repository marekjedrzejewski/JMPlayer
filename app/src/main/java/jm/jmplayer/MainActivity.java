package jm.jmplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Player playercontrol;
    TrackList trackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playercontrol = new Player(this);
        trackList = new TrackList(this, playercontrol);

        trackList.createTrackList();
    }

    @Override
    protected void onDestroy() {
        playercontrol.exit();
        super.onDestroy();
    }

    public void clickPlayButton(View view) {
        playercontrol.playOrPause();
    }

    public void clickPrevButton(View view) {
        trackList.previousTrack();
    }

    public void clickNextButton(View view) {
        trackList.nextTrack();
    }


}

