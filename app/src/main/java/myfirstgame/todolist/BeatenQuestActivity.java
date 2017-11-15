package myfirstgame.todolist;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BeatenQuestActivity extends MyMenu {

    TextView header;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaten_quest);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<Quest> questList = Quest.allComplete(dbHelper);

        BeatenQuestAdapter questAdapter = new BeatenQuestAdapter(this, questList);
        ListView listView = findViewById(R.id.beatenQuests);
        listView.setAdapter(questAdapter);


        header = findViewById(R.id.beatenQuestsHeader);
        typeface = Typeface.createFromAsset(getAssets(), "Metalista.otf");
        header.setTypeface(typeface);
        header.setTextColor(getResources().getColor(R.color.soBlueItsBlack, getResources().newTheme()));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.menu_beaten);

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
}
