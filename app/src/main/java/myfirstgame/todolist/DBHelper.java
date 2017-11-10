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

    public static final String DATABASE_NAME = "test3.db";

    //    Category table

    public static final String CATEGORY_TABLE_NAME = "categories";
    public static final String CATEGORY_COLUMN_ID = "id";
    public static final String CATEGORY_COLUMN_NAME = "name";
    public static final String CATEGORY_COLUMN_EXP = "exp";
    public static final String CATEGORY_COLUMN_LEVEL = "level";

    //    Quest table
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
        db.execSQL("CREATE TABLE " + CATEGORY_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, exp INTEGER, level INTEGER )");
        db.execSQL("CREATE TABLE " + QUEST_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, description TEXT, expValue INTEGER, category INTERGER, FOREIGN KEY(category) REFERENCES categories(_ID) )");

        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Strength', 0, 0 )");
        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Stamina', 0, 0 )");
        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Intelligence', 0, 0 )");
        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Social', 0, 0 )");


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUEST_TABLE_NAME);
        onCreate(db);
    }

    public void seeds(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_COLUMN_NAME, "Strength");
        contentValues.put(CATEGORY_COLUMN_EXP, 0);
        contentValues.put(CATEGORY_COLUMN_LEVEL, 0);
        db.insert(CATEGORY_TABLE_NAME, null, contentValues);
    }


//    create table created_topics_table (CREATED_TOPIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, TOPIC_ID INTEGER,USER_ID INTEGER, FOREIGN KEY(TOPIC_ID) REFERENCES topics_table(_ID),FOREIGN KEY (USER_ID) REFERENCES users_table(_ID))


}
