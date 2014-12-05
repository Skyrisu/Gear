package gearapp.sstudio.com.gearapp;

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


public class EndScreen extends MainActivity {

    final Context Warn = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        ImageView scoreusual  = (ImageView) findViewById(R.id.imagescoreusual);
        ImageView scorehigh = (ImageView) findViewById(R.id.imagescorehigh);
        TextView score = (TextView) findViewById(R.id.textviewscore);

        if (LvlBest[currentLevel] > turnCounter || LvlBest[currentLevel] == 0){
            LvlBest[currentLevel] = turnCounter;
            Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
            pulse.setRepeatCount(Animation.INFINITE);
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


        TextView t1 = (TextView) findViewById(R.id.backtext);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.nexttext);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textviewscore);
        t1.setTypeface(myCustomFont);

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

    public void EndScreenBack (View v){
        Intent intent = new Intent (this, LevelSelect.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.activity_in_reverse, R.anim.activity_out_reverse);
    }

    public void NextLevel(View v){
        switch(currentLevel){
            case 1:
                Intent nLvl1 = new Intent (this, Level2.class);
                startActivity(nLvl1);
                finish();
                break;
            case 2:
                Intent nLvl2 = new Intent (this, Level3.class);
                startActivity(nLvl2);
                finish();
                break;
            case 3:
                Intent nLvl3 = new Intent (this, Level4.class);
                startActivity(nLvl3);
                finish();
                break;
            case 4:
                Intent nLvl4 = new Intent (this, Level5.class);
                startActivity(nLvl4);
                finish();
                break;
            case 5:
                Intent nLvl5 = new Intent (this, Level6.class);
                startActivity(nLvl5);
                finish();
                break;
            case 6:
                Intent nLvl6 = new Intent (this, Level7.class);
                startActivity(nLvl6);
                finish ();
                break;
            case 7:
                Intent nLvl7 = new Intent (this, Level8.class);
                startActivity(nLvl7);
                finish ();
                break;
            case 8:
                Intent nLvl8 = new Intent (this, Level9.class);
                startActivity(nLvl8);
                finish ();
                break;
            case 9:
                Intent nLvl9 = new Intent (this, Level10.class);
                startActivity(nLvl9);
                finish ();
                break;
            case 10:
                Intent nLvl10 = new Intent (this, Level11.class);
                startActivity(nLvl10);
                finish ();
                break;
            case 11:
                Intent nLvl11 = new Intent (this, Level12.class);
                startActivity(nLvl11);
                finish ();
                break;
            case 12:
                Intent nLvl12 = new Intent (this, Level13.class);
                startActivity(nLvl12);
                finish ();
                break;
            case 13:
                Intent nLvl13 = new Intent (this, Level14.class);
                startActivity(nLvl13);
                finish ();
                break;
            case 14:
                Intent nLvl14 = new Intent (this, Level15.class);
                startActivity(nLvl14);
                finish ();
                break;
            case 15:
                Intent nLvl15 = new Intent (this, Level16.class);
                startActivity(nLvl15);
                finish ();
                break;
            case 16:
                Intent nLvl16 = new Intent (this, Level17.class);
                startActivity(nLvl16);
                finish ();
                break;
            case 17:
                Intent nLvl17 = new Intent (this, Level18.class);
                startActivity(nLvl17);
                finish ();
                break;
            case 18:
                Intent nLvl18 = new Intent (this, LevelSelect2.class);
                startActivity(nLvl18);
                finish ();
                break;
        }
    }
}
