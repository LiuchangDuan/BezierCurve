package com.example.fontviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MaskFilterView(this));
//        setContentView(new StaticLayoutView(this));
//        setContentView(new FontView(this));
//        setContentView(R.layout.activity_main);
    }
}
