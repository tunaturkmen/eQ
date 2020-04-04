package com.example.eq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UnionActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    TextView totalUser;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    int[] usersinTimeSLots = {0, 0, 0, 0, 0,};
    int sumOfUsers=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();

        totalUser = findViewById(R.id.totalUser);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);

        myRef.child("TimeSlots").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(int i=0;i<5;i++){
                    usersinTimeSLots[i] = dataSnapshot.child((i+10)+"/userCount").getValue(Integer.class);
                    sumOfUsers+=dataSnapshot.child((i+10)+"/userCount").getValue(Integer.class);
                }
                totalUser.setText("Total User = "+sumOfUsers);
                textView1.setText("10:00-11:00 = "+usersinTimeSLots[0]);
                textView2.setText("11:00-12:00 = "+usersinTimeSLots[1]);
                textView3.setText("12:00-13:00 = "+usersinTimeSLots[2]);
                textView4.setText("13:00-14:00 = "+usersinTimeSLots[3]);
                textView5.setText("14:00-15:00 = "+usersinTimeSLots[4]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void signOutClicked(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}
