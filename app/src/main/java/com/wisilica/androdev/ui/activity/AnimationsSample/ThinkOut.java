package com.wisilica.androdev.ui.activity.AnimationsSample;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.CornerPathEffect;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import com.nightonke.wowoviewpager.Animation.*;
import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.WoWoPathView;
import com.nightonke.wowoviewpager.WoWoViewPager;
import com.nightonke.wowoviewpager.WoWoViewPagerAdapter;
import com.wisilica.androdev.R;

public class ThinkOut extends AppCompatActivity {

    WoWoViewPager viewPager;
    WoWoViewPager wowo;
    ConstraintLayout layoutMain;
    ImageView ivShip,ivShipPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thinkout_second);

        pathAnimation();


    }

    @Override
    protected void onResume() {
        super.onResume();
        pathAnimation();
    }

    private void pathAnimation() {
        ivShipPosition= findViewById(R.id.ivShipPosition);
        ivShip = findViewById(R.id.ivShip);
        layoutMain = findViewById(R.id.layoutMain);
        ViewTreeObserver vto4 = layoutMain.getViewTreeObserver();
        vto4.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                moveIt();
            }
        });
    }

    private void moveIt() {
        int[] locationStart = new int[2];
        int startX=0,startY=0;
        ivShip.getLocationOnScreen(locationStart);
        startX = locationStart[0];
        startY = locationStart[1];
        int[]locationEnd=new int[2];
        int endX=0,endY=0;
        ivShipPosition.getLocationOnScreen(locationEnd);
        endX = locationEnd[0];
        endY = locationEnd[1];
        int centerX,centerY;
        centerX = endX;
        centerY = endY-ivShipPosition.getHeight()+ivShipPosition.getHeight()/4;

        int leftX,leftY;
        leftX = 0;
        leftY = centerY/2;

        Path path = new Path();
        path.moveTo(startX+ivShip.getWidth(),0);
        path.quadTo(startX+ivShip.getWidth(),0,leftX,leftY);
        path.quadTo(leftX,leftY,centerX,centerY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivShip, View.X, View.Y, path);
            objectAnimator.setDuration(3000);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.start();
        }
    }

    private void LoginAnimationPage() {
      ///  wowo = findViewById(R.id.viewPager);
//        addEarthAnimation();
//        addCloudAnimation();
//        addTextAnimation();
//        addRocketAnimation();
//        addCircleAnimation();
//        addMeteorAnimation();
//        addPlanetAnimation();
//        addPlanetTargetAnimation();
//        addLoginLayoutAnimation();
//        addButtonAnimation();
//        addEditTextAnimation();
    }


/*
    private void addEarthAnimation() {
        View earth = findViewById(R.id.ivEarth);
        wowo.addAnimation(earth)
                .add(WoWoRotationAnimation.builder().page(0).keepX(0).keepY(0).fromZ(0).toZ(180).ease(Ease.OutBack).build())
                .add(WoWoRotationAnimation.builder().page(1).keepX(0).keepY(0).fromZ(180).toZ(720).ease(Ease.OutBack).build())
                .add(WoWoRotationAnimation.builder().page(2).keepX(0).keepY(0).fromZ(720).toZ(1260).ease(Ease.OutBack).build())
                .add(WoWoScaleAnimation.builder().page(1).fromXY(1).toXY(0.5).ease(Ease.OutBack).build())
                .add(WoWoScaleAnimation.builder().page(2).fromXY(0.5).toXY(0.25).ease(Ease.OutBack).build());
    }

    private void addCloudAnimation() {
        wowo.addAnimation(findViewById(R.id.ic_clo))
                .add(WoWoTranslationAnimation.builder().page(0).fromX(screenW).toX(0).keepY(0).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(screenW).keepY(0).ease(Ease.InBack).sameEaseBack(false).build());

        wowo.addAnimation(findViewById(R.id.cloud_red))
                .add(WoWoTranslationAnimation.builder().page(0).fromX(-screenW).toX(0).keepY(0).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(-screenW).keepY(0).ease(Ease.InBack).sameEaseBack(false).build());

        wowo.addAnimation(findViewById(R.id.cloud_yellow))
                .add(WoWoTranslationAnimation.builder().page(0).keepX(0).fromY(0).toY(dp2px(50)).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(-screenW).keepY(dp2px(50)).ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addTextAnimation() {
        View text = findViewById(R.id.text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) text.setZ(50);
        String[] texts = new String[]{
                "HOME?",
                "OR SKY?",
                "OR UNIVERSE?",
                "Let's Discover More!",
        };
        wowo.addAnimation(text)
                .add(WoWoTextViewTextAnimation.builder().page(0).from(texts[0]).to(texts[1]).build())
                .add(WoWoTextViewTextAnimation.builder().page(1).from(texts[1]).to(texts[2]).build())
                .add(WoWoTextViewTextAnimation.builder().page(2).from(texts[2]).to(texts[3]).build())
                .add(WoWoTextViewColorAnimation.builder().page(1).from("#05502f").to(Color.WHITE).build());
    }

    private void addRocketAnimation() {
        WoWoPathView pathView = (WoWoPathView) findViewById(R.id.path_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) pathView.setZ(50);

        // For different screen size, try to adjust the scale values to see the airplane.
        float xScale = 1;
        float yScale = 1;

        pathView.newPath()
                .pathMoveTo(xScale * (-100), screenH - 100)
                .pathCubicTo(screenW / 2, screenH - 100,
                        screenW / 2, screenH - 100,
                        screenW / 2, yScale * (-100));
        wowo.addAnimation(pathView)
                .add(WoWoPathAnimation.builder().page(0).from(0).to(0.50).path(pathView).build())
                .add(WoWoPathAnimation.builder().page(1).from(0.50).to(0.75).path(pathView).build())
                .add(WoWoPathAnimation.builder().page(2).from(0.75).to(1).path(pathView).build())
                .add(WoWoAlphaAnimation.builder().page(2).from(1).to(0).build());
    }

    private void addCircleAnimation() {
        View circle = findViewById(R.id.circle);
        wowo.addAnimation(circle)
                .add(WoWoScaleAnimation.builder().page(1).fromXY(1).toXY(r * 2 / circle.getWidth()).build())
                .add(WoWoShapeColorAnimation.builder().page(1).from("#f9dc0a").to("#05502f").build());
    }

    private void addMeteorAnimation() {
        View meteor = findViewById(R.id.meteor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) meteor.setZ(50);
        float fullOffset = screenW + meteor.getWidth();
        float offset = fullOffset / 2;
        wowo.addAnimation(meteor)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .fromX(0).fromY(0)
                        .toX(offset).toY(offset).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(offset).fromY(offset)
                        .toX(fullOffset).toY(fullOffset).ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addPlanetAnimation() {
        View planet0 = findViewById(R.id.planet_0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) planet0.setZ(50);
        wowo.addAnimation(planet0)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .keepX(0)
                        .fromY(0).toY(planet0.getHeight() + 200)
                        .ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(0).toX(screenW)
                        .keepY(planet0.getHeight() + 200)
                        .ease(Ease.InBack).sameEaseBack(false).build());

        View planet1 = findViewById(R.id.planet_1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) planet1.setZ(50);
        wowo.addAnimation(planet1)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .fromX(0).toX(-planet1.getWidth())
                        .keepY(0)
                        .ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(-planet1.getWidth()).toX(-screenW - planet1.getWidth())
                        .keepY(0)
                        .ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addPlanetTargetAnimation() {
        wowo.addAnimation(targetPlanet)
                .add(WoWoRotationAnimation.builder().page(1).keepX(0).keepY(0).fromZ(0).toZ(180).ease(Ease.OutBack).build())
                .add(WoWoRotationAnimation.builder().page(2).keepX(0).keepY(0).fromZ(180).toZ(360).ease(Ease.OutBack).build())
                .add(WoWoTranslationAnimation.builder().page(0).keepX(0)
                        .fromY(-screenH / 2 - screenW / 2)
                        .toY(-screenH / 2).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoScaleAnimation.builder().page(1).fromXY(0.25).toXY(0.5).ease(Ease.OutBack).build())
                .add(WoWoScaleAnimation.builder().page(2).fromXY(0.5).toXY(1).ease(Ease.OutBack).build());
    }

    private void addLoginLayoutAnimation() {
        View layout = findViewById(R.id.login_layout);
        wowo.addAnimation(layout)
                .add(WoWoAlphaAnimation.builder().page(1).start(1).end(1).from(0).to(1).build())
                .add(WoWoShapeColorAnimation.builder().page(2).from("#05502f").to("#0aa05f").build())
                .add(WoWoElevationAnimation.builder().page(2).from(0).to(40).build());
    }

    private void addButtonAnimation() {
        View button = findViewById(R.id.button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) button.setZ(50);
        wowo.addAnimation(button)
                .add(WoWoAlphaAnimation.builder().page(2).from(0).to(1).build());
    }

    private void addEditTextAnimation() {
        wowo.addAnimation(findViewById(R.id.username))
                .add(WoWoAlphaAnimation.builder().page(2).from(0).to(1).build());
        wowo.addAnimation(findViewById(R.id.password))
                .add(WoWoAlphaAnimation.builder().page(2).from(0).to(1).build());
    }






    private void viewPagerAnimation() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(WoWoViewPagerAdapter.builder()
                .fragmentManager(getSupportFragmentManager())
                .count(10)                       // Fragment Count
                //.colorsRes(fragmentColorsRes())                // Colors of fragments, with transparent as default
                .build());
        ViewAnimation viewAnimation = new ViewAnimation(findViewById(R.id.viewTest));
        viewAnimation.add(WoWoScaleAnimation.builder().page(0).fromXY(1).toXY(0.5).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(1).fromXY(0.5).toXY(4).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(2).start(0).end(0.5).fromX(4).toX(1).keepY(4).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(2).start(0.5).end(1).keepX(1).fromY(4).toY(1).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(3).start(0).end(0.5).keepX(1).fromY(1).toY(3).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(3).start(0.5).end(1).fromX(1).toX(3).keepY(3).build());

        viewPager.addAnimation(viewAnimation);
        viewPager.setEase(Ease.InCirc);
        viewPager.setUseSameEaseBack(true);
        viewPager.ready();
    }

    */
}
