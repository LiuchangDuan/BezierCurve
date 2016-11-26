package com.example.testdrawbitmapmesh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    private PolylineView mPolylineView;

//    private ImageView ivMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new LayerView(this));

//        setContentView(R.layout.activity_main);
//
//        mPolylineView = (PolylineView) findViewById(R.id.main_pv);
//
//        List<PointF> pointFs = new ArrayList<PointF>();
//
//        pointFs.add(new PointF(0.3F, 0.5F));
//        pointFs.add(new PointF(1F, 2.7F));
//        pointFs.add(new PointF(2F, 3.5F));
//        pointFs.add(new PointF(3F, 3.2F));
//        pointFs.add(new PointF(4F, 1.8F));
//        pointFs.add(new PointF(5F, 1.5F));
//        pointFs.add(new PointF(6F, 2.2F));
//        pointFs.add(new PointF(7F, 5.5F));
//        pointFs.add(new PointF(8F, 7F));
////        pointFs.add(new PointF(8.6F, 5.7F));
//
//        mPolylineView.setData(pointFs, "Money", "Time");

//        setContentView(new WaveView(this));
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
