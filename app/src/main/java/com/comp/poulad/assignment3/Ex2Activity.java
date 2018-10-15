package com.comp.poulad.assignment3;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.concurrent.ThreadLocalRandom;

public class Ex2Activity extends AppCompatActivity {

    private AnimationDrawable _frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        findViewById(R.id.bStart).setOnClickListener(v -> startAnimation());
        findViewById(R.id.bStop).setOnClickListener(v -> stopAnimation());
    }

    private void startAnimation() {
        Drawable[] frames = new Drawable[5];
        frames[0] = getDrawable(R.drawable.frame0);
        frames[1] = getDrawable(R.drawable.frame1);
        frames[2] = getDrawable(R.drawable.frame2);
        frames[3] = getDrawable(R.drawable.frame3);
        frames[4] = getDrawable(R.drawable.frame4);

        _frameAnimation = new AnimationDrawable();
        _frameAnimation.setOneShot(false); // loop continuously
        for (Drawable frame : frames) {
            int randomDuration = ThreadLocalRandom.current().nextInt(100, 500 + 1);
            _frameAnimation.addFrame(frame, randomDuration);
        }

        ImageView img = findViewById(R.id.ivAnimation);
        img.setBackgroundDrawable(_frameAnimation);

        _frameAnimation.start();
    }

    private void stopAnimation() {
        _frameAnimation.stop();
    }
}
