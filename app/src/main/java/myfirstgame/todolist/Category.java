package myfirstgame.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static myfirstgame.todolist.DBHelper.CATEGORY_COLUMN_EXP;
import static myfirstgame.todolist.DBHelper.CATEGORY_COLUMN_ID;
import static myfirstgame.todolist.DBHelper.CATEGORY_COLUMN_LEVEL;
import static myfirstgame.todolist.DBHelper.CATEGORY_COLUMN_NAME;
import static myfirstgame.todolist.DBHelper.CATEGORY_TABLE_NAME;


/**
 * Created by James on 10/11/2017.
 */

public class Category {

    private String name;
    private int id;
    private int exp;
    private int level;

    public Category(String name) {
        this.name = name;
        this.exp = 0;
        this.level = 1;
    }

    public Category(int id, String name, int exp, int level) {
        this.id = id;
        this.name = name;
        this.exp = 0;
        this.level = 1;
    }

    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_COLUMN_NAME, name);
        contentValues.put(CATEGORY_COLUMN_EXP, exp);
        contentValues.put(CATEGORY_COLUMN_LEVEL, level);

        db.insert(CATEGORY_TABLE_NAME, null, contentValues);
        return true;
    }


    public ArrayList<Category> all(DBHelper dbHelper){
        ArrayList<Category> categories = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CATEGORY_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(CATEGORY_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN_NAME));
            Integer expValue = cursor.getInt(cursor.getColumnIndex(CATEGORY_COLUMN_EXP));
            Integer level = cursor.getInt(cursor.getColumnIndex(CATEGORY_COLUMN_LEVEL));

            Category category= new Category(id, name, expValue, level);
            categories.add(category);
        }
        cursor.close();
        return categories;
    }

    public static boolean deleteAll(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + CATEGORY_TABLE_NAME);
        return true;
    }

    public static boolean delete(DBHelper dbHelper, Integer id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(CATEGORY_TABLE_NAME, selection, values);
        return true;
    }

}
