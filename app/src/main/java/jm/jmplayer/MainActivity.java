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

        createTrackList();

        // load player and sample track for testing
        playercontrol = new Player(this);
        try {
            playercontrol.loadtrack(Uri.parse("/storage/sdcard/Download/jmptune.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        ArrayList<Track> trackList = Search.getAudioFiles();
        ArrayList<String> fileList = new ArrayList<>();

        for(Track track : trackList){
            fileList.add(track.getUri().getLastPathSegment());
        }

        LinearLayout browser = (LinearLayout)findViewById(R.id.browserTrackList);
        ListView lv = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileList);

        lv.setAdapter(adapter);

    }

}

