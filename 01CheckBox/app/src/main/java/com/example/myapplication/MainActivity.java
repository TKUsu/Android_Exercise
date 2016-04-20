package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private CheckBox ch1,ch2,ch3,ch4;
    private ImageView im1,im2,im3,im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ch1 = (CheckBox) findViewById(R.id.checkBox);
        ch2 = (CheckBox) findViewById(R.id.checkBox2);
        ch3 = (CheckBox) findViewById(R.id.checkBox3);
        ch4 = (CheckBox) findViewById(R.id.checkBox4);

        ch1.setOnCheckedChangeListener(this);
        ch2.setOnCheckedChangeListener(this);
        ch3.setOnCheckedChangeListener(this);
        ch4.setOnCheckedChangeListener(this);

        im1 = (ImageView) findViewById(R.id.imageView5);
        im2 = (ImageView) findViewById(R.id.imageView6);
        im3 = (ImageView) findViewById(R.id.imageView7);
        im4 = (ImageView) findViewById(R.id.imageView8);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            if(buttonView==ch1)
                im1.setImageResource(R.mipmap.ic_hanbergur);

            if(buttonView==ch2)
                im2.setImageResource(R.mipmap.ic_2);

            if(buttonView==ch3)
                im3.setImageResource(R.mipmap.ic_3);

            if (buttonView==ch4)
                im4.setImageResource(R.mipmap.ic_4);
        }else{
            if(buttonView==ch1)
                im1.setImageResource(android.R.color.transparent);

            if(buttonView==ch2)
                im2.setImageResource(android.R.color.transparent);

            if(buttonView==ch3)
                im3.setImageResource(android.R.color.transparent);

            if (buttonView==ch4)
                im4.setImageResource(android.R.color.transparent);
        }
    }
}
