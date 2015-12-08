package jm.jmplayer;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.io.File;

public class JMSearchService extends IntentService {

    public JMSearchService() { super("JMSearchService"); }

    public void getAudioFiles(File dir){


        File listFile[] = dir.listFiles();

        if(listFile != null && listFile.length > 0)
        {
            for (File aListFile : listFile) {

                if (aListFile.isDirectory()) {
                    getAudioFiles(aListFile);
                } else if (aListFile.getName().endsWith(".mp3")
                        || aListFile.getName().endsWith(".wav")
                        || aListFile.getName().endsWith(".flac"))

                {
                    Intent newTrackIntent = new Intent("new track")
                            .putExtra("track path", aListFile.getPath());

                    LocalBroadcastManager.getInstance(this).sendBroadcast(newTrackIntent);
                }


            }
        }

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        getAudioFiles(new File(intent.getDataString()));
    }
}
