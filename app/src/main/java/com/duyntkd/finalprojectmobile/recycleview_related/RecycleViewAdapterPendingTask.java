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
import com.duyntkd.finalprojectmobile.LoginActivity;
import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.SelfTaskEditActivity;
import com.duyntkd.finalprojectmobile.fragments.TaskManagementFragment;
import com.duyntkd.finalprojectmobile.models.tasks.SelfTaskInfoForList;

import java.util.ArrayList;

public class RecycleViewAdapterPendingTask extends RecyclerView.Adapter<RecycleViewAdapterPendingTask.ViewHolder> {

    private ArrayList<SelfTaskInfoForList> taskList;
    private AbstractUserActivity currentActivity;

    public RecycleViewAdapterPendingTask(ArrayList<SelfTaskInfoForList> taskList, AbstractUserActivity currentActivity) {
        this.taskList = taskList;
        this.currentActivity = currentActivity;
    }

    @NonNull
    @Override
    public RecycleViewAdapterPendingTask.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_task_pending, parent, false);
        return new RecycleViewAdapterPendingTask.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterPendingTask.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView txtId = cardView.findViewById(R.id.txtId);
        TextView txtTitle = cardView.findViewById(R.id.txtTitle);
        TextView txtDeadline = cardView.findViewById(R.id.txtDeadline);
        TextView txtStatus = cardView.findViewById(R.id.txtStatus);

        txtId.setText(taskList.get(position).getId() + "");
        txtTitle.setText(taskList.get(position).getTitle());
        txtDeadline.setText(taskList.get(position).getEndDate().toString());
        txtStatus.setText(taskList.get(position).getStatus());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentActivity, SelfTaskEditActivity.class);
                intent.putExtra(TaskManagementFragment.TASK_ID_STRING, Integer.parseInt(((TextView)v.findViewById(R.id.txtId)).getText().toString()));
                intent.putExtra(LoginActivity.USER_ID_TEXT, currentActivity.getUserId());
                currentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
        }
    }
}
