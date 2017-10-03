package edu.orangecoastcollege.cs273.dtran258.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the methods in <code>DBHelper</code> by adding, deleting, updating, and retrieving
 * <code>Task</code> objects from a database.
 *
 * @author Derek Tran
 * @version 1.0
 * @since September 28, 2017
 */
public class MainActivity extends AppCompatActivity
{
    /*
    // stores "MainActivity"
    public static final String TAG = MainActivity.class.getSimpleName();
    */

    // Reference to the database:
    private DBHelper mDB;
    // References to the widgets needed
    private EditText mDescriptionEditText;
    private ListView mTaskListView;
    // Reference to the list of all tasks
    private List<Task> mAllTasksList = new ArrayList<>();
    // Reference to the custom list adapter
    TaskListAdapter mTaskListAdapter;

    /**
     * Adds, deletes, updates, and retrieves <code>Task</code> objects from a database, and displays
     * them to the console.
     * @param savedInstanceState Bundle containing the data it recently supplied in
     *                           onSaveInstanceState(Bundle) if activity was reinitialized after
     *                           being previously shut down. Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new DBHelper(this);
        mDescriptionEditText = (EditText) findViewById(R.id.taskEditText);
        mTaskListView = (ListView) findViewById(R.id.taskListView);


        /*
        // Clear the existing database
        deleteDatabase(DBHelper.DATABASE_NAME);

        // Pre-populate the List with 4 tasks
        mAllTasksList.add(new Task("Study for CS 273 Midterm", false));
        mAllTasksList.add(new Task("Sleep", true));
        mAllTasksList.add(new Task("Eat", true));
        mAllTasksList.add(new Task("Do CS 273 Assignment", false));

        // Let's instantiate a new DBHelper
        DBHelper db = new DBHelper(this);

        // Let's loop through the List and add each one to the database:
        for (Task t : mAllTasksList)
        {
            db.addTask(t);
        }

        // Let's clear out the List, then rebuild it from the database this time:
        mAllTasksList.clear();
        // Retrieve all tasks from the database
        mAllTasksList = db.getAllTasks();

        // Loop through each of the Tasks, print them to Log.i
        Log.i(TAG, "Showing all tasks:");
        for (Task t : mAllTasksList)
            Log.i(TAG, t.toString());

        Log.i(TAG, "After deleting tasks 3 and 4");
        db.deleteTask(mAllTasksList.get(2));
        db.deleteTask(mAllTasksList.get(3));
        mAllTasksList = db.getAllTasks();
        for(Task t : mAllTasksList)
            Log.i(TAG, t.toString());

        Log.i(TAG, "After updating task 1");
        mAllTasksList.get(0).setDescription("Study for CS 273 Final");
        mAllTasksList.get(0).setDone(true);
        db.updateTask(mAllTasksList.get(0));
        mAllTasksList = db.getAllTasks();
        for(Task t : mAllTasksList)
            Log.i(TAG, t.toString());

        Log.i(TAG, "Showing task 2");
        Log.i(TAG, db.getSingleTask(mAllTasksList.get(1).getId()).toString());
        */
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // Database related "stuff"
        // 1) Populate the list from the database (using DBHelper)
        mAllTasksList = mDB.getAllTasks();
        // 2) Connect the ListView with the custom list adapter
        mTaskListAdapter = new TaskListAdapter(this, R.layout.task_item, mAllTasksList);
        mTaskListView.setAdapter(mTaskListAdapter);
    }

    public void addTask(View v)
    {
        // Check to see if the description is empty or null
        String description = mDescriptionEditText.getText().toString();
        if (TextUtils.isEmpty(description))
            Toast.makeText(this, "Please enter a description.", Toast.LENGTH_LONG).show();
        else
        {
            // Create Task
            Task newTask = new Task(description, false);
            // Add it to the database;
            mDB.addTask(newTask);
            // Add it to the list
            mAllTasksList.add(newTask);
            // Notify the list adapter that it's been changed
            mTaskListAdapter.notifyDataSetChanged();
            // Clear out the EditText
            mDescriptionEditText.setText("");
        }
    }

    public void clearAllTasks(View v)
    {
        mDB.deleteAllTasks();
        mAllTasksList.clear();
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void changeTaskStatus(View v)
    {
        CheckBox selectedCheckBox = (CheckBox) v;
        Task selectedTask = (Task) selectedCheckBox.getTag();
        // Update the task
        selectedTask.setDone(selectedCheckBox.isChecked());
        // Update the database
        mDB.updateTask(selectedTask);
    }
}
