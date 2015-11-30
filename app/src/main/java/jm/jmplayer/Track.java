package jm.jmplayer;

import android.net.Uri;

public class Track {
    private Uri uri;
    private String title;
    private String artist;
    private long lengthInSeconds;

    public Track(String path)
    {
        this.uri = Uri.parse(path);
    }

    public Uri getUri(){ return uri; }

    public String getArtist(){ return "Darude"; }

    public String getTitle() { return "Sandstorm"; }

    public long getLengthInSeconds(){ return lengthInSeconds; }

    public String getLengthAsString(){ return "06:66"; }

}
