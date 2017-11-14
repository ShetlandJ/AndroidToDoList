package myfirstgame.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class QuestActivity extends MyMenu {

    TextView header;
    Typeface typeface;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quests);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        final DBHelper dbHelper = new DBHelper(this);
        ArrayList<Quest> questList = Quest.allIncomplete(dbHelper);

        final QuestViewAdapter questAdapter = new QuestViewAdapter(this, questList);
        final ListView listView = findViewById(R.id.questList);
        listView.setAdapter(questAdapter);


        header = findViewById(R.id.questsOfName);
        typeface = Typeface.createFromAsset(getAssets(), "Metalista.otf");
        header.setTypeface(typeface);
        header.setTextColor(getResources().getColor(R.color.soBlueItsBlack, getResources().newTheme()));


        header.setText("Quests of James:");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setSelectedItemId(R.id.menu_quests);


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




        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(v.getContext(), AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });


    }



    public void onQuestAddButtonClicked(View button){
        FloatingActionButton fab = findViewById(R.id.addQuestBtn);

        Intent i = new Intent(this, AddQuestActivity.class);
        startActivity(i);
    }

    public void getQuest(View listItem){
        Quest quest = (Quest) listItem.getTag();
        Intent i = new Intent(this, QuestItemActivity.class);
        i.putExtra("name", quest.getName());
        i.putExtra("expValue", quest.getExpValue().toString());
        i.putExtra("category", quest.showCategoryNameByNumber(quest.getCategory()));
        i.putExtra("date", quest.getDate());
        startActivity(i);
    }


    View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
            public boolean onTouch(View v, MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.performClick();
                    Log.i("TAG", "touched down");
                    break;
                case MotionEvent.ACTION_MOVE:
                    v.performClick();
                    Log.i("TAG", "moving: (" + x + ", " + y + ")");
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    Log.i("TAG", "touched up");
                    break;
            }

            return true;
        }
    };




}
