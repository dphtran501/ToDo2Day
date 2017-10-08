package edu.orangecoastcollege.cs273.dtran258.todo2day;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A model class to manage the SQLite database used to store Task data.
 *
 * @author Derek Tran
 * @version 1.0
 * @since September 28, 2017
 */

class DBHelper extends SQLiteOpenHelper
{
    // Create some useful database constants
    /**
     * Name of the database.
     */
    public static final String DATABASE_NAME = "ToDo2Day";
    /**
     * Name of the database table.
     */
    public static final String DATABASE_TABLE = "Tasks";
    /**
     * Version of the database.
     */
    public static final int DATABASE_VERSION = 1;

    // Create some useful table constants
    /**
     * ID field of the database table. It acts as the primary key.
     */
    public static final String KEY_FIELD_ID = "_id";
    /**
     * Description field of the database table.
     */
    public static final String FIELD_DESCRIPTION = "description";
    /**
     * Completion check field of the database table.
     */
    public static final String FIELD_DONE = "done";

    /**
     * Creates a new DBHelper object with the given context.
     * @param context The activity used to open or create the database.
     */
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database table for the first time.
     * @param db The database.
     */
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

    /**
     * Drops the existing database table and creates a new one when database is upgraded.
     * @param db The database.
     * @param i The old database version.
     * @param i1 The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // 1) Drop the existing table
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // 2) Build (create) the new one
        onCreate(db);
    }

    /**
     * Adds a Task to the database.
     * @param newTask The task to add to the database.
     * @return The row ID of the newly inserted row, or -1 if error occurred.
     */
    public int addTask(Task newTask)
    {
        SQLiteDatabase db = getWritableDatabase();
        // Specify values (fields) to insert into database
        // Everything except primary key _id (auto assigned)
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, newTask.getDescription());
        values.put(FIELD_DONE, newTask.isDone() ? 1 : 0);
        int newID = (int) db.insert(DATABASE_TABLE, null, values);
        db.close();

        return newID;
    }

    /**
     * Gets a list of all Tasks in the database.
     * @return List of all Tasks in the database.
     */
    public List<Task> getAllTasks()
    {
        List<Task> allTasksList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        // To retrieve data from database, use a Cursor
        // Cursor stores results of a query
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[] {KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            // Guaranteed at least one result from query
            do
            {
                Task task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
                allTasksList.add(task);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allTasksList;
    }

    /**
     * Deletes all Tasks from the database.
     */
    public void deleteAllTasks()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.close();
    }

    /**
     * Deletes a Task from the database.
     * @param taskToDelete The Task to delete from the database.
     */
    public void deleteTask(Task taskToDelete)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_FIELD_ID + "=" + taskToDelete.getId(), null);
        db.close();
    }

    /**
     * Updates a Task in the database.
     * @param taskToEdit The new Task to replace the old Task with.
     */
    public void updateTask(Task taskToEdit)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, taskToEdit.getDescription());
        values.put(FIELD_DONE, taskToEdit.isDone() ? 1 : 0);
        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + "=" + taskToEdit.getId(), null);
        db.close();
    }

    /**
     * Gets a Task from the database with the specified ID.
     * @param id The ID of the Task to get from the database.
     * @return The Task with the specified ID.
     */
    public Task getSingleTask(int id)
    {
        Task singleTask = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE,
                new String[] {KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                KEY_FIELD_ID + "=" + id, null, null, null, null);

        if (cursor.moveToFirst())
        {
            // Guaranteed at least one result from query
            singleTask = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
        }
        cursor.close();
        db.close();

        return singleTask;
    }

}
