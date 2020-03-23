package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.duyntkd.finalprojectmobile.fragments.TaskManagementFragment;
import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetail;
import com.duyntkd.finalprojectmobile.util.ErrorResponseUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SelfTaskEditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private Button btnsetDate;
    private TextView txtDeadline;
    private int taskId;
    private int userId;
    private EditText edtTaskContent;
    private EditText edtTitle;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/";
    private TextView txtId;

    @Override
    protected void onStart() {
        super.onStart();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    requestUrl + "self/detail?taskId=" + taskId,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            TaskForDetail taskForDetail = gson.fromJson(response.toString(), TaskForDetail.class);
                            int length = taskForDetail.getEndDate().toString().length();
                            if(length != 10) {
                                String unFormatDate = taskForDetail.getEndDate();
                                String formatDate = "";
                                switch (length) {
                                    case 8:
                                        formatDate = "0" + unFormatDate.substring(0,2) + "0" + unFormatDate.substring(2, unFormatDate.length()) ;
                                        break;
                                    case 9:
                                        if(unFormatDate.charAt(0) == '0') {
                                            formatDate =unFormatDate.substring(0,2) + "0" + unFormatDate.substring(2, unFormatDate.length()) ;
                                        } else {
                                            formatDate = "0" + unFormatDate;
                                        }
                                }
                                taskForDetail.setEndDate(formatDate);
                            }
                            txtId.setText(taskForDetail.getId() + "");
                            edtTitle.setText(taskForDetail.getTitle());
                            edtTaskContent.setText(taskForDetail.getContent());
                            txtDeadline.setText(taskForDetail.getEndDate().toString());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sef_task_edit);
        btnsetDate = findViewById(R.id.btnSetDate);
        txtDeadline = findViewById(R.id.txtDeadline);
        edtTaskContent = findViewById(R.id.edtPassword);
        edtTitle = findViewById(R.id.edtName);
        txtId = findViewById(R.id.txtUserId);
        taskId = getIntent().getExtras().getInt(TaskManagementFragment.TASK_ID_STRING);

    }

    private void loadData() {

    }

    public void clickToUpdateTask(View view) {
        String taskContent = edtTaskContent.getText().toString();
        String deadline = txtDeadline.getText().toString();
        String title = edtTitle.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("taskId", taskId);
            jsonBody.put("content", taskContent);
            jsonBody.put("deadline", deadline);
            jsonBody.put("title", title);
            String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/self";
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    requestUrl,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String result = response.getString("status");
                                if(result.equals("success")) {
                                    SelfTaskEditActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(SelfTaskEditActivity.this, "Update failed", Toast.LENGTH_SHORT);
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
                            ErrorResponseUtil.displayErrorMsg(SelfTaskEditActivity.this, error);
                        }
                    }

            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void clickToRemoveTask(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("taskId", taskId);
            String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/self/delete";
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    requestUrl,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String result = response.getString("status");
                                if(result.equals("success")) {
                                    SelfTaskEditActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(SelfTaskEditActivity.this, "Remove failed", Toast.LENGTH_SHORT);
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
}
