package ir.amirhn.amirmusicplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(MainActivity.this, R.raw.music);

        Button play = findViewById(R.id.playBTN);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mp.isPlaying())
                    mp.pause();
                else
                    mp.start();


            }
        });


        Button chooseBTN = findViewById(R.id.chooseFile);
        chooseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                startActivityForResult(intent, 10);

            }
        });
        Button stop = findViewById(R.id.stopMusic);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null)
                    mp.pause();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK) {

            mp = MediaPlayer.create(MainActivity.this, data.getData());
            mp.start();

        }

    }
}
