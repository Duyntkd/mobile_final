package com.duyntkd.finalprojectmobile.models;

import java.io.Serializable;

public class TaskInfoforList implements Serializable {
    private int id;
    private String title;
    private String endDate;
    private String assigner;


    public TaskInfoforList(int id, String title, String endDate, String assigner) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.assigner = assigner;
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

    public String getAssigner() {
        return assigner;
    }

    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }


}
