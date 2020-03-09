package com.duyntkd.finalprojectmobile.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterTask;
import com.duyntkd.finalprojectmobile.recycleview_related.RecycleViewAdapterUser;
import com.duyntkd.finalprojectmobile.repositories.UserRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageUserFragment extends Fragment {
    private RecyclerView recycle_view_users;
    private RecycleViewAdapterUser adapter;


    public ManageUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_manage_user, container,
                false);
        recycle_view_users = (RecyclerView) rootView.findViewById(R.id.list_users);
        adapter = new RecycleViewAdapterUser(UserRepository.getUsersForDisplay(),
                getActivity(), UserRepository.getAllGroupIds(), UserRepository.getAllRoles());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycle_view_users.setAdapter(adapter);
        recycle_view_users.setLayoutManager(layoutManager);


        return rootView;
    }

}
