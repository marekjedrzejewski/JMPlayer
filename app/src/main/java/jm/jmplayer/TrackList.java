package jm.jmplayer;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
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
    TrackAdapter adapter;
    int currentTrackPosition;
    MainActivity mainActivity;
    Intent searchServiceIntent;

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
        trackArray = new ArrayList<>();
        //trackArray = JMSearchService.getAudioFiles();
        currentTrackPosition = -1;

        searchServiceIntent = new Intent(mainActivity, JMSearchService.class);
        searchServiceIntent.setData(Uri.parse("/storage/"));
        mainActivity.startService(searchServiceIntent);

        LocalBroadcastManager.getInstance(mainActivity).registerReceiver(newTrackReceiver,
                new IntentFilter("new track"));
    }

    public void createTrackList(){

        adapter = new TrackAdapter(mainActivity, R.layout.tracklist_row, trackArray);

        trackListView.setAdapter(adapter);
        trackListView.setOnItemClickListener(this);

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
            currentTrackPosition = 0;
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


    private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            trackArray.add(new Track(intent.getStringExtra("track path")));
            adapter.notifyDataSetChanged();

            if(currentTrackPosition == -1)
                loadFirst();
        }
    };
}
