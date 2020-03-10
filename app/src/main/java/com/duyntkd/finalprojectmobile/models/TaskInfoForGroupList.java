package com.duyntkd.finalprojectmobile.models;

public class TaskInfoForGroupList {
    private int id;
    private String title;
    private String endDate;
    private String assignee;
    private String status;


    public TaskInfoForGroupList(int id, String title, String endDate, String assignee, String status) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.assignee = assignee;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
