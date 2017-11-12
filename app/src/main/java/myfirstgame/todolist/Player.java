package myfirstgame.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_ID;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_INTELLIGENCE_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_NAME;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_STAMINA_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_STRENGTH_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_SOCIAL_EXP;
import static myfirstgame.todolist.DBHelper.PROFILE_COLUMN_LEVEL;
import static myfirstgame.todolist.DBHelper.PROFILE_TABLE_NAME;


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

    public Player(String name, int strengthExp, int staminaExp, int intelligenceExp, int socialExp, double level) {
        this.name = name;
        this.strengthExp = strengthExp;
        this.staminaExp = staminaExp;
        this.intelligenceExp = intelligenceExp;
        this.socialExp = socialExp;
        this.level = level;
    }

    public Player(int id,String name, int strengthExp, int staminaExp, int intelligenceExp, int socialExp, double level) {
        this.id = id;
        this.name = name;
        this.strengthExp = strengthExp;
        this.staminaExp = staminaExp;
        this.intelligenceExp = intelligenceExp;
        this.socialExp = socialExp;
        this.id = id;
        this.level = level;
    }

    public static Player load(DBHelper dbHelper, String name){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String where = " WHERE name=?";
        String[] whereArgs = new String[] {String.valueOf(name)};

        Cursor cursor = db.rawQuery("SELECT * FROM " + PROFILE_TABLE_NAME + where, whereArgs);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_ID));
            String userName = cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_NAME));
            Integer strength = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_STRENGTH_EXP));
            Integer stamina = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_STAMINA_EXP));
            Integer intelligence = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_INTELLIGENCE_EXP));
            Integer social = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_SOCIAL_EXP));
            Double level = cursor.getDouble(cursor.getColumnIndex(PROFILE_COLUMN_LEVEL));

            cursor.close();
            Player player = new Player(id, userName, strength, stamina, intelligence, social, level);
            return player;
        }
        return null;
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
        this.strengthExp += strength;
    }

    public void setStamina(int stamina) {
        this.staminaExp += stamina;
    }

    public void setIntelligence(int intelligence) {
        this.intelligenceExp += intelligence;
    }

    public void setSocial(int social) {
        this.socialExp += social;
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

    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_NAME, name);
        contentValues.put(PROFILE_COLUMN_STRENGTH_EXP, strengthExp);
        contentValues.put(PROFILE_COLUMN_STAMINA_EXP, staminaExp);
        contentValues.put(PROFILE_COLUMN_INTELLIGENCE_EXP, intelligenceExp);
        contentValues.put(PROFILE_COLUMN_SOCIAL_EXP, socialExp);
        contentValues.put(PROFILE_COLUMN_LEVEL, level);
        db.insert(PROFILE_TABLE_NAME, null, contentValues);
        return true;
    }

    public static ArrayList<Player> all(DBHelper dbHelper){
        ArrayList<Player> players = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PROFILE_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_NAME));
            Integer strength = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_STRENGTH_EXP));
            Integer stamina = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_STAMINA_EXP));
            Integer intelligence = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_INTELLIGENCE_EXP));
            Integer social = cursor.getInt(cursor.getColumnIndex(PROFILE_COLUMN_SOCIAL_EXP));
            Double level = cursor.getDouble(cursor.getColumnIndex(PROFILE_COLUMN_LEVEL));

            Player player = new Player(id, name, strength, stamina, intelligence, social, level);
            players.add(player);
        }
        cursor.close();
        return players;
    }

    public static boolean deleteAll(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + PROFILE_TABLE_NAME);
        return true;
    }

    public static boolean delete(DBHelper dbHelper, Integer id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(PROFILE_TABLE_NAME, selection, values);
        return true;
    }

    public void update(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_NAME, name);
        contentValues.put(PROFILE_COLUMN_STRENGTH_EXP, strengthExp);
        contentValues.put(PROFILE_COLUMN_STAMINA_EXP, staminaExp);
        contentValues.put(PROFILE_COLUMN_INTELLIGENCE_EXP, intelligenceExp);
        contentValues.put(PROFILE_COLUMN_SOCIAL_EXP, socialExp);
        contentValues.put(PROFILE_COLUMN_LEVEL, level);

        String where = "name=?";
        String[] whereArgs = new String[] {String.valueOf(name)};

        db.update(PROFILE_TABLE_NAME, contentValues, where, whereArgs);
        db.close();
    }
}
