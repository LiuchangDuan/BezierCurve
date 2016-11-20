package com.example.customviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomView mCustomView;

//    private int radius;

//    private LinearLayout llRoot;

//    @SuppressWarnings("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            // 设置自定义View的半径值
//            mCustomView.setRadius(radius);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView = (CustomView) findViewById(R.id.main_cv);

        // 开线程
        new Thread(mCustomView).start();

//        // 开线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 确保线程不断执行不断刷新界面
//                while (true) {
//                    try {
//                        // 如果半径小于200则自加否则大于200后重置半径值以实现往复
//                        if (radius <= 200) {
//                            radius += 10;
//
//                            // 发消息给Handler处理
//                            mHandler.obtainMessage().sendToTarget();
//                        } else {
//                            radius = 0;
//                        }
//
//                        // 每执行一次暂停40毫秒
//                        Thread.sleep(40);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();


//        llRoot = (LinearLayout) findViewById(R.id.main_root_ll);
//        llRoot.addView(new CustomView(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        // 界面销毁后清除Handler的引用
//        mHandler.removeCallbacksAndMessages(null);
    }

}
