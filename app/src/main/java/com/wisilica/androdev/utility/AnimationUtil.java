package com.wisilica.androdev.utility;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;

public class AnimationUtil{

    public static final int LEFT=0;
    public static final int RIGHT=1;



    public static void fallingDownAnimation(final View mainView, View temView, int duration) {
        int[] location = new int[2];
        int endX=0,endY=0;
        temView.getLocationOnScreen(location);
        endX = location[0];
        endY = location[1];
        int mainViewHeight = mainView.getHeight();
        endY = endY - (mainViewHeight+(mainViewHeight/2));
        mainView.setVisibility(View.VISIBLE);
        final ValueAnimator va = ValueAnimator.ofFloat(0f, endY);
        va.setDuration(duration);
        va.reverse();
        va.setInterpolator(new BounceInterpolator());
        va.setInterpolator(new OvershootInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                mainView.setTranslationY((float) animation.getAnimatedValue());
            }
        });
        va.start();
    }

    public static void horizontalMoveAnimation(final View mainView, View temView, int duration,int side) {
        int[] location = new int[2];
        int endX=0,endY=0;
        temView.getLocationOnScreen(location);
        endX = location[0];
        endY = location[1];
        int mainViewWidth = mainView.getWidth();

        if (side == LEFT){
            endX = -(endY - (mainViewWidth+(mainViewWidth/2)));
        }else if (side == RIGHT){
            endX = endY - (mainViewWidth+(mainViewWidth/2));
        }


        mainView.setVisibility(View.VISIBLE);
        final ValueAnimator va = ValueAnimator.ofFloat(0f, endX);
        va.setDuration(duration);
        va.reverse();
        va.setInterpolator(new BounceInterpolator());
        va.setInterpolator(new OvershootInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                mainView.setTranslationX((float) animation.getAnimatedValue());
            }
        });
        va.start();
    }



}
