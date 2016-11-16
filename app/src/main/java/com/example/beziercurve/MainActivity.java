package com.example.beziercurve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new BezierView3(this));
//        setContentView(new BezierView2(this));
        setContentView(R.layout.activity_main);
        final BezierView3 myView = (BezierView3) findViewById(R.id.myView);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.control1:
                        myView.setMode(true);
                        break;
                    case R.id.control2:
                        myView.setMode(false);
                        break;
                }
            }
        });
//        RadioButton control1 = (RadioButton) findViewById(R.id.control1);
//        RadioButton control2 = (RadioButton) findViewById(R.id.control2);
    }

}
