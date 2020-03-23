package com.duyntkd.finalprojectmobile.fragments.admin;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.AbstractUserActivity;
import com.duyntkd.finalprojectmobile.LoginActivity;
import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.UserCreateActivity;
import com.duyntkd.finalprojectmobile.models.groups.Group;
import com.duyntkd.finalprojectmobile.models.roles.Role;
import com.duyntkd.finalprojectmobile.models.tasks.SelfTaskInfoForList;
import com.duyntkd.finalprojectmobile.models.users.User;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterPendingTask;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterTask;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterUser;
import com.duyntkd.finalprojectmobile.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageUserFragment extends Fragment {
    public static final String SELECTED_USER_ID_STRING = "selectedUserId";
    private RecyclerView recycle_view_users;
    private RecycleViewAdapterUser adapter;
    private View rootView;
    private Button btnCreateUser;

    public ManageUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_manage_user, container,
                false);
        recycle_view_users = (RecyclerView) rootView.findViewById(R.id.list_users);
        btnCreateUser = rootView.findViewById(R.id.btnCreateNewUser);
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbstractUserActivity currentActivity = (AbstractUserActivity) getActivity();
                String userId = currentActivity.getUserId() + "";
                Intent intent = new Intent(getActivity(), UserCreateActivity.class);
                intent.putExtra(LoginActivity.USER_ID_TEXT, userId);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void loadData() {
        String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/users";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        AbstractUserActivity currentActivity = (AbstractUserActivity) this.getActivity();
        int userId = currentActivity.getUserId();
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    requestUrl,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Gson gson = new Gson();
                                Type listType = new TypeToken<ArrayList<User>>() {
                                }.getType();
                                ArrayList<User> listUsers = gson.fromJson(response.toString(), listType);
                                adapter = new RecycleViewAdapterUser(listUsers, (AbstractUserActivity) getActivity());
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                recycle_view_users.setAdapter(adapter);
                                recycle_view_users.setLayoutManager(layoutManager);
                            } catch (Exception e) {
                                e.printStackTrace();
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
