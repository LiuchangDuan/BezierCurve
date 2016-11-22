package com.example.imageprocessview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ShaderView(this));
//        setContentView(new DreamEffectView(this));
//        setContentView(new ReflectView(this));
//        setContentView(R.layout.activity_main);
    }
}
