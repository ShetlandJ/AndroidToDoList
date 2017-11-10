package myfirstgame.todolist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class BeatenQuestActivity extends MyMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaten_quest);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()  {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_beaten:
                        onButtonClicked(R.id.menu_beaten);
                    case R.id.menu_quests:
                        onButtonClicked(R.id.menu_quests);
                    case R.id.menu_profile:
                        onButtonClicked(R.id.menu_quests);
                }
                return true;
            }
        });

    }


}
