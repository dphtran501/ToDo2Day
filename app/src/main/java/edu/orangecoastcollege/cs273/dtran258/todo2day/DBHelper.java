package edu.orangecoastcollege.cs273.dtran258.todo2day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeannie on 9/28/2017.
 */

class DBHelper extends SQLiteOpenHelper
{
    // Create some useful database constants
    public static final String DATABASE_NAME = "ToDo2Day";
    public static final String DATABASE_TABLE = "Tasks";
    public static final int DATABASE_VERSION = 1;

    // Create some useful table constants
    public static final String KEY_FIELD_ID = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_DONE = "done";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Generate an SQL statement for creating a new table
        // CREATE TABLE Tasks (_id INTEGER PRIMARY KEY, description TEXT, done INTEGER)
        String createTable = "CREATE TABLE " + DATABASE_TABLE
                + "(" + KEY_FIELD_ID + " INTEGER PRIMARY KEY,"
                + FIELD_DESCRIPTION + " TEXT,"
                + FIELD_DONE + " INTEGER" +
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // 1) Drop the existing table
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // 2) Build (create) the new one
        onCreate(db);
    }
}
