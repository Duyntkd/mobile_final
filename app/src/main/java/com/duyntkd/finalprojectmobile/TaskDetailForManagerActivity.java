package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetail;
import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetailManager;
import com.duyntkd.finalprojectmobile.repositories.TaskRepository;
import com.duyntkd.finalprojectmobile.util.ErrorResponseUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

public class TaskDetailForManagerActivity extends AppCompatActivity {
    public static final String ACCEPT_STRING = "Accepted task";
    public static final String DENY_STRING = "Denied task";
    public static final String CONFIRM_STATUS = "Confirm status";
    public static final String TASK_ID_STRING = "task_Id";
    public static final String TASK_PENDING_STATUS_STRING = "isPending";
    public static final String USER_ID_STRING = "userId";
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/";
    private int taskId;
    private boolean isPending;
    private int userId;

    private TextView txtId;
    private TextView txtTitle;
    private TextView txtContent;
    private TextView txtSolutionDescription;
    private TextView txtAssigneeId;
    private TextView txtAssigneeName;
    private TextView txtStartDate;
    private TextView txtEndDate;


    private Button btnDenyTask;
    private Button btnAccepTask;
    private Button btnApproveTask;
    private Button btnDeclineTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_for_manager);

        taskId = Integer.parseInt(getIntent().getExtras().getString(TASK_ID_STRING));
        isPending = getIntent().getExtras().getBoolean(TASK_PENDING_STATUS_STRING);
        userId = getIntent().getExtras().getInt(USER_ID_STRING);

        txtId = findViewById(R.id.txtId);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        txtSolutionDescription = findViewById(R.id.txtSolutionDescription);
        txtAssigneeId = findViewById(R.id.txtAssigneeId);
        txtAssigneeName = findViewById(R.id.txtAssignee);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtStartDate = findViewById(R.id.txtStartDate);

        if(isPending) {
            btnAccepTask.setVisibility(View.GONE);
            btnDenyTask.setVisibility(View.GONE);
            btnApproveTask.setVisibility(View.VISIBLE);
            btnDeclineTask.setVisibility(View.VISIBLE);
        }
        loadData();

    }

    private void loadData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    requestUrl + "detail/manager?taskId=" + taskId,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            TaskForDetailManager taskForDetail = gson.fromJson(response.toString(), TaskForDetailManager.class);
                            txtId.setText(taskForDetail.getId() + "");
                            txtTitle.setText(taskForDetail.getTitle());
                            txtContent.setText(taskForDetail.getContent());
                            txtSolutionDescription.setText(taskForDetail.getSolutionDescription());
                            txtAssigneeId.setText(taskForDetail.getAssigneeId() + "");
                            txtAssigneeName.setText(taskForDetail.getAssigneeName());
                            txtStartDate.setText(taskForDetail.getStartDate().toString());
                            txtEndDate.setText(taskForDetail.getEndDate().toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }
            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void clickToAccepTask(View view) {
        Intent intent = new Intent(this, TaskConfirmationActivity.class);
        intent.putExtra(CONFIRM_STATUS, ACCEPT_STRING);

        int id = Integer.parseInt(txtId.getText().toString());
        intent.putExtra(TASK_ID_STRING, id);
        intent.putExtra(USER_ID_STRING, userId);
        startActivity(intent);
    }

    public void clickToDenyTask(View view) {
        Intent intent = new Intent(this, TaskConfirmationActivity.class);
        intent.putExtra(CONFIRM_STATUS, DENY_STRING);
        int id = Integer.parseInt(txtId.getText().toString());
        intent.putExtra(TASK_ID_STRING, id);
        intent.putExtra(USER_ID_STRING, userId);
        startActivity(intent);
    }

    public void clickToApproveTask(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("taskId", taskId);
            jsonBody.put("feedback", "");
            jsonBody.put("score", 0);
            jsonBody.put("status", "ongoing");
            jsonBody.put("approver", userId);
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
                                    TaskDetailForManagerActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(TaskDetailForManagerActivity.this, "Approval failed", Toast.LENGTH_SHORT);
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
                            ErrorResponseUtil.displayErrorMsg(TaskDetailForManagerActivity.this, error);
                        }
                    }

            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToAssignToNewOne(View view) {
        Intent intent = new Intent(this, TaskAssignmentActivity.class);
        startActivity(intent);
    }

    public void clickToDeclineTask(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("taskId", taskId);
            jsonBody.put("feedback", "");
            jsonBody.put("score", 0);
            jsonBody.put("status", "not approve");
            jsonBody.put("approver", userId);
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
                                    TaskDetailForManagerActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(TaskDetailForManagerActivity.this, "Approval failed", Toast.LENGTH_SHORT);
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
                            ErrorResponseUtil.displayErrorMsg(TaskDetailForManagerActivity.this, error);
                        }
                    }

            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
