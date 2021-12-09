package com.creasmit.task.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creasmit.task.R;
import com.creasmit.task.data.model.Task;

import java.util.List;

public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private LayoutInflater layoutInflater;

    public TaskViewAdapter(Context context) {
        this.tasks = tasks;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.txt_description.setText(this.tasks.get(position).getDescription());
        holder.txt_delay.setText(this.tasks.get(position).getDelay());
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        LinearLayout item;
        TextView txt_description, txt_delay;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item = itemView.findViewById(R.id.item);
            this.txt_description = itemView.findViewById(R.id.txt_description);
            this.txt_delay = itemView.findViewById(R.id.txt_delay);
        }
    }
}
