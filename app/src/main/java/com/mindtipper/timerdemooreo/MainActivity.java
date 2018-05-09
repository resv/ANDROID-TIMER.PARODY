package com.mindtipper.timerdemooreo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;
    ImageView genehighkickunleashed;

    public void resetTimer() {
        timerTextView.setText("0:05");
        timerSeekBar.setProgress(5);
        timerSeekBar.setEnabled(true);
//        countDownTimer.cancel();
        goButton.setText("Start Kick Timer");
        counterIsActive = false;
    }

    public void buttonClicked(View view) {

        if (counterIsActive) {

            resetTimer();

        } else {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("Redact Kick!");

            CountDownTimer countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                    genehighkickunleashed.animate().alpha(0f);
                }

                @Override
                public void onFinish() {

                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.letsgo);
                    mplayer.start();
                    genehighkickunleashed.animate().alpha(1f);
                    resetTimer();

                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft) {

        int minutes = (secondsLeft /60);
        int seconds = (secondsLeft - (minutes * 60));

        String secondString = Integer.toString(seconds);

        if(seconds <= 9 ){
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genehighkickunleashed = (ImageView) findViewById(R.id.genehighkickunleashed);

        timerSeekBar = findViewById(R.id.timerSeekBar);

        timerTextView = findViewById(R.id.countDownTextView);

        goButton = findViewById(R.id.goButton);

        int max = 600;
        int min = 5;

        timerSeekBar.setMax(max);
        timerSeekBar.setProgress(min);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
