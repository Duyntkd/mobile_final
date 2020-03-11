package com.duyntkd.finalprojectmobile.repositories;

import com.duyntkd.finalprojectmobile.models.tasks.TaskConfirmation;
import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetail;
import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetailManager;
import com.duyntkd.finalprojectmobile.models.tasks.TaskInfoForGroupList;
import com.duyntkd.finalprojectmobile.models.tasks.TaskInfoforHistoryList;
import com.duyntkd.finalprojectmobile.models.tasks.TaskInfoforList;

import java.util.ArrayList;
import java.util.Date;

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

    public static ArrayList<TaskInfoForGroupList> getGroupTasksForDisplay() {
        ArrayList<TaskInfoForGroupList> fakeTaskList = new ArrayList<>();
        fakeTaskList.add(new TaskInfoForGroupList(0, "No Title", "20/2/2012", "You", "waiting for accept"));
        fakeTaskList.add(new TaskInfoForGroupList(1, "Title1", "20/2/2012", "You", "ongoing"));
        fakeTaskList.add(new TaskInfoForGroupList(2, "Title2", "20/2/2012", "You", "done"));
        fakeTaskList.add(new TaskInfoForGroupList(3, "Title3", "20/2/2012", "You", "waiting for permit"));
        fakeTaskList.add(new TaskInfoForGroupList(4, "Title4", "20/2/2012", "You", "finshed"));
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

    public static TaskForDetailManager getTasksDetailManager(int taskId) {
        TaskForDetailManager result = new TaskForDetailManager(taskId, "Very important task", "Blah blah blah... blahhhhhh!", "No description",0 , "NTKD", new Date(), new Date());
        return result;
    }

    public static boolean completeATask(TaskConfirmation confirmation) {
        if (confirmation != null) return true;
        return false;
    }


}
