package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetail;
import com.google.gson.Gson;

import org.json.JSONObject;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String TASK_ID_STRING = "task_Id";
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/";
    private int taskId;



    private TextView txtId;
    private TextView txtTitle;
    private TextView txtContent;
    private EditText edtSolutionDescription;
    private TextView txtAssignerId;
    private TextView txtAssignerName;
    private TextView txtStartDate;
    private TextView txtEndDate;
    private TextView txtStatus;
    private Button btnConfirmDone;
    private Button btnConfirmUndone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        taskId = Integer.parseInt(getIntent().getExtras().getString(TASK_ID_STRING));

        txtId = findViewById(R.id.txtId);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        edtSolutionDescription = findViewById(R.id.edtSolutionDescription);
        txtAssignerId = findViewById(R.id.txtAssignerId);
        txtAssignerName = findViewById(R.id.txtAssigner);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtStartDate = findViewById(R.id.txtStartDate);
        txtStatus = findViewById(R.id.txtStatus);
        btnConfirmDone = findViewById(R.id.btnConfirmDone);
        btnConfirmUndone = findViewById(R.id.btnConfirmUndone);

        loadData(taskId);

    }

    private void loadData(int taskId) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    requestUrl + "detail?taskId=" + taskId,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            TaskForDetail taskForDetail = gson.fromJson(response.toString(), TaskForDetail.class);
                            txtId.setText(taskForDetail.getId() + "");
                            txtTitle.setText(taskForDetail.getTitle());
                            txtContent.setText(taskForDetail.getContent());
                            edtSolutionDescription.setText(taskForDetail.getSolutionDescription());
                            txtAssignerId.setText("id: " + taskForDetail.getAssignerId());
                            txtAssignerName.setText(taskForDetail.getAssignerName());
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

    public void clickToConfirmTaskUndone(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm task undone")
                .setMessage("Do you really want to abort this task?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        RequestQueue requestQueue = Volley.newRequestQueue(TaskDetailActivity.this.getApplicationContext());
                        try {
                            JsonObjectRequest objectRequest = new JsonObjectRequest(
                                    Request.Method.GET,
                                    requestUrl + "confirm?status=undone&taskId=" + taskId,
                                    null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                String updateResult = response.getString("update").toString();
                                                if(updateResult.equals("Success")) {
                                                    txtStatus.setText("undone");
                                                    btnConfirmDone.setVisibility(View.GONE);
                                                    btnConfirmUndone.setVisibility(View.GONE);
                                                    Toast.makeText(TaskDetailActivity.this ,"update success", Toast.LENGTH_SHORT);
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
                                        }
                                    }

                            );
                            requestQueue.add(objectRequest);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void clickToConfirmTaskDone(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm task done")
                .setMessage("Do you really want to confirm this task done?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        RequestQueue requestQueue = Volley.newRequestQueue(TaskDetailActivity.this.getApplicationContext());
                        try {
                            JsonObjectRequest objectRequest = new JsonObjectRequest(
                                    Request.Method.GET,
                                    requestUrl + "confirm?status=done&taskId=" + taskId,
                                    null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                String updateResult = response.getString("update").toString();
                                                if(updateResult.equals("Success")) {
                                                    txtStatus.setText("done");
                                                    btnConfirmDone.setVisibility(View.GONE);
                                                    btnConfirmUndone.setVisibility(View.GONE);
                                                    Toast.makeText(TaskDetailActivity.this ,"update success", Toast.LENGTH_SHORT);
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
                                        }
                                    }

                            );
                            requestQueue.add(objectRequest);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}
