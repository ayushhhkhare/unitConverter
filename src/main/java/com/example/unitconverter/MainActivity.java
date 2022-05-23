package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button weightButton,lengthButton,tempButton,bmiButton,speedButton, currButton,volButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Weight conversion
        weightButton = findViewById(R.id.weight);
        weightButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, weightActivity.class)));

        //Length Conversion
        lengthButton = findViewById(R.id.length);
        lengthButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, lengthActivity.class)));


        //BMI index
        bmiButton = findViewById(R.id.BMI);
        bmiButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, bmiActivity.class)));
    }
}