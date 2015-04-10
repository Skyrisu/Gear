package gearapp.sstudio.com.gearapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Highscores extends MainActivity {

    private List<TextView> TextViews;
    private static final int[] TEXTVIEW_IDS = {
            R.id.textView,
            R.id.textView2,
            R.id.textView3,
            R.id.textView4,
            R.id.textView5,
            R.id.textView6,
            R.id.textView7,
            R.id.textView8,
            R.id.textView9,
            R.id.textView10,
            R.id.textView11,
            R.id.textView12,
            R.id.textView13,
            R.id.textView14,
            R.id.textView15,
            R.id.textView16,
            R.id.textView17,
            R.id.textView18
    };

    private List<TextView> TextViewsScore;
    private static final int[] TEXTVIEWSCORE_IDS = {
            R.id.score1,
            R.id.score2,
            R.id.score3,
            R.id.score4,
            R.id.score5,
            R.id.score6,
            R.id.score7,
            R.id.score8,
            R.id.score9,
            R.id.score10,
            R.id.score11,
            R.id.score12,
            R.id.score13,
            R.id.score14,
            R.id.score15,
            R.id.score16,
            R.id.score17,
            R.id.score18
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        TextViews = new ArrayList<>(TEXTVIEW_IDS.length);
        for(int id : TEXTVIEW_IDS) {
            TextView textview = (TextView)findViewById(id);
            TextViews.add(textview);
        }

        TextViewsScore = new ArrayList<>(TEXTVIEWSCORE_IDS.length);
        for(int id : TEXTVIEWSCORE_IDS) {
            TextView textview = (TextView)findViewById(id);
            TextViewsScore.add(textview);
        }

        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");
        for (int i = 0 ; i < TextViews.size() ; i++) {
            TextViews.get(i).setTypeface(myCustomFont);
            TextViewsScore.get(i).setTypeface(myCustomFont);
        }

        for (int i = 0 ; i < TextViewsScore.size() ; i++) {
                TextViewsScore.get(i).setText(String.valueOf(LvlBest[i+1])+ "    ");
        }



        Animation gearanim = AnimationUtils.loadAnimation(Highscores.this, R.anim.rotateloop);
        final ImageView gear = (ImageView) findViewById(R.id.BackGear);
        gear.startAnimation(gearanim);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscores, menu);
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

    public void Back(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
