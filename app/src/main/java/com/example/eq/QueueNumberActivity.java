package com.example.eq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QueueNumberActivity extends AppCompatActivity {

    TextView timeInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_number);

        Bundle extras = getIntent().getExtras();

        setTime(extras);
    }

    public void setTime(Bundle extras){
        int timeSlot = Integer.parseInt(extras.getString("timeSlot"));
        int part = Integer.parseInt(extras.getString("part"));

        timeInfoTextView = findViewById(R.id.timeInfoTextView);

        if(part!=6){
            timeInfoTextView.setText(timeSlot+":"+(part-1)+"0-"+timeSlot+":"+part+"0");
        }
        else{
            timeInfoTextView.setText(timeSlot+":"+(part-1)+"0-"+(timeSlot+1)+":00");
        }
    }

    public void locationImageViewTabbed(View view){
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }
}
