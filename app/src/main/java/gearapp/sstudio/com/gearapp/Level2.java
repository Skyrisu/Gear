package gearapp.sstudio.com.gearapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Level2 extends MainActivity {

    final Context Warn = this;
    int turnCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        turnCounter = 0;
        DegreesGear1 = 0;
        DegreesGear2 = 0;
        DegreesGear3 = 0;
        currentLevel = 2;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level1, menu);
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
    public void openLvlSelect(View view) {
        Intent intent = new Intent (this, LevelSelect.class);
        startActivity(intent);
        finish();
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

    public void turnGear(View v){
        switch (v.getId()){
            case R.id.Gear1:
                turn1(v);
                DegreesGear1 = DegreesGear1 + 90;
                break;
            case R.id.Gear2:
                turn2(v);
                DegreesGear2 = DegreesGear2 + 90;
                break;
            case R.id.Gear3:
                turn3(v);
                DegreesGear3 = DegreesGear3 + 90;
                break;
        }
        final TextView viewCounter = (TextView) findViewById(R.id.TextViewCounter);
        turnCounter ++;
        viewCounter.setText ("Turns: " + String.valueOf(turnCounter));
        if (DegreesGear1 == 360) {
            DegreesGear1 = 0;
        } else if (DegreesGear2 == 360) {
            DegreesGear2 = 0;
        } else if (DegreesGear3 == 360) {
            DegreesGear3 = 0;
        }
        if (DegreesGear1 == 180 && DegreesGear1 == DegreesGear2 && DegreesGear1 == DegreesGear3){
            Intent EndScreen = new Intent (this, EndScreen.class);
            startActivity(EndScreen);
        }
    }
}
