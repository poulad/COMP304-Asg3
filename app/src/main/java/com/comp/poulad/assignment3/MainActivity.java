package com.comp.poulad.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bEx1 = findViewById(R.id.bEx1);
        bEx1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ex1Activity.class);
            startActivity(intent);
        });

        Button bEx2 = findViewById(R.id.bEx2);
        bEx2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ex2Activity.class);
            startActivity(intent);
        });

        Button bEx3 = findViewById(R.id.bEx3);
        bEx3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ex3Activity.class);
            startActivity(intent);
        });
    }
}