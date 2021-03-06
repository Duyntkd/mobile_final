package com.duyntkd.finalprojectmobile.fragments.employee;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.duyntkd.finalprojectmobile.AdminActivity;
import com.duyntkd.finalprojectmobile.EmployeeActivity;
import com.duyntkd.finalprojectmobile.LoginActivity;
import com.duyntkd.finalprojectmobile.ManagerActivity;
import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.models.tasks.TaskInfoforList;
import com.duyntkd.finalprojectmobile.presenter.CurrentUser;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterTask;
import com.duyntkd.finalprojectmobile.repositories.TaskRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageCurrentTaskFragment extends Fragment {
    private RecyclerView recycle_view_tasks;
    private RecycleViewAdapterTask adapter;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/current";
    private View rootView;
    private ArrayList<TaskInfoforList> resultForCurrentTasks;


    public ManageCurrentTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        recycle_view_tasks = (RecyclerView) rootView.findViewById(R.id.list_tasks);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        EmployeeActivity currentActivity = (EmployeeActivity) this.getActivity();
        int userId = currentActivity.getUserId();
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    requestUrl + "?userId=" +  userId,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Gson gson = new Gson();
                                Type listType = new TypeToken<ArrayList<TaskInfoforList>>(){}.getType();
                                ArrayList<TaskInfoforList> listCurrentTask = gson.fromJson(response.toString(), listType);
                                resultForCurrentTasks = listCurrentTask;
                                adapter = new RecycleViewAdapterTask(resultForCurrentTasks, getActivity());
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                recycle_view_tasks.setAdapter(adapter);
                                recycle_view_tasks.setLayoutManager(layoutManager);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_manage_task, container, false);
        return rootView;
    }




}
