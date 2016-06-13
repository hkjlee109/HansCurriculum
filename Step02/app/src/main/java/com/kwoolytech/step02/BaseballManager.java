package com.kwoolytech.step02;

import android.content.ClipData;
import android.content.ClipDescription;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.Context;

import java.util.Random;

public class BaseballManager {
    private Context mainContext;
    private FrameLayout mainLayout;

    private Random random;

    private final static int BallWidth = 120;
    private final static int BallHeight = 120;

    public BaseballManager(Context context, FrameLayout frameLayout) {
        mainContext = context;
        mainLayout = frameLayout;

        random = new Random();
    }

    public void ThrowBaseball() {
        ImageView image = new ImageView(mainContext);
        image.setBackgroundResource(R.drawable.baseball);

        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(BallWidth, BallHeight,
                                                                      Gravity.NO_GRAVITY);
        param.leftMargin = random.nextInt(mainLayout.getWidth() - BallWidth);
        param.topMargin = random.nextInt(mainLayout.getHeight() - BallHeight);

        image.setLayoutParams(param);
        image.setOnLongClickListener(baseballOnLongClickListener);

        mainLayout.addView(image);

        return;
    }

    View.OnLongClickListener baseballOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        }
    };
}
