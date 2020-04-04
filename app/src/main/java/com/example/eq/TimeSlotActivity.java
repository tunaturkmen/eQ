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

public class TimeSlotActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();
    }

    public void time1Tabbed(View view){
        searchPlace("10");
    }

    public void time2Tabbed(View view){
        searchPlace("11");
    }

    public void time3Tabbed(View view){
        searchPlace("12");
    }

    public void time4Tabbed(View view){
        searchPlace("13");
    }

    public void time5Tabbed(View view){
        searchPlace("14");
    }

    public void searchPlace(final String timeSlot){
        myRef.child("TimeSlots/"+timeSlot).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final int userCountinTimeSlot = dataSnapshot.child("userCount").getValue(Integer.class);
                if(userCountinTimeSlot!=750){
                    for(int i=1;i<=6;i++){
                        int userCountinPart = dataSnapshot.child(Integer.toString(i)).getValue(Integer.class);
                        if(userCountinPart!=125){
                            myRef.child("TimeSlots/"+timeSlot+"/"+i).setValue(userCountinPart+1);
                            myRef.child("TimeSlots/"+timeSlot+"/userCount").setValue(userCountinTimeSlot+1);
                            myRef.child("Users/"+mAuth.getCurrentUser().getUid()+"/timeSlot").setValue(timeSlot);
                            myRef.child("Users/"+mAuth.getCurrentUser().getUid()+"/part").setValue(i);

                            Intent intent = new Intent(getApplicationContext(), QueueNumberActivity.class);
                            intent.putExtra("timeSlot",timeSlot);
                            intent.putExtra("part",Integer.toString(i));
                            startActivity(intent);
                            finish();
                            break;
                        }
                    }
                }
                else{
                    Toast.makeText(TimeSlotActivity.this, "There is no place in ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
