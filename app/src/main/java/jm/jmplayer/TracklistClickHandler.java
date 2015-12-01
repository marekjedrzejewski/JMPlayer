package jm.jmplayer;

import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import java.io.IOException;

public class TracklistClickHandler implements AdapterView.OnItemClickListener {

    Player playercontrol;

    public TracklistClickHandler(Player playercontrol){
        this.playercontrol = playercontrol;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            playercontrol.loadtrack(((Track)parent.getItemAtPosition(position)));
            playercontrol.playOrPause();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
