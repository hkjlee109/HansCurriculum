package com.kwoolytech.step02;

import android.content.res.Resources;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.Context;

import java.util.Random;

public class BaseballManager {
    private Context                  mainContext;
    private Resources                mainResources;
    private FrameLayout              mainLayout;
    private View                     mainGloveView;
    private Animation                mainStrikeAnimation;
    private Random                   random;
    private View.OnLongClickListener baseballOnLongClickListener;
    private Button.OnClickListener   buttonOnClickListener;
    private View.OnDragListener      gloveOnDragListener;
    private View.OnDragListener      layoutOnDragListener;

    public BaseballManager(Context context, FrameLayout frameLayout,
                           View gloveView, Animation strikeAnimation) {
        mainContext         = context;
        mainResources       = mainContext.getResources();
        mainLayout          = frameLayout;
        mainGloveView       = gloveView;
        mainStrikeAnimation = strikeAnimation;
        random              = new Random();

        baseballOnLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(null, shadowBuilder, v, 0);
                return true;
            }
        };

        buttonOnClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThrowBaseball();
            }
        };

        gloveOnDragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        View view = (View)event.getLocalState();
                        FrameLayout layout = (FrameLayout)view.getParent();
                        layout.removeView(view);
                        mainGloveView.startAnimation(mainStrikeAnimation);
                        break;
                    default:
                        break;
                }
                return true;
            }
        };

        layoutOnDragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        View view = (View)event.getLocalState();
                        view.animate()
                                .x(event.getX() - mainResources.getDimensionPixelSize(R.dimen.ball_width)/2)
                                .y(event.getY() - mainResources.getDimensionPixelSize(R.dimen.ball_height)/2)
                                .setDuration(1000).start();
                        break;
                    default:
                        break;
                }
                return true;
            }
        };
    }

    private void ThrowBaseball() {
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

    public Button.OnClickListener GetButtonOnClickListener() { return buttonOnClickListener; }
    public View.OnDragListener    GetGloveOnDragListener()   { return gloveOnDragListener; }
    public View.OnDragListener    GetLayoutOnDragListener()  { return layoutOnDragListener; }
}
