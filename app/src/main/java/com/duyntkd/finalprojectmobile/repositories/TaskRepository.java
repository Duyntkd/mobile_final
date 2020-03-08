package com.duyntkd.finalprojectmobile.repositories;

import com.duyntkd.finalprojectmobile.models.TaskForDetail;
import com.duyntkd.finalprojectmobile.models.TaskInfoforHistoryList;
import com.duyntkd.finalprojectmobile.models.TaskInfoforList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskRepository {

    public static ArrayList<TaskInfoforList> getTasksForDisplay() {
        ArrayList<TaskInfoforList> fakeTaskList = new ArrayList<>();
        fakeTaskList.add(new TaskInfoforList(0, "No Title", "20/2/2012", "You"));
        fakeTaskList.add(new TaskInfoforList(1, "Title1", "20/2/2012", "You"));
        fakeTaskList.add(new TaskInfoforList(2, "Title2", "20/2/2012", "You"));
        fakeTaskList.add(new TaskInfoforList(3, "Title3", "20/2/2012", "You"));
        fakeTaskList.add(new TaskInfoforList(4, "Title4", "20/2/2012", "You"));
        return fakeTaskList;
    }

    public static ArrayList<TaskInfoforHistoryList> getTasksHistoryForDisplay() {
        ArrayList<TaskInfoforHistoryList> fakeTaskHistoryList = new ArrayList<>();
        fakeTaskHistoryList.add(new TaskInfoforHistoryList(0, "No Title", "20/2/2012", "You", "ongoing"));
        fakeTaskHistoryList.add(new TaskInfoforHistoryList(1, "Title1", "20/2/2012", "You", "done"));
        fakeTaskHistoryList.add(new TaskInfoforHistoryList(2, "Title2", "20/2/2012", "You", "ongoing"));
        fakeTaskHistoryList.add(new TaskInfoforHistoryList(3, "Title3", "20/2/2012", "You", "undone"));
        fakeTaskHistoryList.add(new TaskInfoforHistoryList(4, "Title4", "20/2/2012", "You", "ongoing"));
        return fakeTaskHistoryList;
    }


    public static TaskForDetail getTasksDetail(int taskId) {
        TaskForDetail result = new TaskForDetail(taskId, "Very important task", "Blah blah blah... blahhhhhh!", "No description",0 , "ThanhPC", new Date(), new Date());
        return result;
    }


}
