package com.example.myfirstapp;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4;
    Button randomMsgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        final int[] soundList = new int[4];
        sound1 = soundPool.load(this, R.raw.bird, 1);
        sound2 = soundPool.load(this, R.raw.bow, 1);
        sound3 = soundPool.load(this, R.raw.burp, 1);
        sound4 = soundPool.load(this, R.raw.pew, 1);

        soundList[0] = sound1;
        soundList[1] = sound2;
        soundList[2] = sound3;
        soundList[3] = sound4;

        final Random r = new Random();
        randomMsgBtn = (Button) this.findViewById(R.id.randomMsgBtn);
        randomMsgBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button) {
                soundPool.play(soundList[r.nextInt(4)], 1, 1, 0, 0, 1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
