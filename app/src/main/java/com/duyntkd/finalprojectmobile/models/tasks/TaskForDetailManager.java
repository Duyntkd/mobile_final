package com.duyntkd.finalprojectmobile.models.tasks;

import java.util.Date;

public class TaskForDetailManager {
    private int id;
    private String title;
    private String content;
    private String  solutionDescription;
    private int assigneeId;
    private String assigneeName;
    private String startDate;
    private String endDate;

    public TaskForDetailManager(int id, String title, String content, String solutionDescription, int assigneeId, String assigneeName, String startDate, String endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.solutionDescription = solutionDescription;
        this.assigneeId = assigneeId;
        this.assigneeName = assigneeName;
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

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
