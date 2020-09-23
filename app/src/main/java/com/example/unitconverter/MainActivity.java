//***********************************************************
//  MainActivity.java
//
//  Dustin Kaban
//  Student ID: T00663749
//  September 22nd, 2020
//
//  This class handles the main functionality of our Conversion App
//***********************************************************

package com.example.unitconverter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //Variables of the UI objects in the scene
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText entryValueText;
    private TextView resultText;
    //Strings so we can read the information of the spinner
    private String fromSpinnerString;
    private String toSpinnerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign the objects in the scene accordingly
        fromSpinner = (Spinner)findViewById(R.id.spinner_from);
        toSpinner = (Spinner)findViewById(R.id.spinner_to);
        entryValueText = (EditText) findViewById(R.id.editText_value);
        resultText = (TextView)findViewById(R.id.text_result);
    }

    //This method is called when the Convert button is pressed
    /** Called when the user pushes the Convert button */
    public void convertValue(View view)
    {
        //We want to get the updated selections from the Spinners
        fromSpinnerString = fromSpinner.getSelectedItem().toString();
        toSpinnerString = toSpinner.getSelectedItem().toString();

        //We want to throw an exception if we can't parse the string as a double
        try
        {
            double valueToConvert = Double.parseDouble(entryValueText.getText().toString());
            //We want to verify that a conversion actually takes place
            if(fromSpinnerString.equals(toSpinnerString))
            {
                Toast.makeText(getApplicationContext(),"No Conversion Needed",Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(toSpinnerString.equals("Celsius"))
                {
                    valueToConvert = convertToCelsius(valueToConvert);
                }
                else
                {
                    valueToConvert = convertToFahrenheit(valueToConvert);
                }

                resultText.setText(String.format("%.2f",valueToConvert));
            }
        }
        catch(Exception e)
        {
            //Incorrect value entered, need a number.
            Toast.makeText(getApplicationContext(),"Please Enter a Value",Toast.LENGTH_SHORT).show();
        }
    }

    //Converting from Celsius to Fahrenheit
    public double convertToFahrenheit(double value)
    {
        return ((value * 9/5) + 32);
    }

    //Converting from Fahrenheit to Celsius
    public double convertToCelsius(double value)
    {
        return ((value -32) * 5/9);
    }
}