package jm.jmplayer;

import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

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
        trackList.loadFirst();
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

