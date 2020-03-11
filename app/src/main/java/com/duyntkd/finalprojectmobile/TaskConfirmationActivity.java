package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.duyntkd.finalprojectmobile.models.tasks.TaskConfirmation;
import com.duyntkd.finalprojectmobile.repositories.TaskRepository;

public class TaskConfirmationActivity extends AppCompatActivity {
    private int taskId;
    private boolean isTaskAccepted;
    private TextView txtScoreLabel;
    private EditText edtScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_task_complete);
        taskId = getIntent().getExtras().getInt(TaskDetailForManagerActivity.TASK_ID_STRING);
        TextView txtTaskId = findViewById(R.id.txtTaskId);
        txtTaskId.setText(taskId + "");


        isTaskAccepted = checkConfirmStatus();
        if(isTaskAccepted) {
            txtScoreLabel = findViewById(R.id.txtScoreLabel);
            edtScore = findViewById(R.id.edtScore);
            txtScoreLabel.setVisibility(View.VISIBLE);
            edtScore.setVisibility(View.VISIBLE);
        }


    }

    private boolean checkConfirmStatus () {
        String confirmationStatus = getIntent()
                .getExtras().getString(TaskDetailForManagerActivity.CONFIRM_STATUS);
        switch (confirmationStatus) {
            case TaskDetailForManagerActivity.ACCEPT_STRING:
                return true;
            case TaskDetailForManagerActivity.DENY_STRING:
                return false;
        }
        return false;
     }

    public void clickToUploadImage(View view) {

    }

    public void clickToConfrimComplete(View view) {
        if(isTaskAccepted) {
            TaskRepository.completeATask(new TaskConfirmation());
        } else {
            TaskRepository.completeATask(null);
        }
    }
}
