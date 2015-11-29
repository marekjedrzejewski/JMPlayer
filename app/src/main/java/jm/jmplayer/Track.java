package jm.jmplayer;

import android.net.Uri;

public class Track {
    private Uri uri;
    private String title;
    private String artist;
    private long durationInSeconds;

    public Track(String path)
    {
        this.uri = Uri.parse(path);
    }

    public Uri getUri(){ return uri; }

}
