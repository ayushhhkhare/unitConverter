package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLOutput;
import android.widget.EditText;
import android.widget.Toast;

public class bmiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        Button button =  findViewById(R.id.button);
        button.setOnClickListener(v->
        {
            TextView bmi = findViewById(R.id.BMI);
            EditText height_ref = findViewById(R.id.heightInput);
            EditText weight_ref = findViewById(R.id.weightInput);

            String heightStr,weightStr;
            heightStr = height_ref.getText().toString();
            weightStr = weight_ref.getText().toString();
            if(weight_ref.length()==0 || height_ref.length()==0) {

                Toast.makeText(bmi.getContext(), "Incomplete input", Toast.LENGTH_SHORT).show();
                if (weight_ref.length() == 0) {
                    weight_ref.setError("This field cannot be empty");
                    weight_ref.requestFocus();
                } else {
                    height_ref.setError("This field cannot be empty");
                    height_ref.requestFocus();
                }
            }
            else{
                double height, weight, BMIval;
                height = Double.parseDouble(heightStr) / 100.0;
                weight = Double.parseDouble(weightStr);
                BMIval = getBMI(height, weight);
                bmi.setText("    Your BMI is " + String.format("%.1f", BMIval));
                String text = bmi.getText().toString();
                if (BMIval < 18.5)
                    bmi.setText(text + "\nYou are underweight");
                else if (BMIval < 25)
                    bmi.setText(text + "\nYour weight is normal");
                else if (BMIval < 30)
                    bmi.setText(text + "\n You are overweight");
                else
                    bmi.setText(text + "\n\t\t\t You are obese");

                System.out.println(text + " " + height + " " + weight);
            }
            });
    }

    double getBMI(double height,double weight){
        return weight/(height*height);
    }
}