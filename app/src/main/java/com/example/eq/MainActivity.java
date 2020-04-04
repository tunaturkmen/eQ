package com.example.eq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {//1 kez çalışıyor(yaratılırken)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void studentLoginTabbed(View view){//
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        //intent.putExtra("status","student");

    }

    /*public void unionLoginTabbed(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("status","union");
        startActivity(intent);
    }*/
}
