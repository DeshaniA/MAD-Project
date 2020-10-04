package com.example.goals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity1 extends AppCompatActivity {

    EditText txtName, txtTarget, txtDeadline, txtDes;
    Button butSave, butShow, butUpdate, butDelete;
    DatabaseReference dbRef;
    Goal gal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        this.setTitle("Add Goal");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtName = findViewById(R.id.EtInputID);
        txtTarget = findViewById(R.id.EtInputName);
        txtDeadline = findViewById(R.id.EtInputDate);
        txtDes = findViewById(R.id.EtInputDes);

        butSave = findViewById(R.id.ButSave);
        butShow = findViewById(R.id.ButShow);
        butUpdate = findViewById(R.id.ButUpdate);
        butDelete = findViewById(R.id.ButDelete);

        gal = new Goal();

        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Goal").child("Gal1");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
            }
        });


        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("Goal").child("Gal1").child("target").setValue(txtTarget.getText().toString().trim());
                dbRef.child("Goal/Gal1/deadline").setValue(txtDeadline.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        });


        butShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Goal/Gal1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtTarget.setText(dataSnapshot.child("target").getValue().toString());
                            txtDeadline.setText(dataSnapshot.child("deadline").getValue().toString());
                            txtDes.setText(dataSnapshot.child("description").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "Cannot find Gal1", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Goal");
                if(TextUtils.isEmpty(txtName.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Empty Name", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(txtTarget.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Empty Target", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(txtDeadline.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Empty Deadline", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(txtDes.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Empty Description", Toast.LENGTH_SHORT).show();
                else{
                    gal.setName(txtName.getText().toString().trim());
                    gal.setTarget(txtTarget.getText().toString().trim());
                    gal.setDeadline(txtDeadline.getText().toString().trim());
                    gal.setDescription(txtDes.getText().toString().trim());
                    dbRef.child("Gal1").setValue(gal);
                    Toast.makeText(getApplicationContext(),"successfully inserted", Toast.LENGTH_SHORT).show();
                    clearControls();
                }
            }
        });
    }
    private  void clearControls(){
        txtName.setText("");
        txtTarget.setText("");
        txtDeadline.setText("");
        txtDes.setText("");
    }
}
