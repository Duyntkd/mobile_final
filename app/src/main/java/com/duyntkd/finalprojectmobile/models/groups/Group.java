package com.duyntkd.finalprojectmobile.models.groups;

import androidx.annotation.NonNull;

public class Group {

    private int id;
    private String name;
    private int managerId;

    public Group(int id, String name, int managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
