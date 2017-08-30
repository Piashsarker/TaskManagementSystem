package soding.com.taskmanagementsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import soding.com.taskmanagementsystem.R;
import soding.com.taskmanagementsystem.Utlis;
import soding.com.taskmanagementsystem.database.DatabaseHandler;
import soding.com.taskmanagementsystem.model.Task;

/**
 * Created by PT on 8/31/2017.
 */

public class InsertTaskFragment extends Fragment {

    private final String NOT_UPDATED_YET = "not_updated_yet" ;
    public static  final String KEY_ID = "key_id";
    public static final  String KEY_Name = "key_name";
    public static final  String KEY_DESCRIPTION = "key_description";
    private EditText etName , etDescription ;
    private Button  btnAdd ;
    private DatabaseHandler databaseHandler ;
    private boolean isNew = true;
    private int taskId ;
    private String name , description ;
    private TaskListFragment taskListFragment ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_task, container, false);
        databaseHandler = new DatabaseHandler(getContext());
        etName  = view.findViewById(R.id.et_name);
        etDescription = view.findViewById(R.id.et_description);
        taskListFragment  = new TaskListFragment();
        btnAdd  = view.findViewById(R.id.button1);



        if(getArguments()!=null){
            isNew = false ;
            name = getArguments().getString(KEY_Name);
            description = getArguments().getString(KEY_DESCRIPTION);
            taskId = getArguments().getInt(KEY_ID);
            etName.setText(name);
            etDescription.setText(description);

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddOnClick();
            }
        });

        return  view ;
    }

    public void btnAddOnClick(){

        if(!etName.equals("") && !etDescription.equals("")){
            if(isNew){
                insertTask();
            }
            else{
                updateTask();
            }
        }

        else{
            Toast.makeText(getContext(), "Provide name and Description", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateTask() {
        Task task = new Task();
        task.setTaskId(taskId);
        task.setName(etName.getText().toString());
        task.setDescription(etDescription.getText().toString());
        task.setDateUpdated(Utlis.getCurrentDate());
        if(databaseHandler.updateTask(task)>0){
            Toast.makeText(getContext(), "Task Updated Successfull . ", Toast.LENGTH_SHORT).show();
            fragmentTransactionMainFrame(taskListFragment);
        }
        else{
            Toast.makeText(getContext(), "Task Not  Updated", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertTask() {
        Task task = new Task();
        task.setName(etName.getText().toString());
        task.setDescription(etDescription.getText().toString());
        task.setDateUpdated(NOT_UPDATED_YET);
        task.setDateCreated(Utlis.getCurrentDate());
        if(databaseHandler.insertTask(task)>0){
            Toast.makeText(getContext(), "Task inserted . ", Toast.LENGTH_SHORT).show();
            fragmentTransactionMainFrame(taskListFragment);
        }
        else{
            Toast.makeText(getContext(), "Task not inserted", Toast.LENGTH_SHORT).show();
        }


    }


    public void fragmentTransactionMainFrame(Fragment f){
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_frame,f);
        fragmentTransaction.commit();

    }
}
