package com.example.testdrawbitmapmesh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    private ImageView ivMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new WaveView(this));
//        setContentView(new PathView(this));
//        setContentView(new CanvasView(this));
//        setContentView(new BitmapMeshView2(this));
//        setContentView(new BitmapMeshView(this));
//        setContentView(R.layout.activity_main);
//
//        ivMain = (ImageView) findViewById(R.id.main_iv);
//
//        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawColor(Color.RED);
//
//        ivMain.setImageBitmap(bitmap);

    }

}
