package com.kwz.longpicdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {
    Handler mHandler = new Handler();
    private run r;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        r = new run();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("TAG", "run: -->>" + 11 + "threadName" + Thread.currentThread().getName());
                mHandler.post(r);
            }
        }, 1000, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null)
            mHandler.removeCallbacks(r);
        timer.cancel();
    }

    class run implements Runnable {
        @Override
        public void run() {
            Log.i("TAG", "run: -->>" + 22 + "threadName" + Thread.currentThread().getName());
        }
    }
}
