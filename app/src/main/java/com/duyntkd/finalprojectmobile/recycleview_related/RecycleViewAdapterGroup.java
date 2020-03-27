package com.duyntkd.finalprojectmobile.recycleview_related;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.duyntkd.finalprojectmobile.AbstractUserActivity;
import com.duyntkd.finalprojectmobile.GroupEditActivity;
import com.duyntkd.finalprojectmobile.LoginActivity;
import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.fragments.admin.ManageGroupFragment;
import com.duyntkd.finalprojectmobile.models.groups.Group;

import java.util.ArrayList;

public class RecycleViewAdapterGroup extends RecyclerView.Adapter<RecycleViewAdapterGroup.ViewHolder>{
    private ArrayList<Group> groupList;
    private AbstractUserActivity currentActivity;
    private ArrayList<ViewHolder> viewHolders = new ArrayList<>();

    public RecycleViewAdapterGroup(ArrayList<Group> groupList, AbstractUserActivity currentActivity) {
        this.groupList = groupList;
        this.currentActivity = currentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_group, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        viewHolders.add(holder);
        CardView cardView = holder.cardView;
        TextView txtId = cardView.findViewById(R.id.txtId);
        TextView txtGroupName = cardView.findViewById(R.id.txtGroupName);
        txtId.setText(groupList.get(position).getId() + "");
        txtGroupName.setText(groupList.get(position).getName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentActivity, GroupEditActivity.class);
                intent.putExtra(ManageGroupFragment.SELECTED_GROUP_ID_STRING, Integer.parseInt(((TextView)v.findViewById(R.id.txtId)).getText().toString()));
                intent.putExtra(LoginActivity.USER_ID_TEXT, currentActivity.getUserId());
                intent.putExtra(ManageGroupFragment.SELECTED_GROUP_NAME_STRING, ((TextView)v.findViewById(R.id.txtGroupName)).getText().toString());
                currentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
        }
    }
}
