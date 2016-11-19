package com.example.rotateanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        assert imageView != null;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 计算中心点（这里是使用view的中心作为旋转的中心点）
                final float centerX = v.getWidth() / 2.0f;
                final float centerY = v.getHeight() / 2.0f;
                // 括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
                final Rotate3Animation rotation = new Rotate3Animation(MainActivity.this,
                        0, 180, centerX, centerY, 0f, true);
                // 设置动画时长
                rotation.setDuration(3000);
                // 保持旋转后效果
                rotation.setFillAfter(true);
                // 设置插值器
                rotation.setInterpolator(new LinearInterpolator());
                v.startAnimation(rotation);
            }
        });
    }
}
