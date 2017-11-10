package myfirstgame.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestActivity extends MyMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quests);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<Quest> questList = Quest.all(dbHelper);

        QuestViewAdapter questAdapter = new QuestViewAdapter(this, questList);
        ListView listView = findViewById(R.id.questList);
        listView.setAdapter(questAdapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()  {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_beaten:
                        onButtonClicked(R.id.menu_beaten);
                        break;
                    case R.id.menu_quests:
                        onButtonClicked(R.id.menu_quests);
                        break;
                    case R.id.menu_profile:
                        onButtonClicked(R.id.menu_profile);
                        break;
                }
                return true;
            }
        });
    }

    public void onQuestAddButtonClicked(View button){
        FloatingActionButton fab = findViewById(R.id.addQuestBtn);

        Intent i = new Intent(this, AddQuestActivity.class);
        startActivity(i);
    }



}
