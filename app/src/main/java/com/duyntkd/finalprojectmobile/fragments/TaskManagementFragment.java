package com.duyntkd.finalprojectmobile.fragments;


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
import com.duyntkd.finalprojectmobile.TaskAssignmentActivity;
import com.duyntkd.finalprojectmobile.models.tasks.SelfTaskInfoForList;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterPendingTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskManagementFragment extends Fragment {
    public static final String TASK_ID_STRING = "taskId";

    private RecyclerView recycle_view_tasks;
    private RecycleViewAdapterPendingTask adapter;
    private String requestUrl = "https://mobilefinalprojectserver.azurewebsites.net/api/tasks/self";
    private ArrayList<SelfTaskInfoForList> resultForCurrentTasks;
    private View rootView;
    private Button btnCreateNewTask;

    @Override
    public void onStart() {
        super.onStart();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        AbstractUserActivity currentActivity = (AbstractUserActivity) this.getActivity();
        int userId = currentActivity.getUserId();
        try {
            JsonArrayRequest arrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    requestUrl + "?userId=" + userId,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Gson gson = new Gson();
                                Type listType = new TypeToken<ArrayList<SelfTaskInfoForList>>() {
                                }.getType();
                                ArrayList<SelfTaskInfoForList> listCurrentTask = gson.fromJson(response.toString(), listType);
                                resultForCurrentTasks = listCurrentTask;
                                recycle_view_tasks = (RecyclerView) rootView.findViewById(R.id.list_tasks);
                                adapter = new RecycleViewAdapterPendingTask(resultForCurrentTasks, (AbstractUserActivity) getActivity());
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

    public TaskManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_task_management, container, false);
        // Inflate the layout for this fragment
        
        btnCreateNewTask = rootView.findViewById(R.id.btnCreateNewTask);
        btnCreateNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbstractUserActivity currentActivity = (AbstractUserActivity) getActivity();
                String userId = currentActivity.getUserId() + "";
                Intent intent = new Intent(getActivity(), TaskAssignmentActivity.class);
                intent.putExtra(LoginActivity.USER_ID_TEXT, userId);
                intent.putExtra(TaskAssignmentActivity.IS_A_SELF_TASK, true);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
