package myfirstgame.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestViewAdapter extends ArrayAdapter<Quest> {

    Button expValue;
    Quest quest;
    Player player;

    public QuestViewAdapter(Context context, ArrayList<Quest> quests){
        super(context, 0, quests);
    }

    public View getView(final int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.quest_item, parent, false);
        }
        final Quest currentQuest = getItem(position);

        TextView itemName = listItemView.findViewById(R.id.itemName);
        itemName.setText(currentQuest.getName().toString());

        final Button expValue = listItemView.findViewById(R.id.expValue);
        expValue.setText(currentQuest.getExpValue().toString());

        final DBHelper dbHelper = new DBHelper(this.getContext());


        expValue.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(dbHelper, currentQuest, player);
                return true;
            }
        });

        listItemView.setTag(currentQuest);

        return listItemView;

    }

    public void longclick(DBHelper dbHelper, Quest quest, Player player)
    {
        quest.setCompleted(1);
        quest.update(dbHelper);
        switch (quest.getCategory()) {
            case 1:
                player.setStrength(quest.getExpValue());
                player.update(dbHelper);
                break;
            case 2:
                player.setStamina(quest.getExpValue());
                player.update(dbHelper);
                break;
            case 3:
                player.setIntelligence(quest.getExpValue());
                player.update(dbHelper);
                break;
            case 4:
                player.setSocial(quest.getExpValue());
                player.update(dbHelper);
                break;
        }
        remove(quest);
        notifyDataSetChanged();

    }

}
