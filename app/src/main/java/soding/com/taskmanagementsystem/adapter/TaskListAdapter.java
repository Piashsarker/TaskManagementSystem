package soding.com.taskmanagementsystem.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import soding.com.taskmanagementsystem.R;
import soding.com.taskmanagementsystem.activity.MainActivity;
import soding.com.taskmanagementsystem.database.DatabaseHandler;
import soding.com.taskmanagementsystem.fragment.InsertTaskFragment;
import soding.com.taskmanagementsystem.model.Task;

/**
 * Created by PT on 8/31/2017.
 */

public class TaskListAdapter  extends RecyclerView.Adapter<TaskListAdapter.MyViewHolder> {

    private ArrayList<Task> taskArrayList;
    private Context context;
    private DatabaseHandler databaseHandler ;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description, dateCreated, dateUpdated;
        private LinearLayout row;


        public MyViewHolder(View view) {
            super(view);
            row = (LinearLayout) view.findViewById(R.id.row);
            name = (TextView) view.findViewById(R.id.txt_task_name);
            description = (TextView) view.findViewById(R.id.txt_description);
            dateCreated = (TextView) view.findViewById(R.id.txt_date_created);
            dateUpdated = (TextView) view.findViewById(R.id.txt_date_updated);


        }
    }


    public TaskListAdapter(Context context, ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        this.context = context;
        databaseHandler  = new DatabaseHandler(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_task, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.name.setText(taskArrayList.get(position).getName());
        holder.description.setText(taskArrayList.get(position).getDescription());
        holder.dateUpdated.setText(taskArrayList.get(position).getDateUpdated());
        holder.dateCreated.setText(taskArrayList.get(position).getDateCreated());

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public void deleteTask(int taskId , int position ){
        databaseHandler.deleteTask(taskId);
        taskArrayList.remove(position);
        notifyDataSetChanged();
    }


    public void showDialog(final int position){
        new AlertDialog.Builder(context)
                .setTitle("Choose action ")
                .setMessage("Choose Update Or Delete? ")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       fragmentTransaction(position);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        deleteTask(taskArrayList.get(position).getTaskId() , position);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void fragmentTransaction(int position) {

        InsertTaskFragment insertTaskFragment = new InsertTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(InsertTaskFragment.KEY_ID , taskArrayList.get(position).getTaskId());
        bundle.putString(InsertTaskFragment.KEY_Name , taskArrayList.get(position).getName());
        bundle.putString(InsertTaskFragment.KEY_DESCRIPTION, taskArrayList.get(position).getDescription());
        insertTaskFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction=((MainActivity)context).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.main_frame,insertTaskFragment);
            fragmentTransaction.commit();

    }
}