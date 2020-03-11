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

import com.duyntkd.finalprojectmobile.R;
import com.duyntkd.finalprojectmobile.TaskDetailActivity;
import com.duyntkd.finalprojectmobile.models.tasks.TaskInfoforList;

import java.util.ArrayList;

public class RecycleViewAdapterTask extends RecyclerView.Adapter<RecycleViewAdapterTask.ViewHolder>{

    private ArrayList<TaskInfoforList> taskList;
    private Activity currentActivity;

    public RecycleViewAdapterTask(ArrayList<TaskInfoforList> taskList, Activity currentActivity) {
        this.taskList = taskList;
        this.currentActivity = currentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_task, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView txtId = cardView.findViewById(R.id.txtId);
        TextView txtTitle = cardView.findViewById(R.id.txtTitle);
        TextView txtDeadline = cardView.findViewById(R.id.txtDeadline);
        TextView txtAssigner = cardView.findViewById(R.id.txtAssigner);

        txtId.setText(taskList.get(position).getId() + "");
        txtTitle.setText(taskList.get(position).getTitle());
        txtDeadline.setText(taskList.get(position).getEndDate().toString());
        txtAssigner.setText(taskList.get(position).getAssigner());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentActivity, TaskDetailActivity.class);
                intent.putExtra(TaskDetailActivity.TASK_ID_STRING, ((TextView)v.findViewById(R.id.txtId)).getText().toString());
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
