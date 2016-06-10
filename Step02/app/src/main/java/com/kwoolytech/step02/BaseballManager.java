package com.kwoolytech.step02;

import android.view.Gravity;
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

        mainLayout.addView(image);

        return;
    }
}
