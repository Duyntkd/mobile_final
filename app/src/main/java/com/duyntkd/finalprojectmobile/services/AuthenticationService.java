package com.duyntkd.finalprojectmobile.services;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.AdminActivity;
import com.duyntkd.finalprojectmobile.EmployeeActivity;
import com.duyntkd.finalprojectmobile.LoginActivity;
import com.duyntkd.finalprojectmobile.ManagerActivity;
import com.duyntkd.finalprojectmobile.R;
import com.google.gson.JsonObject;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthenticationService extends AsyncTask<String, Void, String>{
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_MANAGER = "Manager";
    public static final String ROLE_EMPLOYEE = "Employee";
    public static final int CHECKLOGIN = 0;


    private Activity context;
    private RequestQueue requestQueue;
    private Object result;
    private Intent intent;
    private int requestType = CHECKLOGIN;

    public AuthenticationService(Activity context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }



    /*  public Object requestLogin(String userName, String password) {
         result = null;
         String url = "https://10.0.2.2:5001/api/authen/login";
         AsyncHttpClient client = new AsyncHttpClient();
         RequestParams params = new RequestParams();
         params.put("Username", userName);
         params.put("Password", password);
         client.get(url, params, new JsonHttpResponseHandler() {
             @Override
             public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                 result = response.toString();
             }

             @Override
             public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                 Log.e("Error message;: ", "Something wrong");
             }
         });
        return result;
    }*/

   /* public Object requestLogin(String userName, String password) {
        userName = userName.trim();
        password = password.trim();
        if(userName.isEmpty() || password.isEmpty()) return null;
        if (userName.equals(LoginActivity.ROLE_ADMIN)) return LoginActivity.ROLE_ADMIN;
        if (userName.equals(LoginActivity.ROLE_MANAGER)) return LoginActivity.ROLE_MANAGER;
        return LoginActivity.ROLE_EMPLOYEE;
    }*/

    public Object requestLogin(String userName, String password) {

        return result;
    }

    @Override
    protected String doInBackground(String... strings) {
        String userName = strings[0];
        String password = strings[1];


        result = null;

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
                                result = response.getString("role").toString();
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
        if (result == null) return "";
        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String role = s;
        if (role != "") {
            switch (role.toString()) {
                case ROLE_ADMIN:
                    intent = new Intent(context, AdminActivity.class);
                    break;
                case ROLE_MANAGER:
                    intent = new Intent(context, ManagerActivity.class);
                    break;
                case ROLE_EMPLOYEE:
                    intent = new Intent(context, EmployeeActivity.class);
                    break;
            }
            context.startActivity(intent);

        } else {
            TextView txtErrorMsg = (TextView)context.findViewById(R.id.txtErrorMsg);
            txtErrorMsg.setText("Login failed please check your username and password!");
            txtErrorMsg.setVisibility(View.VISIBLE);
        }
    }




}
