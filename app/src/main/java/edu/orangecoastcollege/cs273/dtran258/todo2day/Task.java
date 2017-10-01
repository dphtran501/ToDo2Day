package edu.orangecoastcollege.cs273.dtran258.todo2day;

/**
 * Represents a task on a task list.
 *
 * @author Derek Tran
 * @version 1.0
 * @since September 28, 2017
 */

public class Task
{
    private int mId;
    private String mDescription;
    private boolean mIsDone;

    /**
     * Creates a new Task with specified id, description, and completion check.
     *
     * @param id          The id of this Task.
     * @param description The description of this Task.
     * @param isDone      Truth statement of whether this Task is done or not.
     */
    public Task(int id, String description, boolean isDone)
    {
        mId = id;
        mDescription = description;
        mIsDone = isDone;
    }

    /**
     * Creates a new Task with specfied description and completion check. ID is initialized to -1.
     *
     * @param description The description of this Task.
     * @param isDone      Truth statement of whether this Task is done or not.
     */
    public Task(String description, boolean isDone)
    {
        this(-1, description, isDone);
    }

    /**
     * Creates a new Task with ID initialized to -1, description initialized to an empty String, and
     * the completion check initialized to false.
     */
    public Task()
    {
        this(-1, "", false);
    }

    /**
     * Gets the ID of the task.
     *
     * @return The ID of the task.
     */
    public int getId()
    {
        return mId;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription()
    {
        return mDescription;
    }

    /**
     * Gets the truth statement of whether the task is done or not.
     *
     * @return The truth statement of whether the task is done or not.
     */
    public boolean isDone()
    {
        return mIsDone;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task.
     */
    public void setDescription(String description)
    {
        mDescription = description;
    }

    /**
     * Sets whether the task is done or not.
     *
     * @param done The truth statement of whether the task is done or not.
     */
    public void setDone(boolean done)
    {
        mIsDone = done;
    }

    /**
     * Converts the Task to a String.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString()
    {
        return "Task{" + "id=" + mId + ", description='" + mDescription + '\'' + ", isDone=" + mIsDone + '}';
    }
}
