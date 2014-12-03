package gearapp.sstudio.com.gearapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class EndScreen extends MainActivity {

    final Context Warn = this;

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
                Intent nLvl3 = new Intent (this, Level4.class);
                startActivity(nLvl3);
                finish();
                break;
            case 4:
                Intent nLvl4 = new Intent (this, Level5.class);
                startActivity(nLvl4);
                finish();
                break;
            case 5:
                Intent nLvl5 = new Intent (this, Level6.class);
                startActivity(nLvl5);
                finish();
                break;
            case 6:
                Intent nLvl6 = new Intent (this, Level7.class);
                startActivity(nLvl6);
                finish();
                break;
            case 7:
                Intent nLvl7 = new Intent (this, Level8.class);
                startActivity(nLvl7);
                finish();
                break;
            case 8:
                Intent nLvl8 = new Intent (this, Level9.class);
                startActivity(nLvl8);
                finish();
                break;
            case 9:
                Intent nLvl9 = new Intent (this, LevelSelect.class);
                startActivity(nLvl9);
                finish();
                break;
        }
    }
}
