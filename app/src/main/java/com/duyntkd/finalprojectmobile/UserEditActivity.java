package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserEditActivity extends AppCompatActivity {
    private int userId;
    private int selectedUserId;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/";
    private User selectedUser;

    private TextView textViewStatus;
    private TextView textViewUserId;
    private EditText edtName;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtPhone;
    private Spinner spinnerRole;
    private Spinner spinnerGroup;


    @Override
    protected void onStart() {
        super.onStart();
        loadData();

    }

    private void loadData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    requestUrl + "users/detail?userId=" + selectedUserId,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            User user = gson.fromJson(response.toString(), User.class);
                            selectedUser = user;
                            textViewUserId.setText(user.getId() + "");
                            textViewStatus.setText(user.getStatus());
                            edtPhone.setHint(user.getPhone());
                            edtName.setHint(user.getName());
                            loadRoles();
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
                                    (UserEditActivity.this.getApplicationContext(),
                                            android.R.layout.simple_spinner_item, listRoles);
                            spinnerRole.setAdapter(roleSpinerAdapter);
                            Role currentRole = null;
                            for (Role role : listRoles) {
                                if (role.getId() == selectedUser.getRoleId()) {
                                    currentRole = role;
                                    break;
                                }
                            }
                            spinnerRole.setSelection(listRoles.indexOf(currentRole));
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
                                    (UserEditActivity.this.getApplicationContext(),
                                            android.R.layout.simple_spinner_item, listGroups);
                            spinnerGroup.setAdapter(roleSpinerAdapter);
                            Group currentGroup = null;
                            for (Group group : listGroups) {
                                if (group.getId() == selectedUser.getGroupId()) {
                                    currentGroup = group;
                                    break;
                                }
                            }
                            spinnerGroup.setSelection(listGroups.indexOf(currentGroup));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        userId = getIntent().getExtras().getInt(LoginActivity.USER_ID_TEXT);
        selectedUserId = getIntent().getExtras().getInt(ManageUserFragment.SELECTED_USER_ID_STRING);

        textViewUserId = findViewById(R.id.txtUserId);
        textViewStatus = findViewById(R.id.txtStatus);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtPhone = findViewById(R.id.edtPhone);
        spinnerGroup = findViewById(R.id.spinnerGroup);
        spinnerRole = findViewById(R.id.spinnerRole);


    }

    public void clickToActiveAccount(View view) {

    }

    public void clickToDeactiveAccount(View view) {

    }

    public void clickToUpdateInfo(View view) {

    }
}
