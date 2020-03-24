package com.duyntkd.finalprojectmobile.fragments;


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
import com.duyntkd.finalprojectmobile.ManagerActivity;
import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.models.tasks.GroupTaskInfoForList;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterGroupTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingTaskManagementFragment extends Fragment {
    private RecyclerView recycle_view_tasks;
    private RecycleViewAdapterGroupTask adapter;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/group/pending";
    private ArrayList<GroupTaskInfoForList> resultForCurrentTasks;
    private View rootView;

    public PendingTaskManagementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        ManagerActivity currentActivity = (ManagerActivity) this.getActivity();
        int group = currentActivity.getGroupId();
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    requestUrl + "?groupId=" + group,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Gson gson = new Gson();
                                Type listType = new TypeToken<ArrayList<GroupTaskInfoForList>>() {
                                }.getType();
                                ArrayList<GroupTaskInfoForList> listCurrentTask = gson.fromJson(response.toString(), listType);
                                resultForCurrentTasks = listCurrentTask;
                                recycle_view_tasks = (RecyclerView) rootView.findViewById(R.id.list_tasks);
                                adapter = new RecycleViewAdapterGroupTask(resultForCurrentTasks, getActivity(), true);
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_manage_group_task, container, false);
        return rootView;
    }

}
