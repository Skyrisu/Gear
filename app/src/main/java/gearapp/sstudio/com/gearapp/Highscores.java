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


public class Highscores extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        TextView t1 = (TextView) findViewById(R.id.textView);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView2);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView3);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView4);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView5);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView6);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView7);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView8);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.textView9);
        t1.setTypeface(myCustomFont);


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
