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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load player and sample track for testing
        playercontrol = new Player(this);

        createTrackList();


    }

    @Override
    protected void onDestroy() {
        playercontrol.exit();
        super.onDestroy();
    }

    public void clickPlayButton(View view) {
        playercontrol.playOrPause();
    }

    public void createTrackList(){

        ArrayList<Track> trackList = playercontrol.getTrackList();
        Track[] trackArr = new Track[trackList.size()];
        trackArr = trackList.toArray(trackArr);

        LinearLayout browser = (LinearLayout)findViewById(R.id.browserTrackList);
        ListView lv = (ListView)findViewById(R.id.listView);

        TrackAdapter adapter=
                new TrackAdapter(this, R.layout.tracklist_row, trackArr);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new TracklistClickHandler(playercontrol));

    }

}

