package jm.jmplayer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackAdapter extends ArrayAdapter<Track> {

    Context context;
    int layoutResourceId;
    ArrayList<Track> data = null;

    public TrackAdapter(Context context, int layoutResourceId,
                        ArrayList<Track> data) {
        super(context,layoutResourceId,data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        TrackHolder holder;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TrackHolder();
            holder.trackStateIcon = (ImageView)row.findViewById(R.id.trackStateIcon);
            holder.fileNameText = (TextView)row.findViewById(R.id.fileNameText);
            holder.trackDataText = (TextView)row.findViewById(R.id.trackDataText);
            holder.trackLengthText = (TextView)row.findViewById(R.id.trackLengthText);

            row.setTag(holder);
        }
        else{
            holder = (TrackHolder)row.getTag();
        }

        Track object = data.get(position);
        holder.fileNameText.setText(object.getUri().getLastPathSegment());
        if(object.getIsPlaying()){
            holder.trackStateIcon.setVisibility(View.VISIBLE);
        }
        else {
            holder.trackStateIcon.setVisibility(View.INVISIBLE);
        }
        holder.trackDataText.setText(object.getArtist() + " - " + object.getTitle());
        holder.trackLengthText.setText(object.getLengthAsString());

        return row;
    }
}

class TrackHolder{
    ImageView trackStateIcon;
    TextView fileNameText;
    TextView trackDataText;
    TextView trackLengthText;
}