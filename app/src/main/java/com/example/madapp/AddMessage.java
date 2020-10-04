package com.example.madapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diary.Model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button button,saveButton,showButton,updateButton,deleteButton;
    EditText messageText;

    DatabaseReference dbref;
    Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageText = (EditText) findViewById(R.id.simpleEditText1);

        button = (Button) findViewById(R.id.button);
        saveButton = (Button) findViewById(R.id.saveBtn);
        showButton = (Button) findViewById(R.id.showBtn);
        updateButton = (Button) findViewById(R.id.updateBtn);
        deleteButton = (Button) findViewById(R.id.delBtn);

        message = new Message();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Message");

                try {
                    if (TextUtils.isEmpty(messageText.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please Enter the Message...", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        message.setMessage(messageText.getText().toString().trim());

                        dbref.push().setValue(message);

                        //dbref.child("message1").setValue(message);
                        clearData();

                        Toast.makeText(getApplicationContext(),"Your Feedback Saved Successfully...",Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception ex){

                }
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference showRef = FirebaseDatabase.getInstance().getReference().child("Message").child("message1");
                showRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            messageText.setText(snapshot.child("message").getValue().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No Source to Display...",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Message");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("message1")){
                            try {
                                message.setMessage(messageText.getText().toString().trim());

                                dbref = FirebaseDatabase.getInstance().getReference().child("Message").child("message1");
                                dbref.setValue(message);

                                Toast.makeText(getApplicationContext(),"Your Message Updated Successfully...",Toast.LENGTH_SHORT).show();
                            }catch (Exception ex){

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No Source to Update...",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Message");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("feedback1")){
                            dbref = FirebaseDatabase.getInstance().getReference().child("Message").child("message1");
                            delRef.removeValue();
                            clearData();

                            Toast.makeText(getApplicationContext(),"Your Message Deleted Successfully...",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No Source to Delete...",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void clearData() {
        messageText.setText("");
    }
}