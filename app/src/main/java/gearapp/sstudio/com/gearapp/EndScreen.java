package gearapp.sstudio.com.gearapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class EndScreen extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
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
    public void EndScreenBack (View v){
        Intent intent = new Intent (this, LevelSelect.class);
        startActivity(intent);
        finish();
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
                Intent nLvl3 = new Intent (this, LevelSelect.class);
                startActivity(nLvl3);
                finish();
                break;
        }
    }
}
