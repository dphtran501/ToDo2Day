package edu.orangecoastcollege.cs273.dtran258.todo2day;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

/**
 * An Adapter class that provides views for each Task object in the ListView.
 *
 * @author Derek Tran
 * @version 1.0
 * @since October 3, 2017
 */

public class TaskListAdapter extends ArrayAdapter<Task>
{
    private Context mContext;
    private int mResourceID;
    private List<Task> mTaskList;

    /**
     * Creates a new TaskListAdapter object.
     *
     * @param context  The activity that uses this adapter.
     * @param resource The layout file to inflate.
     * @param objects  The list of Task objects.
     */
    public TaskListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Task> objects)
    {
        super(context, resource, objects);
        mContext = context;
        mResourceID = resource;
        mTaskList = objects;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * inflate(int, android.view.ViewGroup, boolean) to specify a root view and to prevent
     * attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible
     *                    to convert this view to display the correct data, this method can create a
     *                    new view. Heterogeneous lists can specify their number of view types, so
     *                    that this View is always of the right type (see getViewTypeCount() and
     *                    getItemViewType(int)).
     * @param parent      The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        // Let's retrieve the selectedTask
        Task selectedTask = mTaskList.get(position);

        // Used LayoutInflator to inflate the view for this specific task:
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceID, null);

        // Get a reference to the checkbox
        CheckBox selectedCheckBox = (CheckBox) view.findViewById(R.id.isDoneCheckBox);
        selectedCheckBox.setChecked(selectedTask.isDone());
        selectedCheckBox.setText(selectedTask.getDescription());

        // Tag = invisible locker behind each view (store anything there)
        selectedCheckBox.setTag(selectedTask);

        return view;
    }
}
