package com.duyntkd.finalprojectmobile.models;


import java.io.Serializable;
import java.util.Date;

public class TaskForDetail implements Serializable {
    private int id;
    private String title;
    private String content;
    private String  solutionDescription;
    private int assignerId;
    private String assignerName;
    private Date startDate;
    private Date endDate;

    public TaskForDetail(int id, String title, String content, String solutionDescription, int assignerId, String assignerName, Date startDate, Date endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.solutionDescription = solutionDescription;
        this.assignerId = assignerId;
        this.assignerName = assignerName;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public int getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(int assignerId) {
        this.assignerId = assignerId;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
