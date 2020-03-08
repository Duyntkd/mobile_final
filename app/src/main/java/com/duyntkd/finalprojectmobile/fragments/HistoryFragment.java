package com.duyntkd.finalprojectmobile.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterTaskHistory;
import com.duyntkd.finalprojectmobile.repositories.TaskRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recycle_view_tasks;
    private RecycleViewAdapterTaskHistory adapter;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        recycle_view_tasks = (RecyclerView) rootView.findViewById(R.id.list_tasks_history);
        adapter = new RecycleViewAdapterTaskHistory(TaskRepository.getTasksHistoryForDisplay(), getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycle_view_tasks.setAdapter(adapter);
        recycle_view_tasks.setLayoutManager(layoutManager);
        return rootView;
    }

}
