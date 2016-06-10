package com.kwoolytech.step02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private BaseballManager baseballManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseballManager = new BaseballManager(
                                  MainActivity.this,
                                  (FrameLayout)findViewById(R.id.frameLayout));

        findViewById(R.id.buttonThrow).setOnClickListener(buttonOnClickListener);
    }

    Button.OnClickListener buttonOnClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            baseballManager.ThrowBaseball();
        }
    };
}