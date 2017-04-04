package com.zadaca.ipand.unicon;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;

import static com.zadaca.ipand.unicon.ResultActivity.KEY_INPUT_UNIT;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_INPUT_VALUE;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_OUTPUT_UNIT;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_OUTPUT_VALUE;

/**
 * Created by ipand on 28.3.2017..
 */

public class TemperatureActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner sTemp1, sTemp2;
    EditText etTemp;
    Button bTempConvert;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        setUpUI();
    }

    private void setUpUI()
    {
        sTemp1 = (Spinner) findViewById(R.id.sTemp1);
        sTemp2 = (Spinner) findViewById(R.id.sTemp2);
        etTemp = (EditText) findViewById(R.id.etTemp);
        bTempConvert = (Button) findViewById(R.id.bTempConvert);
        bTempConvert.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.Temperature, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTemp1.setAdapter(adapter);
        sTemp2.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        String inUnit, outUnit, textValue;
        inUnit = sTemp1.getSelectedItem().toString();
        outUnit= sTemp2.getSelectedItem().toString();
        String etValue = etTemp.getText().toString();
        Double value = Convert(inUnit, outUnit, etValue);
        textValue = value.toString();
        Intent explicitIntent = new Intent(getApplicationContext(),ResultActivity.class);
        explicitIntent.putExtra(KEY_INPUT_UNIT, inUnit);
        explicitIntent.putExtra(KEY_OUTPUT_UNIT, outUnit);
        explicitIntent.putExtra(KEY_INPUT_VALUE, etValue);
        explicitIntent.putExtra(KEY_OUTPUT_VALUE, textValue);
        this.startActivity(explicitIntent);
    }

    private double Convert (String inUnit, String outUnit, String inValue)
    {
        double outValue = 0;
        double numberValue;
        numberValue = Double.parseDouble(inValue);

        switch (inUnit) {
            case "Celsius":
                if (outUnit.equals("Celsius"))
                    outValue = numberValue;
                if (outUnit.equals("Kelvin"))
                    outValue = numberValue + 273.15;
                if (outUnit.equals("Fahrenheit"))
                    outValue = (numberValue*9/5) + 32;
                break;
            case "Kelvin":
                if (outUnit.equals("Celsius"))
                    outValue = numberValue - 273.15;
                if (outUnit.equals("Kelvin"))
                    outValue = numberValue;
                if (outUnit.equals("Fahrenheit"))
                    outValue = (numberValue*9/5) - 459.67;
                break;
            case "Fahrenheit":
                if (outUnit.equals("Celsius"))
                    outValue = (numberValue - 32)*(5/9);
                if (outUnit.equals("Kelvin"))
                    outValue = (numberValue + 459.67)*(5/9);
                if (outUnit.equals("Fahrenheit"))
                    outValue = numberValue;
                break;
        }
        return outValue;
    }
}
