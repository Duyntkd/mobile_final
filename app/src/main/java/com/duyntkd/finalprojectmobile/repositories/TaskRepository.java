package com.duyntkd.finalprojectmobile.repositories;

import com.duyntkd.finalprojectmobile.models.TaskForDetail;
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


    public static TaskForDetail getTasksDetail(int taskId) {
        TaskForDetail result = new TaskForDetail(taskId, "Very important task", "Blah blah blah... blahhhhhh!", "No description",0 , "ThanhPC", new Date(), new Date());
        return result;
    }
}
