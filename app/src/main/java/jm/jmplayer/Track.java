package jm.jmplayer;

import android.media.MediaMetadataRetriever;
import android.net.Uri;

public class Track {
    static MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
    private Uri uri;
    private String title;
    private String artist;
    private long duration;

    public Track(String path)
    {
        this.uri = Uri.parse(path);
        metaRetriever.setDataSource(path);

        title = metaRetriever.extractMetadata(
                MediaMetadataRetriever.METADATA_KEY_TITLE);
        if (title == null)
            title = uri.getLastPathSegment();

        artist = metaRetriever.extractMetadata(
                MediaMetadataRetriever.METADATA_KEY_ARTIST);
        if (artist == null)
            artist = "N/A";

        duration = Long.parseLong(
                metaRetriever.extractMetadata(
                        MediaMetadataRetriever.METADATA_KEY_DURATION)
        );
    }

    public Uri getUri(){ return uri; }

    public String getArtist(){
        return artist;

    }

    public String getTitle() { return title; }

    public long getLengthInSeconds(){ return duration; }

    public String getLengthAsString(){
        long durationS = duration / 1000;
        long h = durationS / 3600;
        long m = (durationS - h * 3600) / 60;
        long s = durationS - (h * 3600 + m * 60);
        if (h == 0)
            return String.format("%d:%02d", m, s);
        else
            return String.format("%d:%02d:%02d", h, m, s);
    }

}
