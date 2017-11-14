package myfirstgame.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuestItemActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    TextView expValue;
    TextView category;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_single_item);

        Bundle extras = getIntent().getExtras();
        String questName = extras.getString("name");
        String questDescription = extras.getString("description");
        String questExpValue = extras.getString("expValue");
        String questCategory = extras.getString("category");
        String questDate = extras.getString("date");
        name = findViewById(R.id.nameViews);
        name.setText(questName);

        description = findViewById(R.id.descriptionView);
        description.setText(questDescription);

        expValue = findViewById(R.id.expView);
        expValue.setText(questExpValue);

        category = findViewById(R.id.categoryView);
        category.setText("This is a feat of: " + questCategory);

        date = findViewById(R.id.dateView);
        date.setText(questDate);

    }
}
