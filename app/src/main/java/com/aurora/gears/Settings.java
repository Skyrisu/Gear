package com.aurora.gears;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Settings extends MainActivity {

    public int HighDegree;
    public int LevelDegree;
    public int ResetDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        HighDegree = 0;
        LevelDegree = 0;
        ResetDegree = 0;

        TextView t1 = (TextView) findViewById(R.id.scoretip);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.leveltip);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.tiptext);
        t1.setTypeface(myCustomFont);

        Animation gearAnim = AnimationUtils.loadAnimation(Settings.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.BackGear);
        gear.startAnimation(gearAnim);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void turnReset(View view) {

        playSound(view);
        final ImageButton lvlreset = (ImageButton) findViewById(R.id.resetlevel);
        final ImageButton highreset = (ImageButton) findViewById(R.id.resetscore);
        switch (view.getId()) {
            case R.id.resetlevel:
                ResetDegree = LevelDegree;
                LevelDegree = LevelDegree + 90;
                break;
            case R.id.resetscore:
                ResetDegree = HighDegree;
                HighDegree = HighDegree + 90;
                break;
        }
        RotateAnimation rotate = new RotateAnimation(ResetDegree, ResetDegree + 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(400);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);

        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (HighDegree == 180) {
                    highreset.setEnabled(false);
                    SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
                    SharedPreferences.Editor editor = LevelSave.edit();
                    for (int i = 0; i < LvlBest.length; i++) {
                        editor.putInt("LevelBest" + (i + 1), 0);
                        editor.apply();
                    }
                    HighDegree = 0;
                    highreset.setRotation(180);
                    highreset.setImageResource(R.drawable.check);
                } else if (LevelDegree == 180) {
                    lvlreset.setEnabled(false);
                    SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
                    SharedPreferences.Editor editor = LevelSave.edit();
                    editor.putInt("LevelDone", 0);
                    editor.apply();
                    LevelDegree = 0;
                    lvlreset.setRotation(180);
                    lvlreset.setImageResource(R.drawable.check);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
