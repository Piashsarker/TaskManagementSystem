# TaskManagementSystem
Task Create , Task Edit , Task Delete , All Task.For update and Edit Action click on Task Row.

A Simple application in andorid. In this application you can add a Task , edit a task and delete a task . You can see a list of Task also. 
This app is a simple database application. 

Database Methods are : 

 1. For inserting a Task object insertTask() methods with task  properties of name , description , dateCreated , dateUpdate . 

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
    
    
    
 2.For Updating A Task of Object : 


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
    
    
    
  3.  For Getting All Task From The Database Table : 

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


4. For Delection a particular task . 

    public int deleteTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int a = db.delete(TABLE_TASK, COLUMN_TASK_ID + " = ?",
                new String[]{String.valueOf(taskId)});
        Log.e("DELECTED ", String.valueOf(taskId));
        db.close();
        return a ;
    }
