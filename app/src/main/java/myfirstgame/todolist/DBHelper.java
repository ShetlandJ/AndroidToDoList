package myfirstgame.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.sql.SQLException;
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
        db.execSQL("CREATE TABLE " + QUEST_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, description TEXT, expValue INTEGER, category INTEGER, FOREIGN KEY(category) REFERENCES categories(_ID), date TEXT)");

        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Strength', 0, 0 );");
        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Stamina', 0, 0 );");
        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Intelligence', 0, 0 );");
        db.execSQL("INSERT INTO " + CATEGORY_TABLE_NAME + "('name', 'exp', 'level') VALUES ('Social', 0, 0 );");
        db.execSQL("INSERT INTO " + QUEST_TABLE_NAME + "('name', 'description', 'expValue', 'category', 'date') VALUES ('Gym', 'Weightlifting down The Gym', '300', '1', '25/05/2017');");



    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUEST_TABLE_NAME);
        onCreate(db);
    }


//    §§§§§§ TABLE CHECKER §§§§§§
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }


//    create table created_topics_table (CREATED_TOPIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, TOPIC_ID INTEGER,USER_ID INTEGER, FOREIGN KEY(TOPIC_ID) REFERENCES topics_table(_ID),FOREIGN KEY (USER_ID) REFERENCES users_table(_ID))


}
