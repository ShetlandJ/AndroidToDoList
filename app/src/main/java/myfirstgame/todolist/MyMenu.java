package myfirstgame.todolist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by James on 10/11/2017.
 */

public class MyMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_navigation_main, menu);

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()  {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.menu_beaten:
//                        onButtonClicked(R.id.menu_beaten);
//                        break;
//                    case R.id.menu_quests:
//                        onButtonClicked(R.id.menu_quests);
//                        break;
//                    case R.id.menu_profile:
//                        onButtonClicked(R.id.menu_profile);
//                }
//                return true;
//            }
//        });

        return true;

    }

    public void onButtonClicked(Integer id){
        if (id == R.id.menu_beaten) {
            Intent i = new Intent(this, BeatenQuestActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_quests) {
            Intent i = new Intent(this, QuestActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_profile) {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }
    }




}
