package soding.com.taskmanagementsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import soding.com.taskmanagementsystem.R;
import soding.com.taskmanagementsystem.adapter.TaskListAdapter;
import soding.com.taskmanagementsystem.database.DatabaseHandler;
import soding.com.taskmanagementsystem.model.Task;

/**
 * Created by PT on 8/31/2017.
 */

public class TaskListFragment extends Fragment{

    private TaskListAdapter adapter ;
    private RecyclerView recyclerView;
    private DatabaseHandler databaseHandler ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        databaseHandler = new DatabaseHandler(getContext());
        recyclerView = (RecyclerView)view.findViewById(R.id.task_list);
        loadRecylerView(databaseHandler.getAllTask());

        return  view ;
    }

    private void loadRecylerView(ArrayList<Task> allTask) {
        adapter = new TaskListAdapter(getContext(), allTask);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }


}
