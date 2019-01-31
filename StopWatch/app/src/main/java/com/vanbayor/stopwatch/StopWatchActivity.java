package com.vanbayor.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


public class StopWatchActivity extends Activity {
    private int seconds = 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seconds = savedInstanceState.getInt("seconds", seconds);
        running = savedInstanceState.getBoolean("running", running);

        setContentView(R.layout.activity_stop_watch);
        runTimer();

    }

    /**
     * Method gets called before the Activity is destroyed.
     * @param savedInstanceState is the current Bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }


    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;

    }

    public void onClickReset(View view) {
        seconds = 0;
        running = false;
    }

    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%02d:%02d:%02d",
                        hours, minutes, secs);
                timeView.setText(time);

                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
