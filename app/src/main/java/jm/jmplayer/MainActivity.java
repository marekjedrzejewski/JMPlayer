package jm.jmplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Player playercontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load player and sample track for testing
        playercontrol = new Player(this);
        try {
            playercontrol.loadtrack(Uri.parse("/storage/sdcard/Download/jmptune.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        playercontrol.exit();
        super.onDestroy();
    }

    public void clickPlayButton(View view) {
        playercontrol.playOrPause();
    }

}

