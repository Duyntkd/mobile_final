package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.services.AuthenticationService;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private AuthenticationService authService;
    private Intent intent;

    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_MANAGER = "Manager";
    public static final String ROLE_EMPLOYEE = "Employee";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authService = new AuthenticationService(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView txtErrorMsg = (TextView)findViewById(R.id.txtErrorMsg);
        txtErrorMsg.setVisibility(View.GONE);
    }

    public void onclickLogin(View view) {
        String userName = ((EditText) findViewById(R.id.edtUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.edtPassword)).getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Username", userName);
            jsonBody.put("Password", password);

            String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/authen/login";


            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    requestUrl,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String role = response.getString("role").toString();
                                if (role != "") {
                                    switch (role.toString()) {
                                        case ROLE_ADMIN:
                                            intent = new Intent(LoginActivity.this, AdminActivity.class);
                                            break;
                                        case ROLE_MANAGER:
                                            intent = new Intent(LoginActivity.this, ManagerActivity.class);
                                            break;
                                        case ROLE_EMPLOYEE:
                                            intent = new Intent(LoginActivity.this, EmployeeActivity.class);
                                            break;
                                    }
                                    LoginActivity.this.startActivity(intent);

                                } else {
                                    TextView txtErrorMsg = (TextView)LoginActivity.this.findViewById(R.id.txtErrorMsg);
                                    txtErrorMsg.setText("Login failed please check your username and password!");
                                    txtErrorMsg.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception e) {
                                Log.e("Response error: ", e.getMessage());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error message;: ", error.getMessage());
                        }
                    }

            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
