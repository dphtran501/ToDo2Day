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
