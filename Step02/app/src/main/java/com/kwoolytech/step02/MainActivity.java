package com.kwoolytech.step02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
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
        findViewById(R.id.imageViewGlove).setOnDragListener(gloveOnDragListener);
    }

    Button.OnClickListener buttonOnClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            baseballManager.ThrowBaseball();
        }
    };

    View.OnDragListener gloveOnDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    View view = (View)event.getLocalState();
                    FrameLayout layout = (FrameLayout)view.getParent();
                    layout.removeView(view);
                    break;
                default:
                    break;
            }
            return true;
        }

    };
}