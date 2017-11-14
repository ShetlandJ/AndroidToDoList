package myfirstgame.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by James on 11/11/2017.
 */

public class BeatenQuestAdapter extends ArrayAdapter<Quest> {


        public BeatenQuestAdapter(Context context, ArrayList<Quest> quests){
            super(context, 0, quests);
        }

        public View getView(int position, View listItemView, ViewGroup parent){
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.beaten_quest_item, parent, false);
            }
            Quest currentQuest = getItem(position);

            MyTextView itemName = listItemView.findViewById(R.id.beatenQuestName);
            itemName.setText(currentQuest.getName().toString());

            listItemView.setTag(currentQuest);

            return listItemView;
        }


    }
