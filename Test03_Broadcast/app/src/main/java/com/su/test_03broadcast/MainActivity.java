package com.su.test_03broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    final static String MY_ACTION = "testActivity.MY_ACTION";

    TextView textData;

    public static final int RQS_STOP_SERVICE = 1;
    MyReceiver myReceiver;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textData = (TextView)findViewById(R.id.data);
        Button buttonStart = (Button)findViewById(R.id.start);
        Button buttonStop = (Button)findViewById(R.id.stop);
        buttonStart.setOnClickListener(buttonStartOnClickListener);
        buttonStop.setOnClickListener(buttonStopOnClickListener);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MY_ACTION);
        registerReceiver(myReceiver, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        unregisterReceiver(myReceiver);
        super.onStop();
    }

    Button.OnClickListener buttonStartOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(MainActivity.this,testService.class);
            MainActivity.this.startService(intent);
        }};

    Button.OnClickListener buttonStopOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setAction(testService.MY_ACTION);
            intent.putExtra("RQS", RQS_STOP_SERVICE);
            sendBroadcast(intent);
        }};

    private class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            long timestamp = arg1.getLongExtra("timestamp", 0);
            long curtime = System.currentTimeMillis();
            long delay = curtime - timestamp;
            textData.setText(String.valueOf(timestamp)
                    + " : " + String.valueOf(curtime)
                    + " delay " + String.valueOf(delay)
                    + "(ms)");
        }

    }
}