package com.dqr.www.deepasynctask;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyThread syncRunnable = new MyThread(0);
        for (int i = 0; i < 200; i++) {
            executorService.execute(syncRunnable);
        }


    }


    class MyThread implements Runnable{

        public int tag;
        int ticksCount=5;

        public MyThread(int tag) {
            this.tag = tag;
        }

        @Override
        public void run() {
            ticksCount--;
            Log.d(TAG,Thread.currentThread().getName()+"-----"+ticksCount);
        }
    }

}
