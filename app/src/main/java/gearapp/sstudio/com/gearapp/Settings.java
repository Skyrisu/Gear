package gearapp.sstudio.com.gearapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;


public class Settings extends MainActivity {

    public int HighDegree;
    public int LevelDegree;
    public int ResetDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        HighDegree=0;
        LevelDegree=0;
        ResetDegree=0;


        TextView t1 = (TextView) findViewById(R.id.scoretip);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.leveltip);
        t1.setTypeface(myCustomFont);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.tiptext);
        t1.setTypeface(myCustomFont);
        final TextView lvlreset = (TextView) findViewById(R.id.LevelResetView);
        lvlreset.setTypeface(myCustomFont);
        final TextView highreset = (TextView) findViewById(R.id.HighResetView);
        highreset.setTypeface(myCustomFont);
        lvlreset.setVisibility(View.INVISIBLE);
        highreset.setVisibility(View.INVISIBLE);
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
        final TextView lvlreset = (TextView) findViewById(R.id.LevelResetView);
        final TextView highreset = (TextView) findViewById(R.id.HighResetView);
        switch(view.getId()){
            case R.id.resetlevel:
                ResetDegree = LevelDegree;
                LevelDegree = LevelDegree +90;
                break;
            case R.id.resetscore:
                ResetDegree = HighDegree;
                HighDegree = HighDegree +90;
                break;
        }
        RotateAnimation rotate = new RotateAnimation(ResetDegree , ResetDegree + 90, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
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
                        editor.commit();
                    }
                    HighDegree = 0;
                    highreset.setVisibility(View.VISIBLE);
                } else if (LevelDegree == 180) {
                    lvlreset.setEnabled(false);
                    SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
                    SharedPreferences.Editor editor = LevelSave.edit();
                    editor.putInt("LevelDone", 0);
                    editor.commit();
                    LevelDegree = 0;
                    lvlreset.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void Back(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
