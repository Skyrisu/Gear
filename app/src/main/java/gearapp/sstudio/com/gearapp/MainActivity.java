package gearapp.sstudio.com.gearapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;


public class MainActivity extends Activity {

    public float DegreesGear1;
    public float DegreesGear2;
    public float DegreesGear3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void turn1(View view){
        RotateAnimation rotate = new RotateAnimation(DegreesGear1 ,DegreesGear1 + 90 , Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(400);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }
    public void turn2(View view){
        RotateAnimation rotate = new RotateAnimation(DegreesGear2 ,DegreesGear2 + 90 , Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(400);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }
    public void turn3(View view){
        RotateAnimation rotate = new RotateAnimation(DegreesGear3 ,DegreesGear3 + 90 , Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(400);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }
}
