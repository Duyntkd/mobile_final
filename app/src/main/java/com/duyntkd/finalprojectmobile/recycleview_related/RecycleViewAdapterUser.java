package com.duyntkd.finalprojectmobile.recycleview_related;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.duyntkd.finalprojectmobile.AbstractUserActivity;
import com.duyntkd.finalprojectmobile.LoginActivity;
import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.UserEditActivity;
import com.duyntkd.finalprojectmobile.fragments.admin.ManageUserFragment;
import com.duyntkd.finalprojectmobile.models.users.User;

import java.util.ArrayList;

public class RecycleViewAdapterUser extends RecyclerView.Adapter<RecycleViewAdapterUser.ViewHolder> {
    private ArrayList<User> usersList;
    private AbstractUserActivity currentActivity;
    private ArrayList<ViewHolder> viewHolders = new ArrayList<>();

    public ArrayList<ViewHolder> getViewHolders() {
        return viewHolders;
    }

    public RecycleViewAdapterUser(ArrayList<User> usersList, AbstractUserActivity currentActivity) {
        this.usersList = usersList;
        this.currentActivity = currentActivity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_user, parent, false);
        return new ViewHolder(cardView);
    }

   /* public void loadRoles(ArrayList<Role> groupRoleList) {
        for (int i = 0; i < viewHolders.size(); i++) {
            ViewHolder holder = viewHolders.get(i);
            CardView cardView = holder.cardView;
            Spinner dropdownListRole = cardView.findViewById(R.id.dropdownListRole);
            ArrayAdapter<Role> roleSpinerAdapter = new ArrayAdapter<Role>
                    (this.currentActivity.getApplicationContext(),
                            android.R.layout.simple_spinner_item, groupRoleList);
            dropdownListRole.setAdapter(roleSpinerAdapter);
            if (!usersList.isEmpty()) {
                dropdownListRole.setSelection(groupRoleList.indexOf(usersList.get(i).getRole()));
            }


        }
    }

    public void loadGroups(ArrayList<Group> groupList) {
        for (int i = 0; i < viewHolders.size(); i++) {
            ViewHolder holder = viewHolders.get(i);
            CardView cardView = holder.cardView;
            Spinner dropdownListGroup = cardView.findViewById(R.id.dropdownListGroup);
            ArrayAdapter<Group> groupSpinerAdapter = new ArrayAdapter<Group>
                    (this.currentActivity.getApplicationContext(),
                            android.R.layout.simple_spinner_item, groupList);
            dropdownListGroup.setAdapter(groupSpinerAdapter);
            if (!usersList.isEmpty()) {
                dropdownListGroup.setSelection(groupList.indexOf(usersList.get(i).getGroupId()));


            }
        }
    }*/

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        viewHolders.add(holder);
        CardView cardView = holder.cardView;
        TextView txtId = cardView.findViewById(R.id.txtId);
        TextView txtRoleName = cardView.findViewById(R.id.txtRoleName);
        TextView txtGroupName = cardView.findViewById(R.id.txtGroupName);
        TextView txtName = cardView.findViewById(R.id.txtName);
        TextView txtUsername = cardView.findViewById(R.id.txtUsername);

        txtId.setText(usersList.get(position).getId() + "");
        txtName.setText(usersList.get(position).getName());
        txtUsername.setText(usersList.get(position).getUsername());
        txtRoleName.setText(usersList.get(position).getRoleName());
        txtGroupName.setText(usersList.get(position).getGroupName());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentActivity, UserEditActivity.class);
                intent.putExtra(ManageUserFragment.SELECTED_USER_ID_STRING, Integer.parseInt(((TextView)v.findViewById(R.id.txtId)).getText().toString()));
                intent.putExtra(LoginActivity.USER_ID_TEXT, currentActivity.getUserId());
                currentActivity.startActivity(intent);
            }
        });

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
