package myfirstgame.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by James on 09/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test2.db";
    public static final String QUEST_TABLE_NAME = "quests";
    public static final String QUEST_COLUMN_ID = "id";
    public static final String QUEST_COLUMN_NAME = "name";
    public static final String QUEST_COLUMN_DESC = "description";
    public static final String QUEST_COLUMN_EXP = "expValue";
    public static final String QUEST_COLUMN_CATEGORY = "category";
    public static final String QUEST_COLUMN_DATE = "date";

    private HashMap hp;


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + QUEST_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, description TEXT, expValue INTEGER, category TEXT, date TEXT )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + QUEST_TABLE_NAME);
        onCreate(db);
    }

    public boolean save(String name, String description, Integer expValue, String category, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUEST_COLUMN_NAME, name);
        contentValues.put(QUEST_COLUMN_DESC, description);
        contentValues.put(QUEST_COLUMN_EXP, expValue);
        contentValues.put(QUEST_COLUMN_CATEGORY, category);
        contentValues.put(QUEST_COLUMN_DATE, date);

        db.insert(QUEST_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Quest> all(){
        ArrayList<Quest> quests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QUEST_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DESC));
            Integer expValue = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_EXP));
            String category = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DATE));


            Quest quest = new Quest(id, name, description, expValue, category, date);
            quests.add(quest);
        }
        cursor.close();
        return quests;
    }

    public void delete(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(QUEST_TABLE_NAME, selection, values);

    }



}
