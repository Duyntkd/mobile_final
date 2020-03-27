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
import com.duyntkd.finalprojectmobile.models.users.User;
import com.duyntkd.finalprojectmobile.util.ErrorResponseUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GroupCreateActivity extends AppCompatActivity {
    private EditText edtName;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/";

    @Override
    protected void onStart() {
        super.onStart();
        //loadEmployees();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);
        edtName = findViewById(R.id.edtName);
    }



    public void clickToCreateGroup(View view) {
        String name = edtName.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", name);
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    requestUrl + "groups",
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            try {
                                String result = response.getString("status");
                                if (result.equals("success")) {
                                    GroupCreateActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(GroupCreateActivity.this, "Create failed", Toast.LENGTH_SHORT);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ErrorResponseUtil.displayErrorMsg(GroupCreateActivity.this, error);
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
