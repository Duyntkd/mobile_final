package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskAssignmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Button btnsetDate;
    private TextView txtDeadline;
    private int userId;
    private int groupId;
    private EditText edtAssigneeId;
    private EditText edtTaskContent;
    private EditText edtTitle;


    public int getUserId() {
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
        edtAssigneeId = findViewById(R.id.edtAssigneeId);
        edtTaskContent = findViewById(R.id.edtContent);
        edtTitle = findViewById(R.id.edtTitle);
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
        userId = this.getIntent().getExtras().getInt(LoginActivity.USER_ID_TEXT);

        int assigneeId = Integer.parseInt(edtAssigneeId.getText().toString());
        String taskContent = edtTaskContent.getText().toString();
        String deadline = txtDeadline.getText().toString();
        String title = edtTitle.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("assignerId", userId);
            jsonBody.put("assigneeId", assigneeId);
            jsonBody.put("content", taskContent);
            jsonBody.put("deadline", deadline);
            jsonBody.put("title", title);
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
                        }
                    }

            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
