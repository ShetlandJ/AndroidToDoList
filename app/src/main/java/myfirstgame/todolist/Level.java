package myfirstgame.todolist;


/**
 * Created by James on 13/11/2017.
 */

public class Level {

    private static final int LEVEL_BASE = 1500;
    private static final int CATEGORY_LEVEL_BASE = 1000;

    public static int getLevelExp(int level) {
        if (level == 1) {
            return 0;
        } else
            return (level - 1) * LEVEL_BASE + (level - 2) * (level - 1) * 100 / 2;
    }

    public static int getCategoryLevelExp(int level) {
        if (level == 1) {
            return 0;
        } else return (level - 1) * CATEGORY_LEVEL_BASE + (level - 2) * (level - 1) * 100 / 2;
    }
}
