package com.duyntkd.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public abstract class AbstractUserActivity extends AppCompatActivity {

    protected int userId;
    protected int groupId;
    protected String role;

    public int getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getRole() {
        return role;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void logout() {
        Intent intent = new Intent(AbstractUserActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

}
