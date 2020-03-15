package com.duyntkd.finalprojectmobile.presenter;

import android.util.Log;

import org.json.JSONObject;

public class CurrentUser {
    public static String userName;
    public static int id;
    public static String phone;
    public static int roleId;
    public static String roleName;
    public static int groupId;
    public static String status;
    public static String name;


    public  static void setCurrentUserInfo(JSONObject currentUserInfo) {
        try {
            id = currentUserInfo.getJSONObject("user").getInt("id");
            phone = currentUserInfo.getJSONObject("user").getString("phone");
            roleId = currentUserInfo.getJSONObject("user").getJSONObject("role").getInt("roleId");
            groupId = currentUserInfo.getJSONObject("user").getInt("groupId");
            status = currentUserInfo.getJSONObject("user").getString("status");
            userName = currentUserInfo.getJSONObject("user").getString("userName");
            roleName = currentUserInfo.getJSONObject("user").getJSONObject("role").getString("roleName");
            name = currentUserInfo.getJSONObject("user").getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
