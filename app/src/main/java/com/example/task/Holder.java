package com.example.task;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {


    TextView task,goalName,startTime,endTime;
    ImageButton imageButton;
    private final Context context;
    ///Member member = new Member();


    public Holder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageButton = itemView.findViewById(R.id.imagebutton);
        imageButton.setOnClickListener(this);
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



    @Override
    public void onClick(View view) {

        showPopupMenu(view);

    }
    private void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {


        switch (item.getItemId()){
            case R.id.action_popup_edit:
                Intent intent = new Intent(context, EditTask.class);
                context.startActivity(intent);
                return true;
            case R.id.action_popup_delete:
                //deleteTask(task);
                return true;
            case R.id.action_popup_accomplished:
                return true;
            default:
                return false;
        }

    }

    /*private void deleteTask(TextView  taskName) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Task").child(String.valueOf(taskName));
        Toast.makeText(context.getApplicationContext(), "Artist Deleted", Toast.LENGTH_LONG).show();


        dR.removeValue();
    }*/
}
