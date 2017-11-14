package myfirstgame.todolist;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ProfileActivity extends MyMenu {

    MyTextView header;
    MyTextView strengthExp;
    MyTextView staminaExp;
    MyTextView intelligenceExp;
    MyTextView socialExp;
    MyTextView levelOver;
    MyTextView expNeeded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        final DBHelper dbHelper = new DBHelper(this);


        Category strength = Category.load(dbHelper, "Strength");
        Category stamina = Category.load(dbHelper, "Stamina");
        Category intelligence = Category.load(dbHelper, "Intelligence");
        Category social = Category.load(dbHelper, "Social");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setSelectedItemId(R.id.menu_profile);

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

        Player user = Player.load(dbHelper, "James");

            header = findViewById(R.id.profileHeader);
            Typeface typeface = Typeface.createFromAsset(getAssets(), "Metalista.otf");
            header.setTypeface(typeface);
            header.setTextColor(getResources().getColor(R.color.soBlueItsBlack, getResources().newTheme()));
            header.setText("you_irl");

            strengthExp = findViewById(R.id.strengthEXP);
            strengthExp.setText(strength.getLevel().toString());
            strengthExp.bringToFront();

            staminaExp = findViewById(R.id.staminaEXP);
            staminaExp.setText(stamina.getLevel().toString());
            staminaExp.bringToFront();

            intelligenceExp = findViewById(R.id.intelligenceEXP);
            intelligenceExp.setText(intelligence.getLevel().toString());
            intelligenceExp.bringToFront();

            socialExp = findViewById(R.id.socialEXP);
            socialExp.setText(social.getLevel().toString());
            socialExp.bringToFront();

            levelOver = findViewById(R.id.levelExp);
            levelOver.setText(user.getLevel().toString());

            expNeeded = findViewById(R.id.expNeeded);
            expNeeded.setText("LEVEL (" + user.getTotalExperience() + " / " + Level.getLevelExp(user.getLevel() + 1) + ")");

            ProgressBar simpleProgressBar = findViewById(R.id.simpleProgressBar);
            simpleProgressBar.setMax(100);
            simpleProgressBar.setProgress(playerProgressByPercentage());

            ProgressBar strengthProgressBar = findViewById(R.id.strengthProgressBar);
            strengthProgressBar.setMax(100);
            strengthProgressBar.setProgress(categoryProgressByPercentage(strength));

            ProgressBar staminaProgressBar = findViewById(R.id.staminaProgressBar);
            staminaProgressBar.setMax(100);
            staminaProgressBar.setProgress(categoryProgressByPercentage(stamina));

            ProgressBar intelligenceBar = findViewById(R.id.intelligenceProgressBar);
            intelligenceBar.setMax(100);
            intelligenceBar.setProgress(categoryProgressByPercentage(intelligence));

            ProgressBar socialBar = findViewById(R.id.socialProgressBar);
            socialBar.setMax(100);
            socialBar.setProgress(categoryProgressByPercentage(social));

    }

    public int playerProgressByPercentage(){
        DBHelper dbHelper = new DBHelper(this);
        Player user = Player.load(dbHelper, "James");
        int myExp = user.getTotalExperience();

        int currentExpNeeded = Level.getLevelExp(user.getLevel());

        int nextLevelExp = Level.getLevelExp(user.getLevel() + 1);

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
