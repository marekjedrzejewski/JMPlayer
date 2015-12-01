package jm.jmplayer;

import android.media.MediaMetadataRetriever;
import android.net.Uri;

public class Track {
    static MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
    private Uri uri;
    private String title;
    private String artist;
    private long lengthInSeconds;

    public Track(String path)
    {
        this.uri = Uri.parse(path);
        metaRetriever.setDataSource(path);
        title = metaRetriever.extractMetadata(
                MediaMetadataRetriever.METADATA_KEY_TITLE);
        artist = metaRetriever.extractMetadata(
                MediaMetadataRetriever.METADATA_KEY_ARTIST);

    }

    public Uri getUri(){ return uri; }

    public String getArtist(){
        return artist;

    }

    public String getTitle() { return title; }

    public long getLengthInSeconds(){ return lengthInSeconds; }

    public String getLengthAsString(){ return "06:66"; }

}
