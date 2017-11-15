package myfirstgame.todolist;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestViewAdapter extends ArrayAdapter<Quest> {

    Player player;
    MediaPlayer mp;

    public QuestViewAdapter(Context context, ArrayList<Quest> quests) {
        super(context, 0, quests);
    }

    public View getView(final int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.quest_item, parent, false);
        }
        Quest currentQuest = getItem(position);

        MyTextView myTextView = listItemView.findViewById(R.id.itemName);
        myTextView.setText(currentQuest.getName().toString());
        listItemView.setBackgroundResource(R.color.progressBarTransparent50);

        Button expValue = listItemView.findViewById(R.id.expValue);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "americanTypwriterRegular.ttf");
        expValue.setTypeface(font);

        expValue.setText(currentQuest.getExpValue().toString() + "exp");

        final DBHelper dbHelper = new DBHelper(this.getContext());

        mp = null;

        player = Player.load(dbHelper, "James");

        listItemView.setTag(currentQuest);

        final View finalListItemView = listItemView;
        expValue.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                Quest currentQuest = getItem(position);
                removeListItem(finalListItemView, currentQuest);
                longclick(dbHelper, currentQuest, player);
                notifyDataSetChanged();
                return true;
            }
        });

        return listItemView;

    }

    protected void removeListItem(final View rowView, final Quest position) {
        final Animation animation = AnimationUtils.loadAnimation(

                this.getContext(), R.anim.fadeout);
                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {

            public void run() {
                rowView.startAnimation(animation);
                remove(position);
                notifyDataSetChanged();
            }
        }, 0);

    }

    public void longclick(DBHelper dbHelper, Quest quest, Player player) {
        quest.setCompleted(1);
        quest.update(dbHelper);
        switch (quest.getCategory()) {
            case 1:
                player.setStrength(quest.getExpValue());
                Category strength = Category.load(dbHelper, "Strength");
                strength.setExp(quest.getExpValue());
                strength.setLevel();
                strength.update(dbHelper);
                break;
            case 2:
                player.setStamina(quest.getExpValue());
                Category stamina = Category.load(dbHelper, "Stamina");
                stamina.setExp(quest.getExpValue());
                stamina.setLevel();
                stamina.update(dbHelper);
                break;
            case 3:
                player.setIntelligence(quest.getExpValue());
                Category intelligence = Category.load(dbHelper, "Intelligence");
                intelligence.setExp(quest.getExpValue());
                intelligence.setLevel();
                intelligence.update(dbHelper);
                break;
            case 4:
                player.setSocial(quest.getExpValue());
                Category social = Category.load(dbHelper, "Social");
                social.setExp(quest.getExpValue());
                social.setLevel();
                social.update(dbHelper);
                break;
        }

        if (hasLevelIncreased()) {
            MediaPlayer levelUp = MediaPlayer.create(this.getContext(), R.raw.fanfare);
            levelUp.start();
        }
        MediaPlayer mp = MediaPlayer.create(this.getContext(), R.raw.complete);
        mp.start();

        player.update(dbHelper);
        player.setLevel();
        player.update(dbHelper);

        Context context = this.getContext();
        CharSequence text = "Quest beaten!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean hasLevelIncreased() {
        Integer currentLevel = player.getLevel();
        Integer newLevel = player.setLevel();
        if (newLevel > currentLevel) {
            return true;
        }
        return false;
    }
}
