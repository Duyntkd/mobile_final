package com.duyntkd.finalprojectmobile.models.users;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String username;
    private String name;
    private String password;
    private int roleId;
    private String roleName;
    private String groupName;
    private int groupId;
    private String phone;
    private String status;

    public User(int id, String username, String name, String password, int roleId, String roleName, String groupName, int groupId, String phone, String status) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.roleName = roleName;
        this.groupName = groupName;
        this.groupId = groupId;
        this.phone = phone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return username + "-" + name;
    }
}
