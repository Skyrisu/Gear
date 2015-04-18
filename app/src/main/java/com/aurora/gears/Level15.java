package com.aurora.gears;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;


public class Level15 extends GameMain {

    final Context Warn = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level15);

        TextView t1 = (TextView) findViewById(R.id.Level15Text);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.MoveText);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.TextViewMoveNumber);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.BestText);
        t1.setTypeface(myCustomFont);
        t1 = (TextView) findViewById(R.id.TextViewBestNumber);
        t1.setTypeface(myCustomFont);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        turnCounter = 0;
        DegreesGear1 = 0;
        DegreesGear2 = 0;
        DegreesGear3 = 0;
        DegreesGear4 = 0;
        currentLevel = 15;

        final TextView viewBest = (TextView) findViewById(R.id.TextViewBestNumber);
        viewBest.setText(String.valueOf(LvlBest[currentLevel]));

        ImageButton gStart3 = (ImageButton) findViewById(R.id.Gear3);
        turn180(gStart3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level12, menu);
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
        Intent intent = new Intent (this, LevelSelect2.class);
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
        playSound(v);
        ImageButton gear1 = (ImageButton) findViewById(R.id.Gear1);
        ImageButton gear2 = (ImageButton) findViewById(R.id.Gear2);
        ImageButton gear3 = (ImageButton) findViewById(R.id.Gear3);
        ImageButton gear4 = (ImageButton) findViewById(R.id.Gear4);
        switch (v.getId()){
            case R.id.Gear1:
                turnLast(gear1, 4);
                break;
            case R.id.Gear2:
                turn(gear1);
                turn(gear2);
                turnLast(gear3, 4);
                break;
            case R.id.Gear3:
                turn(gear1);
                turn(gear3);
                turnLast(gear4, 4);
                break;
            case R.id.Gear4:
                turnLast(gear4, 4);
                break;
        }
        final TextView viewCounter = (TextView) findViewById(R.id.TextViewMoveNumber);
        turnCounter ++;
        viewCounter.setText (String.valueOf(turnCounter));
    }

    public void reload(View v){
        Intent reload = new Intent (this, Level15.class);
        finish();
        startActivity(reload);
    }
}
