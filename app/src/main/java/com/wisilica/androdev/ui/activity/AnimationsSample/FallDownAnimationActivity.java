package com.wisilica.androdev.ui.activity.AnimationsSample;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import com.wisilica.androdev.R;
import com.wisilica.androdev.utility.AnimationUtil;

public class FallDownAnimationActivity extends AppCompatActivity {
    ConstraintLayout constrainLearnMain,constrainLearn,cl_main,constrainLearnSide;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_down_animation);

        constrainLearnMain = findViewById(R.id.constrainLearnMain);
        constrainLearn = findViewById(R.id.constrainLearn);
        constrainLearnSide = findViewById(R.id.constrainLearnSide);
        cl_main = findViewById(R.id.cl_main);


        constrainLearnMain.setVisibility(View.INVISIBLE);
        constrainLearnSide.setVisibility(View.INVISIBLE);

       // fallingDownAnimation(constrainLearnMain, constrainLearn, 3000);

        check();
    }

    private void check() {
        final int[] endX = {0};
        final int[] endY = {0};
        ViewTreeObserver vto = constrainLearnMain.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                constrainLearnMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                endX[0] = constrainLearnMain.getMeasuredWidth();
//                endY[0] = constrainLearnMain.getMeasuredHeight();
              //  AnimationUtil.fallingDownAnimation(constrainLearnMain, constrainLearn, 1000);
                AnimationUtil.horizontalMoveAnimation(constrainLearnSide, constrainLearn, 1000,AnimationUtil.LEFT);
            }
        });

    }


}
