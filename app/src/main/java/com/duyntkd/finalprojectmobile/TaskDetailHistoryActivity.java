package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.duyntkd.finalprojectmobile.models.TaskForDetail;
import com.duyntkd.finalprojectmobile.repositories.TaskRepository;

public class TaskDetailHistoryActivity extends AppCompatActivity {
    public static final String TASK_ID_STRING = "task_Id";



    private TextView txtId;
    private TextView txtTitle;
    private TextView txtContent;
    private TextView edtSolutionDescription;
    private TextView txtAssignerId;
    private TextView txtAssignerName;
    private TextView txtStartDate;
    private TextView txtEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_history);
        int taskId = Integer.parseInt(getIntent().getExtras().getString(TASK_ID_STRING));

        txtId = findViewById(R.id.txtId);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        edtSolutionDescription = findViewById(R.id.edtSolutionDescription);
        txtAssignerId = findViewById(R.id.txtAssignerId);
        txtAssignerName = findViewById(R.id.txtAssigner);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtStartDate = findViewById(R.id.txtStartDate);

        loadData(taskId);

    }

    private void loadData(int taskId) {
        TaskForDetail taskForDetail =  TaskRepository.getTasksDetail(taskId);
        txtId.setText(taskForDetail.getId() + "");
        txtTitle.setText(taskForDetail.getTitle());
        txtContent.setText(taskForDetail.getContent());
        edtSolutionDescription.setText(taskForDetail.getSolutionDescription());
        txtAssignerId.setText(taskForDetail.getAssignerId() + "");
        txtAssignerName.setText(taskForDetail.getAssignerName());
        txtStartDate.setText(taskForDetail.getStartDate().toString());
        txtEndDate.setText(taskForDetail.getEndDate().toString());
    }

    public void clickToConfirmTaskUndone(View view) {

    }

    public void clickToConfirmTaskDone(View view) {
    }
}
