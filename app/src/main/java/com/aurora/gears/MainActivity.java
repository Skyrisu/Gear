package com.aurora.gears;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
    private static int sessionDepth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.d("Wat soll der Muell", "OnCreate");

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

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

    private boolean mIsBound = false;
    public MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        doUnbindService();
        //Log.d("Wat soll der Muell", "OnDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d("Wat soll der Muell", "OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("Wat soll der Muell", "OnResume");
        if(mServ != null) {
            mServ.resumeMusic();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.d("Wat soll der Muell", "OnStart");
        sessionDepth++;
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.d("Wat soll der Muell", "OnStop");
        if (sessionDepth > 0)
            sessionDepth--;
        if (sessionDepth == 0) {
            if(mServ != null) {
                mServ.pauseMusic();
            }
        }
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