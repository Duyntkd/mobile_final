package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.models.users.User;
import com.duyntkd.finalprojectmobile.util.ErrorResponseUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskAssignmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static final String IS_A_SELF_TASK = "isASelfTask";

    private Button btnsetDate;
    private TextView txtDeadline;
    private TextView txtTaskId;
    private TextView txtTaskIdLabel;
    private TextView txtAssigneeIdLabel;
    private String userId;
    private int groupId;
    private Spinner spinnerAssignee;
    private EditText edtTaskContent;
    private EditText edtTitle;
    private boolean isASelfTask;
    private int assigneeId;
    private String taskStatus;

    public boolean isASelfTask() {
        return isASelfTask;
    }


    public String getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task_to_new_one);
        btnsetDate = findViewById(R.id.btnSetDate);
        txtDeadline = findViewById(R.id.txtDeadline);
        spinnerAssignee = findViewById(R.id.spinnerAssignee);
        edtTaskContent = findViewById(R.id.edtPassword);
        edtTitle = findViewById(R.id.edtName);
        txtTaskId = findViewById(R.id.txtTaskId);
        txtTaskIdLabel = findViewById(R.id.txtTaskIdLabel);
        txtAssigneeIdLabel = findViewById(R.id.txtAssigneeIdLabel);
        txtTaskIdLabel.setVisibility(View.GONE);
        txtTaskId.setVisibility(View.GONE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        userId = getIntent().getExtras().getString(LoginActivity.USER_ID_TEXT);
        groupId = getIntent().getExtras().getInt(LoginActivity.USER_GROUP_ID_TEXT);
        isASelfTask = getIntent().getExtras().getBoolean(IS_A_SELF_TASK);
        if(isASelfTask) {
            txtTaskId.setVisibility(View.GONE);
            txtTaskIdLabel.setVisibility(View.GONE);
            txtAssigneeIdLabel.setVisibility(View.GONE);
            spinnerAssignee.setVisibility(View.GONE);
        }
        loadGroupEmployee();
    }

    public void clickToSetDate(View view) {
        showDatePickerDialog();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String strMonth = month + "";
        String strDayOfMonth = dayOfMonth + "";
        if(strMonth.length() == 1) strMonth = "0" + month;
        if(strDayOfMonth.length() == 1) strDayOfMonth = "0" + dayOfMonth;
        String date = strMonth + "/" + strDayOfMonth + "/" + year;
        txtDeadline.setText(date);
        Date deadlineDate = new GregorianCalendar(year, month - 1, dayOfMonth   ).getTime();
    }

    public void clickToAssignTask(View view) {
        if(isASelfTask) {
            assigneeId = Integer.parseInt(userId);
            taskStatus = "waiting";
        } else {
            assigneeId = ((User)spinnerAssignee.getSelectedItem()).getId();
            taskStatus = "ongoing";
        }
        String taskContent = edtTaskContent.getText().toString();
        String deadline = txtDeadline.getText().toString();
        String title = edtTitle.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("assignerId", Integer.parseInt(userId));
            jsonBody.put("assigneeId", assigneeId);
            jsonBody.put("content", taskContent);
            jsonBody.put("deadline", deadline);
            jsonBody.put("title", title);
            jsonBody.put("status", taskStatus);
            String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/assign";
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    requestUrl,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String result = response.getString("status");
                                if(result.equals("success")) {
                                    TaskAssignmentActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(TaskAssignmentActivity.this, "Create failed", Toast.LENGTH_SHORT);
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
                            ErrorResponseUtil.displayErrorMsg(TaskAssignmentActivity.this, error);
                        }
                    }

            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadGroupEmployee() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    "https://mobilefinalprojectserver.azurewebsites.net/api/groups/users?groupId=" + groupId,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<User>>() {
                            }.getType();
                            ArrayList<User> listRoles = gson.fromJson(response.toString(), listType);
                            ArrayAdapter<User> roleSpinerAdapter = new ArrayAdapter<User>
                                    (TaskAssignmentActivity.this.getApplicationContext(),
                                            android.R.layout.simple_spinner_item, listRoles);
                            spinnerAssignee.setAdapter(roleSpinerAdapter);
                            spinnerAssignee.setSelection(0);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }
            );
            requestQueue.add(arrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
