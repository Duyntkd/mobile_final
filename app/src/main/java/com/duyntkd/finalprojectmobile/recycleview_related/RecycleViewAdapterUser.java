package com.duyntkd.finalprojectmobile.recycleview_related;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.models.User;

import java.util.ArrayList;

public class RecycleViewAdapterUser extends RecyclerView.Adapter<RecycleViewAdapterUser.ViewHolder> {
    private ArrayList<User> usersList;
    private Activity currentActivity;
    private ArrayList<Integer> groupIdList;
    private ArrayList<String> groupRoleList;

    public RecycleViewAdapterUser(ArrayList<User> taskList, Activity currentActivity, ArrayList<Integer> groupIdList, ArrayList<String> groupRoleList) {
        this.usersList = taskList;
        this.currentActivity = currentActivity;
        this.groupIdList = groupIdList;
        this.groupRoleList = groupRoleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_user, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView txtId = cardView.findViewById(R.id.txtId);
        EditText edtName = cardView.findViewById(R.id.edtName);
        EditText edtUsername = cardView.findViewById(R.id.edtUsername);
        EditText edtPassword = cardView.findViewById(R.id.edtPassword);
        Spinner dropdownListGroup = cardView.findViewById(R.id.dropdownListGroup);
        Spinner dropdownListRole = cardView.findViewById(R.id.dropdownListRole);


        txtId.setText(usersList.get(position).getId() + "");
        edtName.setHint(usersList.get(position).getName());
        edtUsername.setHint(usersList.get(position).getUsername());

        ArrayAdapter<Integer> groupSpinerAdapter = new ArrayAdapter<Integer>
                (this.currentActivity.getApplicationContext(),
                        android.R.layout.simple_spinner_item, groupIdList);

        ArrayAdapter<String> roleSpinerAdapter = new ArrayAdapter<String>
                (this.currentActivity.getApplicationContext(),
                        android.R.layout.simple_spinner_item, groupRoleList);

        dropdownListGroup.setAdapter(groupSpinerAdapter);
        dropdownListRole.setAdapter(roleSpinerAdapter);
        if (!usersList.isEmpty()) {
            dropdownListGroup.setSelection(groupIdList.indexOf(usersList.get(position).getGroupId()));
        }
        if (!groupRoleList.isEmpty()) {
            dropdownListGroup.setSelection(groupIdList.indexOf(usersList.get(position).getRole()));
        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
        }
    }
}
