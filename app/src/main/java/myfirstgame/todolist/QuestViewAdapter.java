package myfirstgame.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestViewAdapter extends ArrayAdapter<Quest> {

    public QuestViewAdapter(Context context, ArrayList<Quest> quests){
        super(context, 0, quests);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.quest_item, parent, false);
        }
        Quest currentQuest = getItem(position);

        TextView itemName = listItemView.findViewById(R.id.itemName);
        itemName.setText(currentQuest.getName().toString());

        Button expValue = listItemView.findViewById(R.id.expValue);
        expValue.setText(currentQuest.getExpValue().toString());

        listItemView.setTag(currentQuest);

        return listItemView;
    }


}
