package myfirstgame.todolist;

/**
 * Created by James on 09/11/2017.
 */

public enum Category {
    STRENGTH("Strength"),
    STAMINA("Stamina"),
    INTELLIGENCE("Intelligence"),
    SOCIAL("Social");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
