

package gearapp.sstudio.com.gearapp;

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


public class Level1 extends MainActivity {

    final Context Warn = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        TextView t1 = (TextView) findViewById(R.id.Level1Text);
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



        turnCounter = 0;
        DegreesGear1 = 0;
        DegreesGear2 = 0;
        DegreesGear3 = 0;
        currentLevel = 1;

        final TextView viewBest = (TextView) findViewById(R.id.TextViewBestNumber);
        viewBest.setText (String.valueOf(LvlBest[currentLevel]));

        ImageButton gStart1 = (ImageButton) findViewById(R.id.Gear1);
        turn270(gStart1);
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
        playSound(v);
        ImageButton gear1 = (ImageButton) findViewById(R.id.Gear1);
        turn(gear1, 1);
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

