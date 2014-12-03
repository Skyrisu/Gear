package gearapp.sstudio.com.gearapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Level9 extends MainActivity {

    final Context Warn = this;
    int turnCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level9);
        turnCounter = 0;
        DegreesGear1 = 0;
        DegreesGear2 = 0;
        DegreesGear3 = 0;
        currentLevel = 6;
        ImageButton gStart1 = (ImageButton) findViewById(R.id.Gear1);
        turn180(gStart1);
        ImageButton gStart2 = (ImageButton) findViewById(R.id.Gear2);
        turn270(gStart2);
        ImageButton gStart3 = (ImageButton) findViewById(R.id.Gear3);
        turn270(gStart3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level9, menu);
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
        ImageButton gear1 = (ImageButton) findViewById(R.id.Gear1);
        ImageButton gear2 = (ImageButton) findViewById(R.id.Gear2);
        ImageButton gear3 = (ImageButton) findViewById(R.id.Gear3);
        if (DegreesGear1 == 360) {
            DegreesGear1 = 0;
        } else if (DegreesGear2 == 360) {
            DegreesGear2 = 0;
        } else if (DegreesGear3 == 360) {
            DegreesGear3 = 0;
        }
        switch (v.getId()){
            case R.id.Gear1:
                turn(gear1, 3);
                turn(gear2, 3);
                turn(gear3, 3);
                break;
            case R.id.Gear2:
                turn(gear1, 3);
                turn(gear2, 3);
                break;
            case R.id.Gear3:
                turn(gear1, 3);
                turn(gear3, 3);
                break;
        }
        final TextView viewCounter = (TextView) findViewById(R.id.TextViewMoveNumber);
        turnCounter ++;
        viewCounter.setText (String.valueOf(turnCounter));
    }

    public void reload(View v){
        Intent reload = new Intent (this, Level1.class);
        finish();
        startActivity(reload);
    }
}
