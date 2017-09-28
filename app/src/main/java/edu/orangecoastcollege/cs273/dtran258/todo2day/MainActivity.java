package edu.orangecoastcollege.cs273.dtran258.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private List<Task> mAllTasksList = new ArrayList<>();

    // stores "MainActivity"
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        for (Task t : mAllTasksList)
            Log.i(TAG, t.toString());
    }
}
