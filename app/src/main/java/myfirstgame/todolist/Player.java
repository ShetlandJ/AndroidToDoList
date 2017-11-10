package myfirstgame.todolist;

import java.text.DecimalFormat;

/**
 * Created by James on 09/11/2017.
 */

public class Player {

    private String name;
    private int strengthExp;
    private int staminaExp;
    private int intelligenceExp;
    private int socialExp;
    private int id;
    private double level;

    public Player(String name, int strength, int stamina, int intelligence, int social, double level) {
        this.name = name;
        this.strengthExp = strength;
        this.staminaExp = stamina;
        this.intelligenceExp = intelligence;
        this.socialExp = social;
        this.level = level;
    }

    public Player(int id, String name, int strength, int stamina, int intelligence, int social, double level) {
        this.id = id;
        this.name = name;
        this.strengthExp = strength;
        this.staminaExp = stamina;
        this.intelligenceExp = intelligence;
        this.socialExp = social;
        this.level = level;
    }

    protected String getName() {
        return name;
    }

    private int getStrength() {
        return strengthExp;
    }

    private int getStamina() {
        return staminaExp;
    }

    private int getIntelligence() {
        return intelligenceExp;
    }

    private int getSocial() {
        return socialExp;
    }

    public double getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(int strength) {
        this.strengthExp = strength;
    }

    public void setStamina(int stamina) {
        this.staminaExp = stamina;
    }

    public void setIntelligence(int intelligence) {
        this.intelligenceExp = intelligence;
    }

    public void setSocial(int social) {
        this.socialExp = social;
    }

    public int getTotalExperience() {
        return getStrength() + getIntelligence() + getSocial() + getStamina();
    }

    public void setLevel(){
        double totalExperience = getTotalExperience();
        double levelCalc = (totalExperience / 1500);
        levelCalc = Math.floor(levelCalc);
        this.level = levelCalc;
    }

}
