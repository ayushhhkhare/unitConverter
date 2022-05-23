package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class weightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        Spinner inputDropdown = findViewById(R.id.weightUnitInput);
        Spinner outputDropdown = findViewById(R.id.weightUnitOutput);
        String []units = new String[]{"Select a unit","KG","Gram","Pound(lb)","Ounce","Quintal","Tonne"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,units);
        inputDropdown.setAdapter(adapter);
        outputDropdown.setAdapter(adapter);

        Button convert = findViewById(R.id.convertButton);
        TextView outputMessage = findViewById(R.id.conversionOutput);
        EditText input = findViewById(R.id.input);
        ConvertWeights ob = new ConvertWeights();

        convert.setOnClickListener(v->{
                String inputUnit = inputDropdown.getSelectedItem().toString();
                String outputUnit = outputDropdown.getSelectedItem().toString();
                String inputValue = input.getText().toString();
                double inputWeight,outputWeight;


                if(TextUtils.isEmpty(inputValue))
                {
                    input.setError("This field cannot be empty");
                    input.requestFocus();
                }
                else if(inputUnit.equals(units[0]))
                {
                    Toast.makeText(weightActivity.this,"Please select a unit from the menu!",Toast.LENGTH_SHORT).show();
                    inputDropdown.requestFocus();
                }
                else if( outputUnit.equals(units[0]))
                {
                    Toast.makeText(weightActivity.this,"Please select a unit from the menu!",Toast.LENGTH_SHORT).show();
                    outputDropdown.requestFocus();
                }
                else{
                    Toast.makeText(weightActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    inputWeight= Double.parseDouble(inputValue);
                    outputWeight = ob.getConversion(inputWeight,inputUnit,outputUnit);
                    outputMessage.setText((input.getText().toString() + " " + inputUnit + "  =  "+String.format( "%.3f",outputWeight) +" "+ outputUnit));
                }

        });

    }
}

class ConvertWeights{

    double getConversion(double input, String inputUnit,String outputUnit){
        double output=0;
        if(inputUnit.equals(outputUnit))
            output=input;
        else{
            if(inputUnit.equals("KG")){
                //{"Select a unit","KG","gram","pound(lb)","ounce","Quintal","Tonne"};
                switch (outputUnit){
                    case "Gram":
                        output=input*1000;
                        break;
                    case "Pound(lb)":
                        output=kgToPound(input);
                        break;
                    case "Ounce":
                        output=kgToOunce(input);
                        break;
                    case "Tonne":
                        output=kgToTonne(input);
                        break;
                    case "Quintal":
                        output=kgToQuintal(input);
                        break;
                }
            }
            else{
                switch (inputUnit){
                    case "Gram":
                        output=getConversion(input/1000.0,"KG",outputUnit);
                        break;
                    case "Pound(lb)":
                        output=getConversion(poundToKG(input),"KG",outputUnit);
                        break;
                    case "Ounce":
                        output=getConversion(ounceToKG(input),"KG",outputUnit);
                        break;
                    case "Tonne":
                        output=getConversion(tonneToKG(input),"KG",outputUnit);
                        break;
                    case "Quintal":
                        output=getConversion(quintalToKG(input),"KG",outputUnit);
                        break;
                }
            }
        }
        return output;
    }

    double ounceToKG(double value) {
        return 0.02835 * value;
    }
    double kgToOunce(double value) {
        return 35.274 * value;
    }
    double tonneToKG(double value) {
        return 1000* value;
    }
    double kgToTonne(double value) {
        return value/1000;
    }
    double poundToKG(double value) {
        return 0.4536 * value;
    }
    double kgToPound(double value) {
        return 2.2046 * value;
    }
    double quintalToKG(double value) {
        return 100 * value;
    }
    double kgToQuintal(double value) {
        return value/100;
    }
}
