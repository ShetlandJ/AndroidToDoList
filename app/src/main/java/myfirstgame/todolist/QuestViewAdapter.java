package myfirstgame.todolist;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_ID;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_INTELLIGENCE_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_LEVEL;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_NAME;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_SOCIAL_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_STAMINA_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_STRENGTH_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_TABLE_NAME;

public class QuestViewAdapter extends ArrayAdapter<Quest> {

    Player player;
    Category category;
    MediaPlayer mp;

    public QuestViewAdapter(Context context, ArrayList<Quest> quests){
        super(context, 0, quests);
    }

    public View getView(final int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.quest_item, parent, false);
        }
        final Quest currentQuest = getItem(position);

        TextView itemName = listItemView.findViewById(R.id.itemName);
        itemName.setText(currentQuest.getName().toString());
        listItemView.setBackgroundResource(R.color.progressBarTransparent50);


        Button expValue = listItemView.findViewById(R.id.expValue);
        expValue.setText(currentQuest.getExpValue().toString());

        final DBHelper dbHelper = new DBHelper(this.getContext());

        mp = null;

        player = Player.load(dbHelper, "James");

        listItemView.setTag(currentQuest);



        category = Category.load(dbHelper, currentQuest.showCategoryNameByNumber(currentQuest.getCategory()));


        final View finalListItemView = listItemView;
        expValue.setOnLongClickListener(new View.OnLongClickListener() {

                public boolean onLongClick(View v) {
                    removeListItem(finalListItemView, currentQuest);
                    longclick(dbHelper, currentQuest, player, category);
                    notifyDataSetChanged();
                    return true;
                }
            });

            return listItemView;



    }

    protected void removeListItem(final View rowView, final Quest position) {
        final Animation animation = AnimationUtils.loadAnimation(

        this.getContext(), R.anim.fadeout);
//        rowView.startAnimation(animation);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {

            public void run() {
                rowView.startAnimation(animation);
                remove(position);
                notifyDataSetChanged();
            }
        }, 0);

    }

    public void longclick(DBHelper dbHelper, Quest quest, Player player, Category category) {
        quest.setCompleted(1);
        quest.update(dbHelper);
        switch (quest.getCategory()) {
            case 1:
                player.setStrength(quest.getExpValue());
                player.update(dbHelper);
                category.setExp(quest.getExpValue());
                category.setLevel();
                category.update(dbHelper);
                break;
            case 2:
                player.setStamina(quest.getExpValue());
                player.update(dbHelper);
                category.setExp(quest.getExpValue());
                category.update(dbHelper);
                break;
            case 3:
                player.setIntelligence(quest.getExpValue());
                player.update(dbHelper);
                category.setExp(quest.getExpValue());
                category.update(dbHelper);
                break;
            case 4:
                player.setSocial(quest.getExpValue());
                player.update(dbHelper);
                category.setExp(quest.getExpValue());
                category.update(dbHelper);
                break;
        }

        if (hasLevelIncreased()) {
            MediaPlayer levelUp = MediaPlayer.create(this.getContext(), R.raw.fanfare);
            levelUp.start();
        }
        MediaPlayer mp = MediaPlayer.create(this.getContext(), R.raw.complete);
        mp.start();

        player.setLevel();
        player.update(dbHelper);
//        remove(quest);
        Context context = this.getContext();
        CharSequence text = "Quest beaten!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean hasLevelIncreased(){
        Integer currentLevel = player.getLevel();
        Integer newLevel = player.setLevel();
        if (newLevel > currentLevel){
            return true;
        }
        return false;
    }


}
