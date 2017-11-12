package myfirstgame.todolist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_ID;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_INTELLIGENCE_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_LEVEL;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_NAME;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_SOCIAL_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_STAMINA_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_STRENGTH_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_TABLE_NAME;


public class ProfileActivity extends MyMenu {

    TextView nameText;
    Player player;
    SQLiteDatabase db;
    Cursor cursorPlayers;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PROFILE_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_NAME));
            Integer strength = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_STRENGTH_EXP));
            Integer stamina = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_STAMINA_EXP));
            Integer intelligence = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_INTELLIGENCE_EXP));
            Integer social = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_SOCIAL_EXP));
            Double level = cursor.getDouble(cursor.getColumnIndex(PROFILE_COLUMN_LEVEL));

            Player player = new Player(id, name, strength, stamina, intelligence, social, level);
            cursor.close();


            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
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

            nameText = findViewById(R.id.name);
            nameText.setText(player.getName());


        }

    }
}
