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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.auroa.level.Level10;
import com.auroa.level.Level11;
import com.auroa.level.Level12;
import com.auroa.level.Level13;
import com.auroa.level.Level14;
import com.auroa.level.Level15;
import com.auroa.level.Level16;
import com.auroa.level.Level17;
import com.auroa.level.Level18;
import com.auroa.level.Level19;
import com.auroa.level.Level2;
import com.auroa.level.Level20;
import com.auroa.level.Level21;
import com.auroa.level.Level22;
import com.auroa.level.Level23;
import com.auroa.level.Level24;
import com.auroa.level.Level25;
import com.auroa.level.Level26;
import com.auroa.level.Level27;
import com.auroa.level.Level28;
import com.auroa.level.Level29;
import com.auroa.level.Level3;
import com.auroa.level.Level30;
import com.auroa.level.Level31;
import com.auroa.level.Level32;
import com.auroa.level.Level33;
import com.auroa.level.Level34;
import com.auroa.level.Level35;
import com.auroa.level.Level36;
import com.auroa.level.Level4;
import com.auroa.level.Level5;
import com.auroa.level.Level6;
import com.auroa.level.Level7;
import com.auroa.level.Level8;
import com.auroa.level.Level9;
import com.google.android.gms.ads.*;


public class EndScreen extends GameMain {

    final Context Warn = this;
    private Intent nLvl;
    static InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        if (currentLevel % 3 == 0) {
            interstitialAd = MainActivity.getAd();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    startActivity(nLvl);
                }
            });
        }

        ImageView scoreusual = (ImageView) findViewById(R.id.imagescoreusual);
        ImageView scorehigh = (ImageView) findViewById(R.id.imagescorehigh);
        TextView score = (TextView) findViewById(R.id.textviewscore);

        if (LvlBest[currentLevel] > turnCounter || LvlBest[currentLevel] == 0) {
            LvlBest[currentLevel] = turnCounter;
            Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
            scorehigh.startAnimation(pulse);
            scoreusual.setVisibility(ImageView.INVISIBLE);
            score.setText(String.valueOf(turnCounter));
        } else {
            scorehigh.setVisibility(ImageView.INVISIBLE);
            score.setText(String.valueOf(turnCounter));
        }

        if (currentLevel > LvlDone) {
            LvlDone = currentLevel;
        }

        SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = LevelSave.edit();
        editor.putInt("LevelDone", LvlDone);
        editor.putInt("LevelBest" + currentLevel, LvlBest[currentLevel]);
        editor.commit();

        // Setting Typefaces
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/fbsbltc.ttf");
        score.setTypeface(myCustomFont);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_end_screen, menu);
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

    public void EndScreenBack(View v) {
        Intent intent = new Intent(this, LevelSelect.class);
        startActivity(intent);
        finish();
    }

    public void NextLevel(View v) {
        switch (currentLevel) {
            case 1:
                nLvl = new Intent(this, Level2.class);
                startActivity(nLvl);
                break;
            case 2:
                nLvl = new Intent(this, Level3.class);
                startActivity(nLvl);
                break;
            case 3:
                nLvl = new Intent(this, Level4.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 4:
                nLvl = new Intent(this, Level5.class);
                startActivity(nLvl);
                break;
            case 5:
                nLvl = new Intent(this, Level6.class);
                startActivity(nLvl);
                break;
            case 6:
                nLvl = new Intent(this, Level7.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 7:
                nLvl = new Intent(this, Level8.class);
                startActivity(nLvl);
                break;
            case 8:
                nLvl = new Intent(this, Level9.class);
                startActivity(nLvl);
                break;
            case 9:
                nLvl = new Intent(this, Level10.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 10:
                nLvl = new Intent(this, Level11.class);
                startActivity(nLvl);
                break;
            case 11:
                nLvl = new Intent(this, Level12.class);
                startActivity(nLvl);
                break;
            case 12:
                nLvl = new Intent(this, Level13.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 13:
                nLvl = new Intent(this, Level14.class);
                startActivity(nLvl);
                break;
            case 14:
                nLvl = new Intent(this, Level15.class);
                startActivity(nLvl);
                break;
            case 15:
                nLvl = new Intent(this, Level16.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 16:
                nLvl = new Intent(this, Level17.class);
                startActivity(nLvl);
                break;
            case 17:
                nLvl = new Intent(this, Level18.class);
                startActivity(nLvl);
                break;
            case 18:
                nLvl = new Intent(this, Level19.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 19:
                nLvl = new Intent(this, Level20.class);
                startActivity(nLvl);
                break;
            case 20:
                nLvl = new Intent(this, Level21.class);
                startActivity(nLvl);
                break;
            case 21:
                nLvl = new Intent(this, Level22.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 22:
                nLvl = new Intent(this, Level23.class);
                startActivity(nLvl);
                break;
            case 23:
                nLvl = new Intent(this, Level24.class);
                startActivity(nLvl);
                break;
            case 24:
                nLvl = new Intent(this, Level25.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 25:
                nLvl = new Intent(this, Level26.class);
                startActivity(nLvl);
                break;
            case 26:
                nLvl = new Intent(this, Level27.class);
                startActivity(nLvl);
                break;
            case 27:
                nLvl = new Intent(this, Level28.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 28:
                nLvl = new Intent(this, Level29.class);
                startActivity(nLvl);
                break;
            case 29:
                nLvl = new Intent(this, Level30.class);
                startActivity(nLvl);
                break;
            case 30:
                nLvl = new Intent(this, Level31.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 31:
                nLvl = new Intent(this, Level32.class);
                startActivity(nLvl);
                break;
            case 32:
                nLvl = new Intent(this, Level33.class);
                startActivity(nLvl);
                break;
            case 33:
                nLvl = new Intent(this, Level34.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
            case 34:
                nLvl = new Intent(this, Level35.class);
                startActivity(nLvl);
                break;
            case 35:
                nLvl = new Intent(this, Level36.class);
                startActivity(nLvl);
                break;
            case 36:
                nLvl = new Intent(this, LevelSelect4.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
        }
        finish();
    }
}
