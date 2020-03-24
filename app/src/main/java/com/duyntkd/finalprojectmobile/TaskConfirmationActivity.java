package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.models.tasks.TaskConfirmation;
import com.duyntkd.finalprojectmobile.repositories.TaskRepository;
import com.duyntkd.finalprojectmobile.util.ErrorResponseUtil;

import org.json.JSONObject;

public class TaskConfirmationActivity extends AppCompatActivity {
    private int taskId;
    private boolean isTaskAccepted;
    private TextView txtScoreLabel;
    private EditText edtScore;
    private EditText edtFeedback;
    private int score;
    private String feedback;
    private String status;
    private int currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_task_complete);
        taskId = getIntent().getExtras().getInt(TaskDetailForManagerActivity.TASK_ID_STRING);
        currentUserId = getIntent().getExtras().getInt(TaskDetailForManagerActivity.USER_ID_STRING);
        TextView txtTaskId = findViewById(R.id.txtUserId);
        txtTaskId.setText(taskId + "");
        isTaskAccepted = checkConfirmStatus();

        edtFeedback = findViewById(R.id.edtFeedback);
        if (isTaskAccepted) {
            txtScoreLabel = findViewById(R.id.txtScoreLabel);
            edtScore = findViewById(R.id.edtScore);
            txtScoreLabel.setVisibility(View.VISIBLE);
            edtScore.setVisibility(View.VISIBLE);
        }
    }

    private boolean checkConfirmStatus() {
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
        feedback = edtFeedback.getText().toString();
        if (isTaskAccepted){
            score = Integer.parseInt(edtScore.getText().toString());
            status = "accepted";
        }
        else {
            score = 0;
            status = "denied";
        }

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("taskId", taskId);
            jsonBody.put("feedback", feedback);
            jsonBody.put("score", score);
            jsonBody.put("status", status);
            jsonBody.put("approverId", currentUserId);
            String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/complete";
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    requestUrl,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String result = response.getString("status");
                                if (result.equals("success")) {
                                    TaskConfirmationActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(TaskConfirmationActivity.this, "Confirmation failed", Toast.LENGTH_SHORT);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            ErrorResponseUtil.displayErrorMsg(TaskConfirmationActivity.this, error);
                        }
                    }

            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
