package soding.com.taskmanagementsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import soding.com.taskmanagementsystem.model.Task;

/**
 * Created by PT on 8/31/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //Database
    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RealEstateDatabase";

    private static final String TABLE_TASK = "task";

    private static final String COLUMN_TASK_ID= "task_id";
    private static final String COLUMN_TASK_NAME = "task_name";
    private static final String COLUMN_TASK_DESCRIPTION = "task_description";
    private static final String COLUMN_TASK_DATE_CREATE = "date_created";
    private static final String COLUMN_TASK_DATE_UPDATED = "date_updated";

    private static final String CREATE_TABLE_TASK= "CREATE TABLE " + TABLE_TASK + " ("
            + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + COLUMN_TASK_NAME + " TEXT,"
            + COLUMN_TASK_DESCRIPTION + " TEXT,"
            + COLUMN_TASK_DATE_CREATE + " TEXT ,"
            + COLUMN_TASK_DATE_UPDATED + " TEXT"
            + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
    }

    public long insertTask(Task task) {
        long a = 0 ;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TASK_NAME, task.getName());
            values.put(COLUMN_TASK_DESCRIPTION, task.getDescription());
            values.put(COLUMN_TASK_DATE_CREATE, task.getDateCreated());
            values.put(COLUMN_TASK_DATE_UPDATED, task.getDateUpdated());
            a=  db.insert(TABLE_TASK, null, values);
        } catch (SQLiteException ex) {
            Log.d("Company Insert:", ex.toString());
        }
        return  a ;
    }

    public int updateTask(Task task) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task.getDescription());
        values.put(COLUMN_TASK_DATE_UPDATED, task.getDateUpdated());
        Log.e("Updated Task ID  ", String.valueOf(task.getTaskId()));
        return db.update(TABLE_TASK, values, COLUMN_TASK_ID + " = ?",
                new String[]{String.valueOf(task.getTaskId())});

    }

    public ArrayList<Task> getAllTask() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setTaskId(cursor.getInt(cursor.getColumnIndex(COLUMN_TASK_ID)));
                task.setName(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_NAME)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_DESCRIPTION)));
                task.setDateCreated(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_DATE_CREATE)));
                task.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_DATE_UPDATED)));
                taskArrayList.add(task);
            }
            while (cursor.moveToNext());
        }
        return taskArrayList;

    }




    public int deleteTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int a = db.delete(TABLE_TASK, COLUMN_TASK_ID + " = ?",
                new String[]{String.valueOf(taskId)});
        Log.e("DELECTED ", String.valueOf(taskId));
        db.close();
        return a ;
    }


}
