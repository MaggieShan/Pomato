package com.example.pomato;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler;
    Runnable runnable;
    Timer timer;

    private TextView countdown;
    private CountDownTimer countDownTimer;
    private long timeLeft = 1500000; //25 min

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                timer.cancel();
                progressBar.setVisibility(View.GONE);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);
            }
        }, 1500000, 500);

        countdown = findViewById(R.id.countdown);
        startTimer();
        }

        public void startTimer(){
            countDownTimer = new CountDownTimer(timeLeft, 1000) {
                @Override
                public void onTick(long l) {
                    timeLeft = l;
                    updateTimer();
                }

                @Override
                public void onFinish() {

                }
            } .start();
        }

        public void updateTimer(){
            int minutes = (int) timeLeft / 60000;
            int seconds = (int) timeLeft % 60000 / 1000;
            String timeLeftText;
            timeLeftText = "" + minutes;
            timeLeftText += ":";
            if (seconds < 10) timeLeftText += "0";
            timeLeftText += seconds;

            countdown.setText(timeLeftText);
        }

    }
