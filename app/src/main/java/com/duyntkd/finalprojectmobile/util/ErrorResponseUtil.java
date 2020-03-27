package com.duyntkd.finalprojectmobile.util;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;


import org.json.JSONObject;

public class ErrorResponseUtil {


    public static void displayErrorMsg(Activity activity, VolleyError error) {
        NetworkResponse networkResponse = error.networkResponse;
        if (networkResponse != null && networkResponse.data != null) {
            try {
                JSONObject jsonError = new JSONObject(new String(networkResponse.data));
                String errors = jsonError.getJSONObject("errors").toString();
                errors = errors.replace("[", "");
                errors = errors.replace("]", "");
                errors = errors.replace("{", "");
                errors = errors.replace("}", "");
                String errorNotification = "ERROR: " + errors;
                Toast.makeText(activity, errorNotification, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                if (e.getMessage() != null) {
                    String msg = e.getMessage();
                    if (msg.contains("Value Failed! Username existed!"))
                        Toast.makeText(activity, "Value Failed! Username existed! Please try another one!", Toast.LENGTH_LONG).show();
                    else if (msg.contains("User is not valid"))
                        Toast.makeText(activity, "User is not valid", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
