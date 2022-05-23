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

public class lengthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        Spinner inputDropdown = findViewById(R.id.lengthUnitInput);
        Spinner outputDropdown = findViewById(R.id.lengthUnitOutput);
        String []units = new String[]{"Select a unit","Meter","KiloMeter","Mile","Yard","CentiMeter","Inch","Feet"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,units);
        inputDropdown.setAdapter(adapter);
        outputDropdown.setAdapter(adapter);

        Button convert = findViewById(R.id.convertButton);
        TextView outputMessage = findViewById(R.id.conversionOutput);
        EditText input = findViewById(R.id.input);
        ConvertLength ob = new ConvertLength();

        convert.setOnClickListener(v -> {
            String inputUnit = inputDropdown.getSelectedItem().toString();
            String outputUnit = outputDropdown.getSelectedItem().toString();
            String inputValue = input.getText().toString();
            double inputLength,outputLength;

            if(TextUtils.isEmpty(inputValue))
            {
                input.setError("This field cannot be empty");
                input.requestFocus();
            }
            else if(inputUnit.equals(units[0]))
            {
                Toast.makeText(lengthActivity.this,"Please select a unit from the menu!",Toast.LENGTH_SHORT).show();
                inputDropdown.requestFocus();
            }
            else if( outputUnit.equals(units[0]))
            {
                Toast.makeText(lengthActivity.this,"Please select a unit from the menu!",Toast.LENGTH_SHORT).show();
                outputDropdown.requestFocus();
            }
            else{
                Toast.makeText(lengthActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                inputLength= Double.parseDouble(inputValue);
                outputLength = ob.getConversion(inputLength,inputUnit,outputUnit);
                if(outputLength==1.0 || outputLength-(int)(outputLength)==0)
                    outputMessage.setText((input.getText().toString() + " " + inputUnit + "  =  "+outputLength +" "+ outputUnit));
                else
                    outputMessage.setText((input.getText().toString() + " " + inputUnit + "  =  "+String.format("%.4f",outputLength) +" "+ outputUnit));
             }
        });

    }
}

class ConvertLength {

    double getConversion(double input, String inputUnit, String outputUnit) {
        double output = 0;
        if (inputUnit.equals(outputUnit))
            output = input;
        else {
            if (inputUnit.equals("Meter")) {
//                {"Select a unit","meter","KM","mile","yard","cm","inches","feet"};
                switch (outputUnit) {
                    case "KiloMeter":
                        output = input / 1000;
                        break;
                    case "Mile":
                        output = meterToMile(input);
                        break;
                    case "Yard":
                        output = meterToYard(input);
                        break;
                    case "CentiMeter":
                        output = input * 100;
                        break;
                    case "Inch":
                        output = meterToInches(input);
                        break;
                    case "Feet":
                        output = meterToFeet(input);
                        break;
                }
            } else {
                switch (inputUnit) {
                    case "KiloMeter":
                        output = input / 1000;
                        break;
                    case "Mile":
                        output = mileToMeter(input);
                        break;
                    case "Yard":
                        output = yardToMeter(input);
                        break;
                    case "CentiMeter":
                        output = input/100;
                        break;
                    case "Inch":
                        output = inchToMeter(input);
                        break;
                    case "Feet":
                        output = feetToMeter(input);
                        break;
                }
                output = getConversion(output,"Meter",outputUnit);
            }
        }
        return output;
    }

    double mileToMeter(double value) {
        return 1609.34 * value;
    }

    double meterToMile(double value) {
        return 0.000621371 * value;
    }

    double yardToMeter(double value) {
        return .9411 * value;
    }

    double meterToYard(double value) {
        return 1.0936 * value;
    }

    double inchToMeter(double value) {
        return .0254 * value;
    }

    double meterToInches(double value) {
        return value * 39.3701;
    }

    double feetToMeter(double value) {
        return .3048 * value;
    }

    double meterToFeet(double value) {
        return value * 3.281;
    }
}