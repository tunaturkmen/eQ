package com.example.eq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();

    }

    public void startButtonTabbed(View view){

        myRef.child("Users/"+mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String timeSlotValue = dataSnapshot.child("timeSlot").getValue(String.class);
                if(timeSlotValue.equalsIgnoreCase("00")){
                    Intent intent = new Intent(getApplicationContext(), TimeSlotActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(HomeActivity.this, "You have already queue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void manageButtonTabbed(View view){
        myRef.child("Users/"+mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String timeSlotValue = dataSnapshot.child("timeSlot").getValue(String.class);
                if(timeSlotValue.equalsIgnoreCase("00")){
                    Toast.makeText(HomeActivity.this, "You don't have queue", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), ManageActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void eventMapTabbed(View view){
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }

    public void signOutTabbed(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
