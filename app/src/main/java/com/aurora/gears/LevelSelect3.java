package com.aurora.gears;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.auroa.level.Level19;
import com.auroa.level.Level20;
import com.auroa.level.Level21;
import com.auroa.level.Level22;
import com.auroa.level.Level23;
import com.auroa.level.Level24;
import com.auroa.level.Level25;
import com.auroa.level.Level26;
import com.auroa.level.Level27;

import java.util.ArrayList;
import java.util.List;


public class LevelSelect3 extends MainActivity implements View.OnClickListener {

    final Context Warn = this;

    private List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.Lvl19Btn,
            R.id.Lvl20Btn,
            R.id.Lvl21Btn,
            R.id.Lvl22Btn,
            R.id.Lvl23Btn,
            R.id.Lvl24Btn,
            R.id.Lvl25Btn,
            R.id.Lvl26Btn,
            R.id.Lvl27Btn,
    };

    private List<TextView> textviews;
    private static final int[] TEXTVIEW_IDS = {
            R.id.Lvl19Text,
            R.id.Lvl20Text,
            R.id.Lvl21Text,
            R.id.Lvl22Text,
            R.id.Lvl23Text,
            R.id.Lvl24Text,
            R.id.Lvl25Text,
            R.id.Lvl26Text,
            R.id.Lvl27Text,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select3);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/fbsbltc.ttf");

        TextView t1 = (TextView) findViewById(R.id.Lvl19Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl20Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl21Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl22Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl23Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl24Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl25Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl26Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl27Text);
        t1.setTypeface(myCustomFont);


        SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
        LvlDone = LevelSave.getInt("LevelDone", 0);
        for (int i = 0; i < LvlBest.length; i++) {
            LvlBest[i] = LevelSave.getInt("LevelBest" + i, 0);
        }

        buttons = new ArrayList<Button>(BUTTON_IDS.length);
        for (int id : BUTTON_IDS) {
            Button button = (Button) findViewById(id);
            buttons.add(button);
        }

        textviews = new ArrayList<TextView>(TEXTVIEW_IDS.length);
        for (int id : TEXTVIEW_IDS) {
            TextView textview = (TextView) findViewById(id);
            textviews.add(textview);
        }

        for (int i = 0; i < buttons.size(); i++) {
            if ((i + 18) < LvlDone + 1) {
                buttons.get((i)).setEnabled(true);
                buttons.get(i).setBackgroundResource(R.drawable.levelgear);
                buttons.get(i).setText(" ");
                textviews.get(i).setVisibility(View.VISIBLE);
            } else {
                buttons.get(i).setEnabled(false);
                buttons.get(i).setBackgroundResource(R.drawable.lock);
                buttons.get(i).setText(" ");
                textviews.get(i).setVisibility(View.INVISIBLE);
            }
        }

        Animation gearanim = AnimationUtils.loadAnimation(LevelSelect3.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.BackGear);
        gear.startAnimation(gearanim);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_select3, menu);
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

    public void backToLvlSelect2(View v) {
        Intent intent = new Intent(this, LevelSelect2.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.activity_in_reverse, R.anim.activity_out_reverse);
    }

    public void onBackPressed() {
        AlertDialog.Builder ExitWarningBuild = new AlertDialog.Builder(Warn);
        ExitWarningBuild.setTitle("Do you really want to close the app?");
        ExitWarningBuild
                .setMessage("Click yes to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ExitWarning = ExitWarningBuild.create();
        ExitWarning.show();
    }

    public void onClick(View v) {
        final View btid = v;

        playSound(v);
        Animation anim = AnimationUtils.loadAnimation(LevelSelect3.this, R.anim.rotate90);
        v.startAnimation(anim);
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch (btid.getId()) {
                    case R.id.Lvl19Btn:
                        Intent lvl19 = new Intent(LevelSelect3.this, Level19.class);
                        startActivity(lvl19);
                        finish();
                        break;
                    case R.id.Lvl20Btn:
                        Intent lvl20 = new Intent(LevelSelect3.this, Level20.class);
                        startActivity(lvl20);
                        finish();
                        break;
                    case R.id.Lvl21Btn:
                        Intent lvl21 = new Intent(LevelSelect3.this, Level21.class);
                        startActivity(lvl21);
                        finish();
                        break;
                    case R.id.Lvl22Btn:
                        Intent lvl22 = new Intent(LevelSelect3.this, Level22.class);
                        startActivity(lvl22);
                        finish();
                        break;
                    case R.id.Lvl23Btn:
                        Intent lvl23 = new Intent(LevelSelect3.this, Level23.class);
                        startActivity(lvl23);
                        finish();
                        break;
                    case R.id.Lvl24Btn:
                        Intent lvl24 = new Intent(LevelSelect3.this, Level24.class);
                        startActivity(lvl24);
                        finish();
                        break;
                    case R.id.Lvl25Btn:
                        Intent lvl25 = new Intent(LevelSelect3.this, Level25.class);
                        startActivity(lvl25);
                        finish();
                        break;
                    case R.id.Lvl26Btn:
                        Intent lvl26 = new Intent(LevelSelect3.this, Level26.class);
                        startActivity(lvl26);
                        finish();
                        break;
                    case R.id.Lvl27Btn:
                        Intent lvl27 = new Intent(LevelSelect3.this, Level27.class);
                        startActivity(lvl27);
                        finish();
                        break;
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
