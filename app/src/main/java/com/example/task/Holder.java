package com.example.task;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    TextView task,goalName,startTime,endTime;

    public Holder(@NonNull View itemView) {
        super(itemView);
    }

    public void setitem(FragmentActivity activity,String taskName,String goal,String day1,String day2,String day3,String day4,String day5,String day6,String day7,String start,String end){

        task = itemView.findViewById(R.id.task_tv);
        goalName = itemView.findViewById(R.id.goal_tv);
        startTime = itemView.findViewById(R.id.starttime_tv);
        endTime = itemView.findViewById(R.id.endtime_tv);

        task.setText(taskName);
        goalName.setText(goal);
        startTime.setText(start);
        endTime.setText(end);

    }
}
