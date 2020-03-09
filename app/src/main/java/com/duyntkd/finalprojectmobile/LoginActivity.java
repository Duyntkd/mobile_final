package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.duyntkd.finalprojectmobile.services.AuthenticationService;

public class LoginActivity extends AppCompatActivity {
    private AuthenticationService authService;
    private Intent intent;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_MANAGER = "manager";
    public static final String ROLE_EMPLOYEE = "employee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authService = new AuthenticationService();
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
        String role = authService.requestLogin(userName, password).toString();
        if (role != null) {
            switch (role) {
                case ROLE_ADMIN:
                    intent = new Intent(this, AdminActivity.class);
                    break;
                case ROLE_MANAGER:
                    intent = new Intent(this, ManagerActivity.class);
                    break;
                case ROLE_EMPLOYEE:
                    intent = new Intent(this, EmployeeActivity.class);
                    break;
            }
            startActivity(intent);

        } else {
            TextView txtErrorMsg = (TextView)findViewById(R.id.txtErrorMsg);
            txtErrorMsg.setText("Login failed please check your username and password!");
            txtErrorMsg.setVisibility(View.VISIBLE);
        }
    }
}
