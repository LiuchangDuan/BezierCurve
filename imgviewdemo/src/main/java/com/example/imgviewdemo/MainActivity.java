package com.example.imgviewdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImgView mImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgView = (ImgView) findViewById(R.id.main_pv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        mImgView.setBitmap(bitmap);

    }

}
