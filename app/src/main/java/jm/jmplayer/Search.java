package jm.jmplayer;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;

public class Search {

    private static ArrayList<Track> audioList = new ArrayList<>();

    public static ArrayList<Track> getAudioFiles() {
        return getAudioFiles(Environment.getExternalStorageDirectory());
    }

    public static ArrayList<Track> getAudioFiles(File dir){

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

                    audioList.add(new Track(aListFile.getPath()));
                }


            }
        }

        return audioList;
    }
}
