package jm.jmplayer;

import android.net.Uri;

public class Track {
    private String path;
    private String title;
    private String artist;
    private long durationInSeconds;

    public Track(String uriPath)
    {
        this.path = uriPath;
    }

}
