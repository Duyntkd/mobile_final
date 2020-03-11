package com.duyntkd.finalprojectmobile.models.tasks;

import java.io.Serializable;
import java.util.Date;


public class Task implements Serializable {
    private int id;
    private String title;
    private String content;
    private int score;
    private String solutionDescription;
    private String feedback;
    private Date startDate;
    private Date endDate;
    private int assignerId;
    private int assigneeId;
    private String confirmReport;
    private int solutionSource;

    public Task(String title, Date startDate, Date endDate, int assignerId, int assigneeId) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignerId = assignerId;
        this.assigneeId = assigneeId;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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

    public int getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(int assignerId) {
        this.assignerId = assignerId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getConfirmReport() {
        return confirmReport;
    }

    public void setConfirmReport(String confirmReport) {
        this.confirmReport = confirmReport;
    }

    public int getSolutionSource() {
        return solutionSource;
    }

    public void setSolutionSource(int solutionSource) {
        this.solutionSource = solutionSource;
    }
}
