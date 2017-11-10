package myfirstgame.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by James on 09/11/2017.
 */

public class Quest {

    private String name;
    private String description;
    private Integer expValue;
    private String category;
    private boolean isCompleted;
    private String date;
    private int id;

    public Quest(String name, String description, Integer expValue, String category, String date) {
        this.name = name;
        this.description = description;
        this.expValue = expValue;
        this.category = category;
        this.isCompleted = false;
        this.date = date;
    }

    public Quest(int id, String name, String description, Integer expValue, String category, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expValue = expValue;
        this.category = category;
        this.isCompleted = false;
        this.date = date;
    }

    //    Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getExpValue() {
        return expValue;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getDate() {
        return date;
    }

    //    Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpValue(Integer expValue) {
        this.expValue = expValue;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
