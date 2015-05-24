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

import com.auroa.level.Level28;
import com.auroa.level.Level29;
import com.auroa.level.Level30;
import com.auroa.level.Level31;
import com.auroa.level.Level32;
import com.auroa.level.Level33;
import com.auroa.level.Level34;
import com.auroa.level.Level35;
import com.auroa.level.Level36;

import java.util.ArrayList;
import java.util.List;


public class LevelSelect4 extends MainActivity implements View.OnClickListener {

    final Context Warn = this;

    private List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.Lvl28Btn,
            R.id.Lvl29Btn,
            R.id.Lvl30Btn,
            R.id.Lvl31Btn,
            R.id.Lvl32Btn,
            R.id.Lvl33Btn,
            R.id.Lvl34Btn,
            R.id.Lvl35Btn,
            R.id.Lvl36Btn,
    };

    private List<TextView> textviews;
    private static final int[] TEXTVIEW_IDS = {
            R.id.Lvl28Text,
            R.id.Lvl29Text,
            R.id.Lvl30Text,
            R.id.Lvl31Text,
            R.id.Lvl32Text,
            R.id.Lvl33Text,
            R.id.Lvl34Text,
            R.id.Lvl35Text,
            R.id.Lvl36Text,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select4);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/fbsbltc.ttf");

        TextView t1 = (TextView) findViewById(R.id.Lvl28Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl29Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl30Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl31Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl32Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl33Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl34Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl35Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl36Text);
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
            if ((i + 27) < LvlDone + 1) {
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

        Animation gearanim = AnimationUtils.loadAnimation(LevelSelect4.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.BackGear);
        gear.startAnimation(gearanim);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_select4, menu);
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

    public void backToLvlSelect3(View v) {
        Intent intent = new Intent(this, LevelSelect3.class);
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
        Animation anim = AnimationUtils.loadAnimation(LevelSelect4.this, R.anim.rotate90);
        v.startAnimation(anim);
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch (btid.getId()) {
                    case R.id.Lvl28Btn:
                        Intent lvl28 = new Intent(LevelSelect4.this, Level28.class);
                        startActivity(lvl28);
                        finish();
                        break;
                    case R.id.Lvl29Btn:
                        Intent lvl29 = new Intent(LevelSelect4.this, Level29.class);
                        startActivity(lvl29);
                        finish();
                        break;
                    case R.id.Lvl30Btn:
                        Intent lvl30 = new Intent(LevelSelect4.this, Level30.class);
                        startActivity(lvl30);
                        finish();
                        break;
                    case R.id.Lvl31Btn:
                        Intent lvl31 = new Intent(LevelSelect4.this, Level31.class);
                        startActivity(lvl31);
                        finish();
                        break;
                    case R.id.Lvl32Btn:
                        Intent lvl32 = new Intent(LevelSelect4.this, Level32.class);
                        startActivity(lvl32);
                        finish();
                        break;
                    case R.id.Lvl33Btn:
                        Intent lvl33 = new Intent(LevelSelect4.this, Level33.class);
                        startActivity(lvl33);
                        finish();
                        break;
                    case R.id.Lvl34Btn:
                        Intent lvl34 = new Intent(LevelSelect4.this, Level34.class);
                        startActivity(lvl34);
                        finish();
                        break;
                    case R.id.Lvl35Btn:
                        Intent lvl35 = new Intent(LevelSelect4.this, Level35.class);
                        startActivity(lvl35);
                        finish();
                        break;
                    case R.id.Lvl36Btn:
                        Intent lvl36 = new Intent(LevelSelect4.this, Level36.class);
                        startActivity(lvl36);
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
