package com.firstapp.room_db_fragment_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new Fragment_A())
                .add(R.id.container_b, new Fragment_B())
                .commit();
    }
}