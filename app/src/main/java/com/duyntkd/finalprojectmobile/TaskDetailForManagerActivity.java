package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailForManagerActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_task_detail_for_manager);
    }
}
