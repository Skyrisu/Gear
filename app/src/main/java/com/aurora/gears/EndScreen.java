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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.*;


public class EndScreen extends MainActivity {

    final Context Warn = this;
    public static InterstitialAd interstitialAd;
    private Intent nLvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        final ImageButton nextButton = (ImageButton) findViewById(R.id.NextLevel);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-4154270865207644/7455937416");

        if (currentLevel % 3 == 0) {
            nextButton.setEnabled(false);
            requestNewInterstitial();
        }

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startActivity(nLvl);
            }

            @Override
            public void onAdLoaded() {
                nextButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad (int errorCode) {
                nextButton.setEnabled(true);
            }
        });

        ImageView scoreusual  = (ImageView) findViewById(R.id.imagescoreusual);
        ImageView scorehigh = (ImageView) findViewById(R.id.imagescorehigh);
        TextView score = (TextView) findViewById(R.id.textviewscore);

        if (LvlBest[currentLevel] > turnCounter || LvlBest[currentLevel] == 0){
            LvlBest[currentLevel] = turnCounter;
            Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
            scorehigh.startAnimation(pulse);
            scoreusual.setVisibility(ImageView.INVISIBLE);
            score.setText(String.valueOf(turnCounter));
        } else {
            scorehigh.setVisibility(ImageView.INVISIBLE);
            score.setText(String.valueOf(turnCounter));
        }

        if (currentLevel > LvlDone){
            LvlDone = currentLevel;
        }

        SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = LevelSave.edit();
        editor.putInt ("LevelDone", LvlDone);
        editor.putInt ("LevelBest"+currentLevel, LvlBest[currentLevel]);
        editor.commit();

        // Setting Typefaces
        TextView t1 = (TextView) findViewById(R.id.backtext);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.nexttext);
        t1.setTypeface(myCustomFont);
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

    public void onBackPressed(){
        AlertDialog.Builder ExitWarningBuild = new AlertDialog.Builder(Warn);
        ExitWarningBuild.setTitle("You rly want to close the app?");
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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                // .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);
    }

    public void EndScreenBack (View v){
        Intent intent = new Intent (this, LevelSelect.class);
        startActivity(intent);
        finish();
    }

    public void NextLevel(View v){
        switch(currentLevel){
            case 1:
                nLvl = new Intent (this, Level2.class);
                startActivity(nLvl);
                break;
            case 2:
                nLvl = new Intent (this, Level3.class);
                startActivity(nLvl);
                break;
            case 3:
                nLvl = new Intent (this, Level4.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 4:
                nLvl = new Intent (this, Level5.class);
                startActivity(nLvl);
                break;
            case 5:
                nLvl = new Intent (this, Level6.class);
                startActivity(nLvl);
                break;
            case 6:
                nLvl = new Intent (this, Level7.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 7:
                nLvl = new Intent (this, Level8.class);
                startActivity(nLvl);
                break;
            case 8:
                nLvl = new Intent (this, Level9.class);
                startActivity(nLvl);
                break;
            case 9:
                nLvl = new Intent (this, Level10.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 10:
                nLvl = new Intent (this, Level11.class);
                startActivity(nLvl);
                break;
            case 11:
                nLvl = new Intent (this, Level12.class);
                startActivity(nLvl);
                break;
            case 12:
                nLvl = new Intent (this, Level13.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 13:
                nLvl = new Intent (this, Level14.class);
                startActivity(nLvl);
                break;
            case 14:
                nLvl = new Intent (this, Level15.class);
                startActivity(nLvl);
                break;
            case 15:
                nLvl = new Intent (this, Level16.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
            case 16:
                nLvl = new Intent (this, Level17.class);
                startActivity(nLvl);
                break;
            case 17:
                nLvl = new Intent (this, Level18.class);
                startActivity(nLvl);
                break;
            case 18:
                nLvl = new Intent (this, LevelSelect2.class);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startActivity(nLvl);
                }
                break;
        }
        finish();
    }
}
