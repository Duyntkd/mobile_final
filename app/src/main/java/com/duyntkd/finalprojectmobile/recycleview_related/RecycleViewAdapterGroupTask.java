package com.duyntkd.finalprojectmobile.recycleview_related;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.duyntkd.finalprojectmobile.ManagerActivity;
import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.TaskDetailForManagerActivity;
import com.duyntkd.finalprojectmobile.models.tasks.GroupTaskInfoForList;
import com.duyntkd.finalprojectmobile.models.tasks.TaskForDetailManager;

import java.util.ArrayList;

public class RecycleViewAdapterGroupTask extends RecyclerView.Adapter<RecycleViewAdapterGroupTask.ViewHolder> {

    private ArrayList<GroupTaskInfoForList> taskList;
    private Activity currentActivity;
    private boolean isPendingTasks;

    public RecycleViewAdapterGroupTask(ArrayList<GroupTaskInfoForList> taskList, Activity currentActivity, boolean isPendingTasks) {
        this.taskList = taskList;
        this.currentActivity = currentActivity;
        this.isPendingTasks = isPendingTasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_task_group, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView txtId = cardView.findViewById(R.id.txtId);
        TextView txtTitle = cardView.findViewById(R.id.txtTitle);
        TextView txtDeadline = cardView.findViewById(R.id.txtDeadline);
        TextView txtAssignee = cardView.findViewById(R.id.txtAssignee);
        TextView txtStatus = cardView.findViewById(R.id.txtStatus);

        txtId.setText(taskList.get(position).getId() + "");
        txtTitle.setText(taskList.get(position).getTitle());
        txtDeadline.setText(taskList.get(position).getEndDate().toString());
        txtAssignee.setText(taskList.get(position).getAssignee());
        txtStatus.setText(taskList.get(position).getStatus());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagerActivity managerActivity = (ManagerActivity)currentActivity;
                Intent intent = new Intent(currentActivity, TaskDetailForManagerActivity.class);
                intent.putExtra(TaskDetailForManagerActivity.TASK_ID_STRING, ((TextView)v.findViewById(R.id.txtId)).getText().toString());
                if(isPendingTasks) {
                    intent.putExtra(TaskDetailForManagerActivity.TASK_PENDING_STATUS_STRING, true);
                } else {
                    intent.putExtra(TaskDetailForManagerActivity.TASK_PENDING_STATUS_STRING, false);
                }
                intent.putExtra(TaskDetailForManagerActivity.USER_ID_STRING, managerActivity.getUserId());
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
