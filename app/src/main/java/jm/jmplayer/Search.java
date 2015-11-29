package jm.jmplayer;

import android.os.Environment;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by bortieboth on 29.11.15.
 */
public class Search {

    private static ArrayList<String> audioList = new ArrayList<>();

    public static ArrayList<String> getAudioFiles() {
        return getAudioFiles(Environment.getExternalStorageDirectory());
    }

    public static ArrayList<String> getAudioFiles(File dir){

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

                    audioList.add(aListFile.getPath());
                }


            }
        }

        return audioList;
    }
}
