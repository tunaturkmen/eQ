package com.example.eq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class testActiivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_actiivty);
    }

    public void button1(View view){
        //getApplicationContext bulunduğum sayfayı bana veriyor
        Intent newPage = new Intent(getApplicationContext(),MapActivity.class);
        startActivity(newPage);
    }

    public void button2(View view){

    }

}
