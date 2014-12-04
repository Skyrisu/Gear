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


public class LevelSelect2 extends MainActivity implements View.OnClickListener{

    final Context Warn = this;

    private List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.Lvl10Btn,
            R.id.Lvl11Btn,
            R.id.Lvl12Btn,
            R.id.Lvl13Btn,
            R.id.Lvl14Btn,
            R.id.Lvl15Btn,
            R.id.Lvl16Btn,
            R.id.Lvl17Btn,
            R.id.Lvl18Btn,
    };

    private List<TextView> textviews;
    private static final int[] TEXTVIEW_IDS = {
            R.id.Lvl10Text,
            R.id.Lvl11Text,
            R.id.Lvl12Text,
            R.id.Lvl13Text,
            R.id.Lvl14Text,
            R.id.Lvl15Text,
            R.id.Lvl16Text,
            R.id.Lvl17Text,
            R.id.Lvl18Text,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");

        TextView t1 = (TextView) findViewById(R.id.Lvl10Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl11Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl12Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl13Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl14Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl15Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl16Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl17Text);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.Lvl18Text);
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

        Animation gearanim = AnimationUtils.loadAnimation(LevelSelect2.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.BackGear);
        gear.startAnimation(gearanim);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_select2, menu);
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

    public void backToLvlSelect (View v){
        Intent  intent = new Intent (this, LevelSelect.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.activity_in_reverse, R.anim.activity_out_reverse);
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
        Animation anim = AnimationUtils.loadAnimation(LevelSelect2.this, R.anim.rotate90);
        v.startAnimation(anim);
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch(btid.getId()){
                    case R.id.Lvl10Btn:
                        Intent lvl10 = new Intent (LevelSelect2.this, Level10.class);
                        startActivity(lvl10);
                        finish();
                        break;
                    case R.id.Lvl11Btn:
                        Intent lvl11 = new Intent (LevelSelect2.this, Level11.class);
                        startActivity(lvl11);
                        finish();
                        break;
                    case R.id.Lvl12Btn:
                        Intent lvl12 = new Intent (LevelSelect2.this, Level12.class);
                        startActivity(lvl12);
                        finish();
                        break;
                    case R.id.Lvl13Btn:
                        Intent lvl13 = new Intent (LevelSelect2.this, Level13.class);
                        startActivity(lvl13);
                        finish();
                        break;
                    case R.id.Lvl14Btn:
                        Intent lvl14 = new Intent (LevelSelect2.this, Level14.class);
                        startActivity(lvl14);
                        finish();
                        break;
                    case R.id.Lvl15Btn:
                        Intent lvl15 = new Intent (LevelSelect2.this, Level15.class);
                        startActivity(lvl15);
                        finish();
                        break;
                    case R.id.Lvl16Btn:
                        Intent lvl16 = new Intent (LevelSelect2.this, Level16.class);
                        startActivity(lvl16);
                        finish();
                        break;
                    case R.id.Lvl17Btn:
                        Intent lvl17 = new Intent (LevelSelect2.this, Level17.class);
                        startActivity(lvl17);
                        finish();
                        break;
                    case R.id.Lvl18Btn:
                        Intent lvl18 = new Intent (LevelSelect2.this, Level18.class);
                        startActivity(lvl18);
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
