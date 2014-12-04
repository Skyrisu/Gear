package gearapp.sstudio.com.gearapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    final Context Warn = this;
    public static int turnCounter;
    public static int DegreesGear1;
    public static int DegreesGear2;
    public static int DegreesGear3;
    public static int currentLevel;
    public static int DegreesGear =0;
    public static int LvlDone;
    public static int[] LvlBest = new int[8];
    public static final String MyPreferences = "LevelDone" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t1 = (TextView) findViewById(R.id.highscoretext);
        TextView t2 = (TextView) findViewById(R.id.creditstext);
        TextView t3 = (TextView) findViewById(R.id.playtext);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        t2.setTypeface(myCustomFont);
        t3.setTypeface(myCustomFont);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Animation gearanim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.NonFunctionGear);
        gear.startAnimation(gearanim);

        if(android.os.Build.VERSION.SDK_INT >= 19) {
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
                        System.exit(0);
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


    public void openCredits(View view) {
        Intent intent = new Intent (this, Credits.class);
        startActivity(intent);
    }

    public void openHighscores(View view) {
        Intent intent = new Intent (this, Highscores.class);
        startActivity(intent);
    }

    public void openPlay(View view) {
        Intent  intent = new Intent (this, LevelSelect.class);
        finish();
        startActivity(intent);
    }

    public void turn90(View startBtn){
        RotateAnimation rotate = new RotateAnimation(0 , 90, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(0);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        startBtn.startAnimation(rotate);
        switch(startBtn.getId()){
            case R.id.Gear1:
                DegreesGear1 = 90;
                break;
            case R.id.Gear2:
                DegreesGear2 = 90;
                break;
            case R.id.Gear3:
                DegreesGear3 = 90;
                break;
        }

    }

    public void turn180(View startBtn){
        RotateAnimation rotate = new RotateAnimation(0 , 180, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(0);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        startBtn.startAnimation(rotate);
        switch(startBtn.getId()){
            case R.id.Gear1:
                DegreesGear1 = 180;
                break;
            case R.id.Gear2:
                DegreesGear2 = 180;
                break;
            case R.id.Gear3:
                DegreesGear3 = 180;
                break;
        }

    }

    public void turn270(View startBtn){
        RotateAnimation rotate = new RotateAnimation(0 , 270, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(0);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        startBtn.startAnimation(rotate);
        switch(startBtn.getId()){
            case R.id.Gear1:
                DegreesGear1 = 270;
                break;
            case R.id.Gear2:
                DegreesGear2 = 270;
                break;
            case R.id.Gear3:
                DegreesGear3 = 270;
                break;
        }

    }

    public void turn(View v,int gearCount){
        final View getGear = v;
        getGear.setEnabled(false);
        if (DegreesGear1 == 360) {
            DegreesGear1 = 0;
        } else if (DegreesGear2 == 360) {
            DegreesGear2 = 0;
        } else if (DegreesGear3 == 360) {
            DegreesGear3 = 0;
        }
        switch(getGear.getId()){
            case R.id.Gear1:
                DegreesGear = DegreesGear1;
                DegreesGear1 = DegreesGear1 +90;
                break;
            case R.id.Gear2:
                DegreesGear = DegreesGear2;
                DegreesGear2 = DegreesGear2 +90;
                break;
            case R.id.Gear3:
                DegreesGear = DegreesGear3;
                DegreesGear3 = DegreesGear3 +90;
                break;
        }
        RotateAnimation rotate = new RotateAnimation(DegreesGear , DegreesGear + 90, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(400);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        v.startAnimation(rotate);

        switch(gearCount){
            case 1:
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (DegreesGear1 == 180) {
                            Intent EndScreen = new Intent (MainActivity.this, EndScreen.class);
                            startActivity(EndScreen);
                            finish();
                        } else {
                            getGear.setEnabled(true);
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 2:
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (DegreesGear1 == 180 && DegreesGear1 == DegreesGear2) {
                            Intent EndScreen = new Intent (MainActivity.this, EndScreen.class);
                            startActivity(EndScreen);
                            finish();
                        } else {
                            getGear.setEnabled(true);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 3:
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (DegreesGear1 == 180 && DegreesGear1 == DegreesGear2 && DegreesGear1 == DegreesGear3) {
                            Intent EndScreen = new Intent (MainActivity.this, EndScreen.class);
                            startActivity(EndScreen);
                            finish ();
                        } else {
                            getGear.setEnabled(true);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
        }
    }


}
