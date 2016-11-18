package com.example.rotatetaiji;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TaiJiView taiJiView = new TaiJiView(this);
        setContentView(taiJiView);
        final Handler handler = new Handler() {
            private float degrees = 0;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                taiJiView.setRotate(degrees += 1);
                this.sendEmptyMessageDelayed(0, 20);
            }
        };
        handler.sendEmptyMessageDelayed(0, 10);
    }
}
