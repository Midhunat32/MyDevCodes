package com.wisilica.androdev.ui.activity.AnimationsSample;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageView;
import com.wisilica.androdev.R;

public class AnimationPath extends AppCompatActivity {

    ImageView   ivFirebase, ivFirebaseTemp;
    ConstraintLayout ivAndroid,ivMove;
    ConstraintLayout layout;
    Button btnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_path);

        ivMove = findViewById(R.id.ivAndroidTemp);
        ivAndroid = findViewById(R.id.ivAndroid);
        btnAnimation = findViewById(R.id.btnAnimation);
        ivAndroid.setVisibility(View.GONE);
        layout = findViewById(R.id.layout);
        ivFirebase = findViewById(R.id.ivFirebase);
        ivFirebaseTemp = findViewById(R.id.ivFirebaseTemp);
        ivFirebase.setVisibility(View.GONE);

        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAndroid.setVisibility(View.INVISIBLE);
                ivFirebase.setVisibility(View.INVISIBLE);

                fallingDownAnimation(ivAndroid, ivMove, 1000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fallingDownAnimation(ivFirebase, ivFirebaseTemp, 1000);
                    }
                }, 200);

            }
        });


    }

    public void fallingDownAnimation(final View mainView, View temView, int duration) {
        int endX = 0, endY = 0;
        int[] location = new int[2];
        temView.getLocationOnScreen(location);
        endX = location[0];
        endY = location[1];
        int mainViewHeight = temView.getHeight();
        endY = endY - (mainViewHeight / 2);
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


//    private void animationUtility() {
//        int[] location = new int[2];
//        ivMove.getLocationOnScreen(location);
//        int endX  = location[0];
//        int endY  = location[1];
//        ivAndroid.getLocationInWindow(location);
//        ivAndroid.setVisibility(View.VISIBLE);
//        final ValueAnimator va = ValueAnimator.ofFloat(0f, endY);
//        va.setDuration(1000);
//        va.setInterpolator(new BounceInterpolator());
//        va.setInterpolator(new OvershootInterpolator());
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            public void onAnimationUpdate(ValueAnimator animation) {
//                ivAndroid.setTranslationY((float)animation.getAnimatedValue());
//            }
//        });
//        va.start();
//    }


}
