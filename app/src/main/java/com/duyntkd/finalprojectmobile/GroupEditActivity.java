package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.duyntkd.finalprojectmobile.fragments.admin.ManageGroupFragment;
import com.duyntkd.finalprojectmobile.models.groups.Group;
import com.duyntkd.finalprojectmobile.models.users.User;
import com.duyntkd.finalprojectmobile.util.ErrorResponseUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GroupEditActivity extends AppCompatActivity {
    private int groupId;
    private String groupName;
    private User currentManager;

    private TextView txtId;
    private EditText edtName;
    private Spinner spinnerManager;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/";


    @Override
    protected void onStart() {
        super.onStart();
        groupId = getIntent().getExtras().getInt(ManageGroupFragment.SELECTED_GROUP_ID_STRING);
        groupName = getIntent().getExtras().getString(ManageGroupFragment.SELECTED_GROUP_NAME_STRING);
        edtName.setText(groupName);
        txtId.setText(groupId + "");
        loadEmployees();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_edit);
        txtId = findViewById(R.id.txtGroupId);
        edtName = findViewById(R.id.edtName);
        spinnerManager = findViewById(R.id.spinnerManager);

    }


    public void clickToUpdateInfo(View view) {
        String name = edtName.getText().toString();
        User manager = (User)spinnerManager.getSelectedItem();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("name", name);
            jsonBody.put("id", groupId);
            if(manager != null) {
                jsonBody.put("managerId", manager.getId());
            } else {
                jsonBody.put("managerId", 0);
            }
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    requestUrl + "groups/update",
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            try {
                                String result = response.getString("status");
                                if (result.equals("success")) {
                                    GroupEditActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(GroupEditActivity.this, "Update failed", Toast.LENGTH_SHORT);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ErrorResponseUtil.displayErrorMsg(GroupEditActivity.this, error);
                            error.printStackTrace();
                        }
                    }
            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadEmployees() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    requestUrl + "groups/" + "candidates?groupId=" + groupId,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<User>>() {
                            }.getType();
                            ArrayList<User> listEmployees = gson.fromJson(response.toString(), listType);
                            ArrayAdapter<User> roleSpinerAdapter = new ArrayAdapter<>
                                    (GroupEditActivity.this.getApplicationContext(),
                                            android.R.layout.simple_spinner_item, listEmployees);
                            spinnerManager.setAdapter(roleSpinerAdapter);
                            for (User user : listEmployees) {
                                if (user.getRoleId() == 2) {
                                    currentManager = user;
                                    break;
                                }
                            }
                            if(currentManager != null) {
                                spinnerManager.setSelection(listEmployees.indexOf(currentManager));
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
            requestQueue.add(arrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
