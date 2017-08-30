package soding.com.taskmanagementsystem.model;

/**
 * Created by PT on 8/31/2017.
 */

public class Task {

    private int taskId;
    private String name;
    private String description;
    private String dateCreated;
    private String dateUpdated;

    public Task(){

    }

    public Task(int taskId, String name, String description, String dateCreated, String dateUpdated) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }


}
