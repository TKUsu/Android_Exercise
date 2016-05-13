package com.su.test_03broadcast;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class testService extends Service {

    final static String MY_ACTION = "testService.MY_ACTION";

    TestServiceReceiver testServiceReceiver;
    boolean running;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Toast.makeText(getBaseContext(),
                "TestServiceReceiver.onCreate",
                Toast.LENGTH_LONG).show();
        testServiceReceiver = new TestServiceReceiver();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Toast.makeText(getBaseContext(),
                "TestServiceReceiver.onStartCommand",
                Toast.LENGTH_LONG).show();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MY_ACTION);
        registerReceiver(testServiceReceiver, intentFilter);
        running = true;

        MyThread myThread = new MyThread();
        myThread.start();

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        this.unregisterReceiver(testServiceReceiver);
        super.onDestroy();
    }

    public class MyThread extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            // TODO Auto-generated method stub
            while(running){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setAction(MainActivity.MY_ACTION);
                intent.putExtra("timestamp", System.currentTimeMillis());
                sendBroadcast(intent);
            }
        }

    }

    public class TestServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            int rqs = arg1.getIntExtra("RQS", 0);
            if (rqs == MainActivity.RQS_STOP_SERVICE){
                Toast.makeText(getBaseContext(),
                        "TestServiceReceiver.onReceive w/ RQS_STOP_SERVICE",
                        Toast.LENGTH_LONG).show();
                running = false;
                stopSelf();
            }
        }
    }

}