package com.aurora.gears;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends Activity {

    final Context Warn = this;
    public static int lvlcomplete;
    public static int currentLevel;
    public static int turnCounter;
    public static int DegreesGear1;
    public static int DegreesGear2;
    public static int DegreesGear3;
    public static int DegreesGear4;
    public static int DegreesGear = 0;
    public static int LvlDone;
    public static int[] LvlBest = new int[28];
    public static final String MyPreferences = "LevelDone";
    static InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        lvlcomplete = 0;

        SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
        LvlDone = LevelSave.getInt("LevelDone", 0);
        for (int i = 0; i < LvlBest.length; i++) {
            LvlBest[i] = LevelSave.getInt("LevelBest" + i, 0);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (getIntent().getBooleanExtra("LOGOUT", false)) {
            finish();
        }

        Animation gearanim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.NonFunctionGear);
        gear.startAnimation(gearanim);

        if (android.os.Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                    | View.INVISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void createAd() {
        // Create an ad.
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-4154270865207644/7455937416");

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load the interstitial ad.
        interstitialAd.loadAd(adRequest);
    }

    public static InterstitialAd getAd() {
        return interstitialAd;
    }

    public void exit(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("LOGOUT", true);
        startActivity(intent);
    }

    public void twitter(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=AuroraAeternum")));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/AuroraAeternum")));
        }
    }

    public void facebook(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://pages/Aurora-Aeternum/314500878744848")));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Aurora-Aeternum/314500878744848")));
        }
    }

    public void googleplus(View view) {
        openGPlus("107284151606336378022");
    }

    public void openGPlus(String profile) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.google.android.apps.plus",
                    "com.google.android.apps.plus.phone.UrlGatewayActivity");
            intent.putExtra("customAppUri", profile);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + profile + "/posts")));
        }
    }

    public void playSound(View view) {

        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.gearsound);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
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

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        finish();
    }

    public void openHighscores(View view) {
        Intent intent = new Intent(this, Highscores.class);
        startActivity(intent);
        finish();
    }

    public void openPlay(View view) {
        Intent intent = new Intent(this, LevelSelect.class);
        startActivity(intent);
        finish();
    }

}