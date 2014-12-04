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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LevelSelect extends MainActivity implements View.OnClickListener{

    final Context Warn = this;

    private List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.Lvl1Btn,
            R.id.Lvl2Btn,
            R.id.Lvl3Btn,
            R.id.Lvl4Btn,
            R.id.Lvl5Btn,
            R.id.Lvl6Btn,
            R.id.Lvl7Btn,
            R.id.Lvl8Btn,
            R.id.Lvl9Btn,
    };

    private List<TextView> textviews;
    private static final int[] TEXTVIEW_IDS = {
            R.id.Lvl1Text,
            R.id.Lvl2Text,
            R.id.Lvl3Text,
            R.id.Lvl4Text,
            R.id.Lvl5Text,
            R.id.Lvl6Text,
            R.id.Lvl7Text,
            R.id.Lvl8Text,
            R.id.Lvl9Text,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");

        TextView t1 = (TextView) findViewById(R.id.Lvl1Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl2Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl3Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl4Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl5Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl6Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl7Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl8Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl9Text);
        t1.setTypeface(myCustomFont);



        SharedPreferences LevelSave = getSharedPreferences(MyPreferences, MODE_PRIVATE);
        LvlDone = LevelSave.getInt("LevelDone", 0);
        for (int i=0; i < LvlBest.length; i++){
            LvlBest[i]= LevelSave.getInt("LevelBest"+i, 0);
        }

        buttons = new ArrayList<Button>(BUTTON_IDS.length);
        for(int id : BUTTON_IDS) {
            Button button = (Button)findViewById(id);
            buttons.add(button);
        }

        textviews = new ArrayList<TextView>(TEXTVIEW_IDS.length);
        for(int id : TEXTVIEW_IDS) {
            TextView textview = (TextView)findViewById(id);
            textviews.add(textview);
        }

        for (int i = 0 ; i < buttons.size() ; i++) {
            if (i < LvlDone+1) {
                buttons.get(i).setEnabled(true);
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

        Animation gearanim = AnimationUtils.loadAnimation(LevelSelect.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.BackGear);
        gear.startAnimation(gearanim);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_select, menu);
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
        Animation anim = AnimationUtils.loadAnimation(LevelSelect.this, R.anim.rotate90);
        v.startAnimation(anim);
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch(btid.getId()){
                    case R.id.Lvl1Btn:
                        Intent lvl1 = new Intent (LevelSelect.this, Level1.class);
                        startActivity(lvl1);
                        finish();
                        break;
                    case R.id.Lvl2Btn:
                        Intent lvl2 = new Intent (LevelSelect.this, Level2.class);
                        startActivity(lvl2);
                        finish();
                        break;
                    case R.id.Lvl3Btn:
                        Intent lvl3 = new Intent (LevelSelect.this, Level3.class);
                        startActivity(lvl3);
                        finish();
                        break;
                    case R.id.Lvl4Btn:
                        Intent lvl4 = new Intent (LevelSelect.this, Level4.class);
                        startActivity(lvl4);
                        finish();
                        break;
                    case R.id.Lvl5Btn:
                        Intent lvl5 = new Intent (LevelSelect.this, Level5.class);
                        startActivity(lvl5);
                        finish();
                        break;
                    case R.id.Lvl6Btn:
                        Intent lvl6 = new Intent (LevelSelect.this, Level6.class);
                        startActivity(lvl6);
                        finish();
                        break;
                    case R.id.Lvl7Btn:
                        Intent lvl7 = new Intent (LevelSelect.this, Level7.class);
                        startActivity(lvl7);
                        finish();
                        break;
                    case R.id.Lvl8Btn:
                        Intent lvl8 = new Intent (LevelSelect.this, Level8.class);
                        startActivity(lvl8);
                        finish();
                        break;
                    case R.id.Lvl9Btn:
                        Intent lvl9 = new Intent (LevelSelect.this, Level9.class);
                        startActivity(lvl9);
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
        Intent  intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
