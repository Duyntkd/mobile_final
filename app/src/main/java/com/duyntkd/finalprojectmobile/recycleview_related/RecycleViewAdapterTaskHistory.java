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
import com.duyntkd.finalprojectmobile.models.tasks.TaskInfoforHistoryList;

import java.util.ArrayList;

public class RecycleViewAdapterTaskHistory extends RecyclerView.Adapter<RecycleViewAdapterTaskHistory.ViewHolder>{
    private ArrayList<TaskInfoforHistoryList> taskList;
    private Activity currentActivity;

    public RecycleViewAdapterTaskHistory(ArrayList<TaskInfoforHistoryList> taskList, Activity currentActivity) {
        this.taskList = taskList;
        this.currentActivity = currentActivity;
    }

    @NonNull
    @Override
    public RecycleViewAdapterTaskHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_task_history, parent, false);
        return new RecycleViewAdapterTaskHistory.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterTaskHistory.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView txtId = cardView.findViewById(R.id.txtId);
        TextView txtTitle = cardView.findViewById(R.id.txtTitle);
        TextView txtDeadline = cardView.findViewById(R.id.txtDeadline);
        TextView txtAssigner = cardView.findViewById(R.id.txtAssigner);
        TextView txtStatus = cardView.findViewById(R.id.txtStatus);

        txtId.setText(taskList.get(position).getId() + "");
        txtTitle.setText(taskList.get(position).getTitle());
        txtDeadline.setText(taskList.get(position).getEndDate().toString());
        txtAssigner.setText(taskList.get(position).getAssigner());
        txtStatus.setText(taskList.get(position).getStatus());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentActivity, TaskDetailActivity.class);
                intent.putExtra(TaskDetailActivity.TASK_ID_STRING, ((TextView)v.findViewById(R.id.txtId)).getText().toString());
                intent.putExtra(TaskDetailActivity.IS_IN_HISTORY_MODE, true);
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

        public CardView getCardView() {
            return cardView;
        }
    }
}
