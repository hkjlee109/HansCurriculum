package com.kwoolytech.step02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private BaseballManager baseballManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseballManager = new BaseballManager(
                                  MainActivity.this,
                                  (FrameLayout)findViewById(R.id.frameLayout),
                                  findViewById(R.id.imageViewGlove),
                                  AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake));

        findViewById(R.id.buttonThrow).setOnClickListener(baseballManager.getButtonOnClickListener());
        findViewById(R.id.imageViewGlove).setOnDragListener(baseballManager.getGloveOnDragListener());
        findViewById(R.id.frameLayout).setOnDragListener(baseballManager.getLayoutOnDragListener());
    }
}