package com.kwoolytech.step02;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.Context;

import java.util.Random;

public class BaseballManager {
    private Context mainContext;
    private FrameLayout mainLayout;
    private Random random;
    private View.OnLongClickListener baseballOnLongClickListener;

    public BaseballManager(Context context, FrameLayout frameLayout) {
        mainContext = context;
        mainLayout = frameLayout;
        random = new Random();

        baseballOnLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(null, shadowBuilder, v, 0);
                return true;
            }
        };
    }

    public void ThrowBaseball() {
        ImageView image = new ImageView(mainContext);
        image.setBackgroundResource(R.drawable.baseball);
        image.setOnLongClickListener(baseballOnLongClickListener);

        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(CommonConstant.BallWidth,
                                                                      CommonConstant.BallHeight);
        param.leftMargin = random.nextInt(mainLayout.getWidth() - CommonConstant.BallWidth);
        param.topMargin = random.nextInt(mainLayout.getHeight() - CommonConstant.BallHeight);
        image.setLayoutParams(param);

        mainLayout.addView(image);

        return;
    }
}
