package com.example.ingsw.gruppo3;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import database.DataManager;
import database.MissionEntity;

/**
 * Modified by Marco Olivieri on 03/12/2017
 * Modified by Step on 03/12/2017.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton newMissionButton;
    ListView missionsList;
    TextView noticeEmptyText;
    DataManager dataManager;
    List<MissionEntity> missions;
    MissionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(com.sw.ing.gestionescontrini.R.layout.activity_main);
        dataManager = new DataManager(this);

        newMissionButton = findViewById(R.id.newMissionButton);
        missionsList = findViewById(R.id.missionsList);
        noticeEmptyText = findViewById(R.id.emptyNoticeTextView);

        MissionEntity test = new MissionEntity("name",null,null,"location",1);
        missions = new ArrayList<MissionEntity>();
        missions.add(test);
        adapter = new MissionAdapter(this, R.layout.mission_row_custom, missions);
        missionsList.setAdapter(adapter);
        newMissionButton.setOnClickListener(this);
        checkInitialization();
        loadMissions();
    }

    /**
     * Initialize of activity
     * Check if there are some mission and show a message
     */
    private void checkInitialization(){
        if(missionsList.getAdapter().getCount()==0){
            noticeEmptyText.setEnabled(Boolean.TRUE);
            missionsList.setEnabled(Boolean.FALSE);
        }
    }

    public void callAddmission(){
        Intent callAddMission = new Intent(this, AddMission.class);
        callAddMission.putExtra(IntentCodes.DATAMANAGER_INTENT_CODE,dataManager);
        startActivity(callAddMission);
    }


    @Override
    public void onClick(View view) {
        if(view instanceof FloatingActionButton){
            callAddmission();
        }
    }

    public void loadMissions(){
        missions.addAll(dataManager.getAllMissions());
    }
    public void addMission(MissionEntity missionEntity) {
        missions.add(missionEntity);
    }

}
