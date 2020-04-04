package com.example.eq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();
    }

    public void cancelQueueTabbed(View view){
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(ManageActivity.this);

        alertDialogBuilder.setTitle("Are you sure to cancel your queue")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String timeSlot=dataSnapshot.child("Users/"+mAuth.getCurrentUser().getUid()+"/timeSlot").getValue(String.class);
                                int part=dataSnapshot.child("Users/"+mAuth.getCurrentUser().getUid()+"/part").getValue(Integer.class);
                                int userCountinPart = dataSnapshot.child("TimeSlots/"+timeSlot+"/"+part).getValue(Integer.class);
                                int userCount = dataSnapshot.child("TimeSlots/"+timeSlot+"/userCount").getValue(Integer.class);

                                myRef.child("Users/"+mAuth.getCurrentUser().getUid()+"/timeSlot").setValue("00");
                                myRef.child("Users/"+mAuth.getCurrentUser().getUid()+"/part").setValue(0);
                                myRef.child("TimeSlots/"+timeSlot+"/"+part).setValue(userCountinPart-1);
                                myRef.child("TimeSlots/"+timeSlot+"/userCount").setValue(userCount-1);

                                finish();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ManageActivity.this, "Canceled", Toast.LENGTH_LONG).show();
                    }
                }).create();
        alertDialogBuilder.show();
    }

    public void changeQueueTabbed(View view){
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String timeSlot=dataSnapshot.child("Users/"+mAuth.getCurrentUser().getUid()+"/timeSlot").getValue(String.class);
                int part=dataSnapshot.child("Users/"+mAuth.getCurrentUser().getUid()+"/part").getValue(Integer.class);
                int userCountinPart = dataSnapshot.child("TimeSlots/"+timeSlot+"/"+part).getValue(Integer.class);
                int userCount = dataSnapshot.child("TimeSlots/"+timeSlot+"/userCount").getValue(Integer.class);

                myRef.child("Users/"+mAuth.getCurrentUser().getUid()+"/timeSlot").setValue("00");
                myRef.child("Users/"+mAuth.getCurrentUser().getUid()+"/part").setValue(0);
                myRef.child("TimeSlots/"+timeSlot+"/"+part).setValue(userCountinPart-1);
                myRef.child("TimeSlots/"+timeSlot+"/userCount").setValue(userCount-1);

                Intent intent = new Intent(getApplicationContext(), TimeSlotActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
