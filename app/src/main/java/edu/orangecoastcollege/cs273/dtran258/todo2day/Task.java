package edu.orangecoastcollege.cs273.dtran258.todo2day;

/**
 * Created by Jeannie on 9/28/2017.
 */

public class Task
{
    private int mId;
    private String mDescription;
    private boolean mIsDone;

    public Task(int id, String description, boolean isDone)
    {
        mId = id;
        mDescription = description;
        mIsDone = isDone;
    }

    public Task(String description, boolean isDone)
    {
        this(-1, description, isDone);
    }

    public Task()
    {
        this(-1, "", false);
    }

    public int getId()
    {
        return mId;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public boolean isDone()
    {
        return mIsDone;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    public void setDone(boolean done)
    {
        mIsDone = done;
    }

    @Override
    public String toString()
    {
        return "Task{" + "id=" + mId + ", description='" + mDescription + '\'' + ", isDone=" + mIsDone + '}';
    }
}
