package com.auroa.level;

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

import com.aurora.gears.GameMain;
import com.aurora.gears.LevelSelect;
import com.aurora.gears.R;


public class Level3 extends GameMain {

    final Context Warn = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);

        TextView t1 = (TextView) findViewById(R.id.Level3Text);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/fbsbltc.ttf");
        t1.setTypeface(myCustomFont);
        turnCounter = 0;
        currentLevel = 3;

        TextView viewTurn = (TextView) findViewById(R.id.TextViewMoveNumber);
        viewTurn.setTypeface(myCustomFont);
        viewTurn.setText("Turn: " + String.valueOf(turnCounter));

        TextView viewBest = (TextView) findViewById(R.id.TextViewBestNumber);
        viewBest.setTypeface(myCustomFont);
        viewBest.setText("Best: " + String.valueOf(LvlBest[currentLevel]));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        DegreesGear1 = 0;
        DegreesGear2 = 0;
        DegreesGear3 = 0;


        ImageButton gStart2 = (ImageButton) findViewById(R.id.Gear2);
        turn180(gStart2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level3, menu);
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
        Intent intent = new Intent(this, LevelSelect.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        AlertDialog.Builder ExitWarningBuild = new AlertDialog.Builder(Warn);
        ExitWarningBuild.setTitle("Do you really want to close the app?");
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

    public void turnGear(View v) {
        playSound(v);
        ImageButton gear1 = (ImageButton) findViewById(R.id.Gear1);
        ImageButton gear2 = (ImageButton) findViewById(R.id.Gear2);
        switch (v.getId()) {
            case R.id.Gear1:
                turn(gear1);
                turnLast(gear2, 2);
                break;
            case R.id.Gear2:
                turnLast(gear2, 2);
                break;
        }
        TextView viewCounter = (TextView) findViewById(R.id.TextViewMoveNumber);
        turnCounter++;
        viewCounter.setText("Turn: " + String.valueOf(turnCounter));
    }

    public void reload(View v) {
        Intent reload = new Intent(this, Level3.class);
        finish();
        startActivity(reload);
    }
}
