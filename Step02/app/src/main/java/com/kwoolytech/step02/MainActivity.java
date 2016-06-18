package com.kwoolytech.step02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private BaseballManager baseballManager;
    Animation shakeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseballManager = new BaseballManager(
                                  MainActivity.this,
                                  (FrameLayout)findViewById(R.id.frameLayout));

        findViewById(R.id.buttonThrow).setOnClickListener(buttonOnClickListener);
        findViewById(R.id.imageViewGlove).setOnDragListener(gloveOnDragListener);
        findViewById(R.id.frameLayout).setOnDragListener(layoutOnDragListener);

        shakeAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
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
                    findViewById(R.id.imageViewGlove).startAnimation(shakeAnimation);
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    View.OnDragListener layoutOnDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    View view = (View)event.getLocalState();
                    view.animate()
                            .x(event.getX() - MainActivity.this.getResources().getDimensionPixelSize(R.dimen.ball_width)/2)
                            .y(event.getY() - MainActivity.this.getResources().getDimensionPixelSize(R.dimen.ball_height)/2)
                            .setDuration(1000).start();
                    break;
                default:
                    break;
            }
            return true;
        }
    };
}