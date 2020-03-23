package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.duyntkd.finalprojectmobile.fragments.admin.ManageUserFragment;
import com.duyntkd.finalprojectmobile.models.groups.Group;
import com.duyntkd.finalprojectmobile.models.roles.Role;
import com.duyntkd.finalprojectmobile.models.users.User;
import com.duyntkd.finalprojectmobile.util.ErrorResponseUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserCreateActivity extends AppCompatActivity {

    private int userId;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/";

    private EditText edtName;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtPhone;
    private EditText edtUsername;
    private Spinner spinnerRole;
    private Spinner spinnerGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

        userId = getIntent().getExtras().getInt(LoginActivity.USER_ID_TEXT);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtUsername = findViewById(R.id.edtUsername);
        spinnerGroup = findViewById(R.id.spinnerGroup);
        spinnerRole = findViewById(R.id.spinnerRole);
        loadRoles();

    }

    public void clickToCreateUser(View view) {
        String name = edtName.getText().toString();
        String username = edtUsername.getText().toString();
        String phone = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();
        int groupId = ((Group) spinnerGroup.getSelectedItem()).getId();
        int roleId = ((Role) spinnerRole.getSelectedItem()).getId();
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Confirm password not match password!", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", name);
            jsonBody.put("userName", username);
            jsonBody.put("phone", phone);
            jsonBody.put("password", password);
            jsonBody.put("groupId", groupId);
            jsonBody.put("roleId", roleId);
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    requestUrl + "users/create",
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            try {
                                String result = response.getString("status");
                                if (result.equals("success")) {
                                    UserCreateActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(UserCreateActivity.this, "Create failed", Toast.LENGTH_SHORT);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ErrorResponseUtil.displayErrorMsg(UserCreateActivity.this, error);
                        }
                    }
            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void loadRoles() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    requestUrl + "roles",
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<Role>>() {
                            }.getType();
                            ArrayList<Role> listRoles = gson.fromJson(response.toString(), listType);
                            ArrayAdapter<Role> roleSpinerAdapter = new ArrayAdapter<Role>
                                    (UserCreateActivity.this.getApplicationContext(),
                                            android.R.layout.simple_spinner_item, listRoles);
                            spinnerRole.setAdapter(roleSpinerAdapter);
                            spinnerRole.setSelection(0);
                            loadGroups();
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

    private void loadGroups() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    requestUrl + "groups",
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<Group>>() {
                            }.getType();
                            ArrayList<Group> listGroups = gson.fromJson(response.toString(), listType);
                            ArrayAdapter<Group> roleSpinerAdapter = new ArrayAdapter<Group>
                                    (UserCreateActivity.this.getApplicationContext(),
                                            android.R.layout.simple_spinner_item, listGroups);
                            spinnerGroup.setAdapter(roleSpinerAdapter);
                            spinnerGroup.setSelection(0);
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
