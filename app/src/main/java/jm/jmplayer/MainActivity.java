package jm.jmplayer;

import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Player playercontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTrackList();

        // load player and sample track for testing
        playercontrol = new Player(this);
        try {
            playercontrol.loadtrack(Uri.parse("/storage/sdcard/Download/jmptune.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickPlayButton(View view) {
        playercontrol.playOrPause();
    }

    public void createTrackList(){
        ArrayList<String> trackList = Search.getAudioFiles();
        LinearLayout browser = (LinearLayout)findViewById(R.id.browserTrackList);

        for(String track : trackList)
        {
            TextView trackPath = new TextView(this);
            trackPath.setText(track);
            browser.addView(trackPath);
        }
    }
}
