package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetailManager;
import com.duyntkd.finalprojectmobile.repositories.TaskRepository;

public class TaskDetailForManagerActivity extends AppCompatActivity {
    public static final String ACCEPT_STRING = "Accepted task";
    public static final String DENY_STRING = "Denied task";
    public static final String CONFIRM_STATUS = "Confirm status";
    public static final String TASK_ID_STRING = "task_Id";


    private TextView txtId;
    private TextView txtTitle;
    private TextView txtContent;
    private TextView txtSolutionDescription;
    private TextView txtAssigneeId;
    private TextView txtAssigneeName;
    private TextView txtStartDate;
    private TextView txtEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_for_manager);

        int taskId = Integer.parseInt(getIntent().getExtras().getString(TASK_ID_STRING));

        txtId = findViewById(R.id.txtId);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        txtSolutionDescription = findViewById(R.id.txtSolutionDescription);
        txtAssigneeId = findViewById(R.id.txtAssigneeId);
        txtAssigneeName = findViewById(R.id.txtAssignee);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtStartDate = findViewById(R.id.txtStartDate);

        loadData(taskId);

    }

    private void loadData(int taskId) {
        TaskForDetailManager taskForDetail =  TaskRepository.getTasksDetailManager(taskId);
        txtId.setText(taskForDetail.getId() + "");
        txtTitle.setText(taskForDetail.getTitle());
        txtContent.setText(taskForDetail.getContent());
        txtSolutionDescription.setText(taskForDetail.getSolutionDescription());
        txtAssigneeId.setText(taskForDetail.getAssigneeId() + "");
        txtAssigneeName.setText(taskForDetail.getAssigneeName());
        txtStartDate.setText(taskForDetail.getStartDate().toString());
        txtEndDate.setText(taskForDetail.getEndDate().toString());
    }

    public void clickToAccepTask(View view) {
        Intent intent = new Intent(this, TaskConfirmationActivity.class);
        intent.putExtra(CONFIRM_STATUS, ACCEPT_STRING);
        int id = Integer.parseInt(txtId.getText().toString());
        intent.putExtra(TASK_ID_STRING, id);
        startActivity(intent);
    }

    public void clickToDenyTask(View view) {
        Intent intent = new Intent(this, TaskConfirmationActivity.class);
        intent.putExtra(CONFIRM_STATUS, DENY_STRING);
        int id = Integer.parseInt(txtId.getText().toString());
        intent.putExtra(TASK_ID_STRING, id);
        startActivity(intent);
    }

    public void clickToApproveTask(View view) {
    }

    public void clickToAssignToNewOne(View view) {
        Intent intent = new Intent(this, TaskAssignmentActivity.class);
        startActivity(intent);
    }
}
