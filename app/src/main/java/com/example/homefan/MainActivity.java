package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton off;
    ImageView fan;
    ObjectAnimator rotateAnimator;
    Switch light;
    SeekBar speedbar;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        off = (ToggleButton) findViewById(R.id.off);
        fan = (ImageView) findViewById(R.id.fan);
        light = (Switch) findViewById(R.id.light);
        speedbar = (SeekBar) findViewById(R.id.speedbar);

        // rotate
        rotateAnimator=ObjectAnimator.ofFloat(fan, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        //button on-off
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(off.isChecked()){
                    rotateAnimator.setDuration(SPEED[3]);
                    rotateAnimator.start();
                } else {
                    rotateAnimator.end();
                }
            }
        });

        //color
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);


        //switch light
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(light.isChecked()){
                    gd.setColors(new int[]{ Color.YELLOW , Color.TRANSPARENT });
                    fan.setBackground(gd);
                } else {
                    fan.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        // mengubah speed
        speedbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
                } else {
                    rotateAnimator.end();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
