package com.duyntkd.finalprojectmobile.repositories;

import com.duyntkd.finalprojectmobile.LoginActivity;
import com.duyntkd.finalprojectmobile.models.User;

import java.util.ArrayList;

public class UserRepository {

    public static ArrayList<User> getUsersForDisplay() {
        ArrayList<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(new User(0, "user1", "Nguyễn Văn A", "user1abc", LoginActivity.ROLE_EMPLOYEE, 1));
        fakeUserList.add(new User(1, "user2", "Nguyễn Văn B", "user2abc", LoginActivity.ROLE_EMPLOYEE, 1));
        fakeUserList.add(new User(2, "manager1", "Nguyễn Văn C", "user3abc", LoginActivity.ROLE_MANAGER, 1));
        fakeUserList.add(new User(3, "user3", "Nguyễn Văn D", "user4abc", LoginActivity.ROLE_EMPLOYEE, 2));
        fakeUserList.add(new User(4, "manager2", "Nguyễn Văn E", "user5abc", LoginActivity.ROLE_MANAGER, 2));
        fakeUserList.add(new User(5, "admin1", "Nguyễn Văn F", "user6abc", LoginActivity.ROLE_ADMIN, 0));
        return fakeUserList;
    }

    public static ArrayList<Integer> getAllGroupIds() {
        ArrayList<Integer> fakeGroupIdList = new ArrayList<>();
        fakeGroupIdList.add(0);
        fakeGroupIdList.add(1);
        fakeGroupIdList.add(2);
        return fakeGroupIdList;
    }


    public static ArrayList<String> getAllRoles() {
        ArrayList<String> fakeRoleList = new ArrayList<>();
        fakeRoleList.add(LoginActivity.ROLE_EMPLOYEE);
        fakeRoleList.add(LoginActivity.ROLE_ADMIN);
        fakeRoleList.add(LoginActivity.ROLE_MANAGER);
        return fakeRoleList;
    }

}
