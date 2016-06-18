package com.kwoolytech.step02;

import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.Context;

import java.util.Random;

public class BaseballManager {
    private Context mainContext;
    private Resources mainResources;
    private FrameLayout mainLayout;
    private Random random;
    private View.OnLongClickListener baseballOnLongClickListener;

    public BaseballManager(Context context, FrameLayout frameLayout) {
        mainContext = context;
        mainResources = mainContext.getResources();
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

        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(
                                               mainResources.getDimensionPixelSize(R.dimen.ball_width),
                                               mainResources.getDimensionPixelSize(R.dimen.ball_height));
        param.leftMargin = random.nextInt(mainLayout.getWidth() - mainResources.getDimensionPixelSize(R.dimen.ball_width));
        param.topMargin = random.nextInt(mainLayout.getHeight() - mainResources.getDimensionPixelSize(R.dimen.ball_height));
        image.setLayoutParams(param);

        mainLayout.addView(image);

        return;
    }
}
