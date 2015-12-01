package jm.jmplayer;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class TrackList implements AdapterView.OnItemClickListener {

    Player playercontrol;
    ListView trackListView;
    ArrayList<Track> trackArray;
    int currentTrackPosition;
    MainActivity mainActivity;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentTrackPosition = position;
        loadChosen();
        playercontrol.playOrPause();
    }

    public TrackList(MainActivity mainActivity, Player player) {
        playercontrol = player;
        this.mainActivity = mainActivity;
        trackListView = (ListView)mainActivity.findViewById(R.id.listView);
        // trackArray = new ArrayList<>();
        trackArray = Search.getAudioFiles();
        currentTrackPosition = 0;
    }

    public void createTrackList(){

        Track[] trackArr = new Track[trackArray.size()];
        trackArr = trackArray.toArray(trackArr);

        ListView lv = trackListView;

        TrackAdapter adapter=
                new TrackAdapter(mainActivity, R.layout.tracklist_row, trackArr);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

    }

    public void loadChosen() {
        try {
            playercontrol.loadtrack(trackArray.get(currentTrackPosition));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFirst() {
        try {
            playercontrol.loadtrack(trackArray.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextTrack() {
        if (currentTrackPosition == trackArray.size()-1)
            currentTrackPosition = 0;
        else
            currentTrackPosition++;
        loadChosen();
        playercontrol.playOrPause();
    }

    public void previousTrack() {
        if (currentTrackPosition == 0)
            currentTrackPosition = trackArray.size()-1;
        else
            currentTrackPosition--;
        loadChosen();
        playercontrol.playOrPause();
    }


}
