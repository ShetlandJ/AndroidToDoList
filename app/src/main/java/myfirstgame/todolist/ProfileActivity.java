package myfirstgame.todolist;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ProfileActivity extends MyMenu {

    TextView nameText;
    TextView strengthExp;
    TextView staminaExp;
    TextView intelligenceExp;
    TextView socialExp;
    TextView totalExp;
    TextView level;
    TextView levelOver;
    TextView expNeeded;

    Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        final DBHelper dbHelper = new DBHelper(this);

        player = Player.load(dbHelper, "James");

        Category strength = Category.load(dbHelper, "Strength");
        Category stamina = Category.load(dbHelper, "Stamina");
        Category intelligence = Category.load(dbHelper, "Intelligence");
        Category social = Category.load(dbHelper, "Social");



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


            strengthExp = findViewById(R.id.strengthEXP);
            strengthExp.setText(strength.getLevel().toString());
            strengthExp.bringToFront();

//            staminaExp = findViewById(R.id.staminaExp);
//            staminaExp.setText("STAMINA: " + player.getStamina().toString());
//            staminaExp.bringToFront();
//
//            intelligenceExp = findViewById(R.id.intelligenceExp);
//            intelligenceExp.setText("INTELLIGENCE: " + player.getIntelligence().toString());
//            intelligenceExp.bringToFront();
//
//            socialExp = findViewById(R.id.socialExp);
//            socialExp.setText("SOCIAL: " + player.getSocial().toString());
//            socialExp.bringToFront();
//
//            totalExp = findViewById(R.id.totalExp);
//            totalExp.setText("TOTAL EXP: " + player.getTotalExperience().toString());
//            totalExp.bringToFront();
//
//            level = findViewById(R.id.levelNumber);
//            level.setText("LEVEL: " + player.getLevel().toString());
//            level.bringToFront();
//
//            levelOver = findViewById(R.id.levelExp);
//            levelOver.setText(player.getLevel().toString());
//
//            expNeeded = findViewById(R.id.levelCounter);
//            expNeeded.setText("LEVEL (" + player.getTotalExperience() + " / " + Level.getLevelExp(player.getLevel() + 1) + ")");
//
            ProgressBar simpleProgressBar = findViewById(R.id.simpleProgressBar);
            simpleProgressBar.setMax(100);
            simpleProgressBar.setProgress(playerProgressByPercentage());

            ProgressBar strengthProgressBar = findViewById(R.id.strengthProgressBar);
            strengthProgressBar.setMax(100);
            strengthProgressBar.setProgress(categoryProgressByPercentage(strength));



    }

    public int playerProgressByPercentage(){
        int myExp = player.getTotalExperience();

        int currentExpNeeded = Level.getLevelExp(player.getLevel());

        int nextLevelExp = Level.getLevelExp(player.getLevel() + 1);

        double ratio = (1.0*myExp - currentExpNeeded) / (1.0*nextLevelExp - currentExpNeeded);

        return (int) (100 * ratio);
    }


    public int categoryProgressByPercentage(Category category){
        Integer categoryExp = category.getExp();

        int currentExpNeeded = Level.getCategoryLevelExp(category.getLevel());

        int nextLevelExp = Level.getCategoryLevelExp(category.getLevel() + 1);

        double ratio = (1.0*categoryExp - currentExpNeeded) / (1.0*nextLevelExp - currentExpNeeded);

        return (int) (100 * ratio);
    }

}
