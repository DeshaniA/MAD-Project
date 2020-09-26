package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fragment1 extends Fragment {
    FloatingActionButton fab;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView recyclerView; //= getActivity().findViewById(R.id.rv_f2);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment1_layout,container,false);
        fab = v.findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Fragment1.this.getActivity(),AddTask.class);
               startActivity(intent);
            }
        });


        recyclerView = (RecyclerView)v.findViewById(R.id.rv_f2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = database.getReference("Task");


        FirebaseRecyclerOptions<Member> options =
                new FirebaseRecyclerOptions.Builder<Member>()
                .setQuery(databaseReference,Member.class)
                .build();

        FirebaseRecyclerAdapter<Member,Holder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member, Holder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Member model) {
                        holder.setitem(getActivity(),model.getTaskName(),model.getGoal(),model.getDay1(),model.getDay2(),model.getDay3(),model.getDay4(),model.getDay5(),model.getDay6(),model.getDay7(),model.getStart(),model.getEnd());
                    }

                    @NonNull
                    @Override
                    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                       View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.task_item,parent,false);

                        return new Holder(view);
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

        return v;
    }



    }



