package com.duyntkd.finalprojectmobile.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.duyntkd.finalprojectmobile.AbstractUserActivity;
import com.duyntkd.finalprojectmobile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private Button btnLogout;
    private AbstractUserActivity currentActivity;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        currentActivity = (AbstractUserActivity) getActivity();
        btnLogout = rootView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentActivity.logout();
            }
        });
        return rootView;
    }

}
