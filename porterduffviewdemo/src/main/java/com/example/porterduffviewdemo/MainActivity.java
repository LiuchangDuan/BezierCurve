package com.example.porterduffviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EraserView(this));
//        setContentView(new ScreenView(this));
//        setContentView(new DisOutView(this));
//        setContentView(new DisInView(this));
//        setContentView(new PorterDuffView(this));
//        setContentView(R.layout.activity_main);
    }
}
