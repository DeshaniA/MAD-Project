package com.example.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    TextInputEditText task,goal;
    int maxid = 0;
    Member member;

    //Initialize variables
    private Toolbar toolbar;
    TextView tvTimer1,tvTimer2;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    Button button,button2;
    CheckBox c1,c2,c3,c4,c5,c6,c7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        task = findViewById(R.id.task);
        goal = findViewById(R.id.goal);
        button = findViewById(R.id.btnsave);
        button2 = findViewById(R.id.btnback);
        c1 = findViewById(R.id.monday);
        c2 = findViewById(R.id.tuesday);
        c3 = findViewById(R.id.wednesday);
        c4 = findViewById(R.id.thursday);
        c5 = findViewById(R.id.friday);
        c6 = findViewById(R.id.saturday);
        c7 = findViewById(R.id.sunday);

        //Assign variable
        tvTimer1 = findViewById(R.id.tv_timer1);
        tvTimer2 = findViewById(R.id.tv_timer2);

        member = new Member();
        ref = database.getInstance().getReference().child("Task");


        final String d1 = "Monday";
        final String d2 = "Tuesday";
        final String d3 = "Wednesday";
        final String d4 = "Thursday";
        final String d5 = "Friday";
        final String d6 = "Saturday";
        final String d7 = "Sunday";

        tvTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddTask.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                //Initialize Calender
                                Calendar calendar = Calendar.getInstance();
                                //set hour and minute
                                calendar.set(0,0,0,t1Hour,t1Minute);
                                //set selected time on text view
                                tvTimer1.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );
                //Displayed previous selected time
                timePickerDialog.updateTime(t1Hour,t1Minute);
                //show dialog
                timePickerDialog.show();
            }
        });



        tvTimer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddTask.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour = hourOfDay;
                                t2Minute = minute;
                                //Initialize Calender
                                Calendar calendar = Calendar.getInstance();
                                //set hour and minute
                                calendar.set(0,0,0,t2Hour,t2Minute);
                                //set selected time on text view
                                tvTimer2.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );
                //Displayed previous selected time
                timePickerDialog.updateTime(t2Hour,t2Minute);
                //show dialog
                timePickerDialog.show();
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid =(int) dataSnapshot.getChildrenCount();
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                member.setTaskName(task.getText().toString());
                member.setGoal(goal.getText().toString());
                member.setStart(tvTimer1.getText().toString());
                member.setEnd(tvTimer2.getText().toString());

                ref.child(String.valueOf(maxid+1)).setValue(member);

                if(c1.isChecked()){
                    member.setDay1(d1);
                    ref.child(String.valueOf(maxid+1)).setValue(member);
                }
                else{
                    //
                }
                if(c2.isChecked()){
                    member.setDay2(d2);
                    ref.child(String.valueOf(maxid+1)).setValue(member);
                }
                else{
                    //
                }
                if(c3.isChecked()){
                    member.setDay3(d3);
                    ref.child(String.valueOf(maxid+1)).setValue(member);
                }
                else{
                    //
                }
                if(c4.isChecked()){
                    member.setDay4(d4);
                    ref.child(String.valueOf(maxid+1)).setValue(member);
                }
                else{
                    //
                }
                if(c5.isChecked()){
                    member.setDay5(d5);
                    ref.child(String.valueOf(maxid+1)).setValue(member);
                }
                else{
                    //
                }
                if(c6.isChecked()){
                    member.setDay6(d6);
                    ref.child(String.valueOf(maxid+1)).setValue(member);
                }
                else{
                    //
                }
                if(c7.isChecked()){
                    member.setDay7(d7);
                    ref.child(String.valueOf(maxid+1)).setValue(member);
                }
                else{
                    //
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTask.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
}