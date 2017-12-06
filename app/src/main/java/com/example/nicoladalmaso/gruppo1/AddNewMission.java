package com.example.nicoladalmaso.gruppo1;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DataManager;
import database.Mission;

public class AddNewMission extends AppCompatActivity {

    Context context;
    public DataManager DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DataManager(this.getApplicationContext());
        setTitle("New Mission");
        setContentView(R.layout.activity_add_new_mission);
        context = this.getApplicationContext();
    }

    /** Dal Maso
     * Opzioni per settaggio nuova toolbar dal /res/menu
     * @param menu
     * @return flag di successo
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addmission_menu, menu);
        return true;
    }

    /** Dal Maso
     * Edit by Lazzarin
     * Cattura degli eventi nella toolbar
     * @param item oggetto nella toolbar catturato
     * @return flag di successo
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_addMission:
                //read input text
                EditText editName =(EditText)findViewById(R.id.input_missionName);
                EditText editDescription = (EditText)findViewById(R.id.input_missionDescription);
                String name = editName.getText().toString();
                String description = editDescription.getText().toString();
                Log.d("verify null",name);
                String voidChar=" ";
                if((name==null)||name.equals("")) {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    name = timeStamp;
                }
                if((description==null)||description.equals("")){
                    description = "Nessuna descrizione";
                }
                File newMissionPath = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/" + name);
                newMissionPath.mkdir();
                Mission miss = new Mission();
                miss.setPersonID(1);
                miss.setName(name);
                miss.setDescription(description);
                miss.setLocation(newMissionPath.getPath());
                DB.addMission(miss);

                //create new directory with input text
                //Start billActivity
                Intent startImageView = new Intent(context, com.example.nicoladalmaso.gruppo1.BillActivity.class);
                startImageView.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Variables.getInstance().setCurrentMissionDir(newMissionPath.getAbsolutePath());
                Log.d("GlobalDir", Variables.getInstance().getCurrentMissionDir());
                startImageView.putExtra("missionName", name);
                startImageView.putExtra("missionDescription",description);
                startImageView.putExtra("missionID", miss.getID());
                context.startActivity(startImageView);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
