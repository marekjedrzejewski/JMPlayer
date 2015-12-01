package jm.jmplayer;

import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class TrackList {

    Player playercontrol;
    ListView trackListView;
    ArrayList<Track> trackArray;
    int currentTrackPosition;
    MainActivity mainActivity;

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
        lv.setOnItemClickListener(new TracklistClickHandler(playercontrol));

    }
}
