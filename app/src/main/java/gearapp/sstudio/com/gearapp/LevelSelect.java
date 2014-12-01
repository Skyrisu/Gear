package gearapp.sstudio.com.gearapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class LevelSelect extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        Button blvl1 = (Button) findViewById(R.id.button);
        blvl1.setOnClickListener(this);
        Button blvl2 = (Button) findViewById(R.id.button2);
        blvl2.setOnClickListener(this);
        Button blvl3 = (Button) findViewById(R.id.button2);
        blvl3.setOnClickListener(this);

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
                    case R.id.button:
                        Intent lvl1 = new Intent (LevelSelect.this, Level1.class);
                        startActivity(lvl1);
                        finish();
                        break;
                    case R.id.button2:
                        Intent lvl2 = new Intent (LevelSelect.this, Level2.class);
                        startActivity(lvl2);
                        finish();
                        break;
                    case R.id.button3:
                        Intent lvl3 = new Intent (LevelSelect.this, Level3.class);
                        startActivity(lvl3);
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
    }
}
