package com.zadaca.ipand.unicon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.zadaca.ipand.unicon.ResultActivity.KEY_INPUT_UNIT;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_INPUT_VALUE;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_OUTPUT_UNIT;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_OUTPUT_VALUE;

/**
 * Created by ipand on 28.3.2017..
 */

public class WeightActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner sWeight1, sWeight2;
    EditText etWeight;
    Button bWeightConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        setUpUI();
    }

    private void setUpUI() {
        sWeight1 = (Spinner) findViewById(R.id.sWeight1);
        sWeight2 = (Spinner) findViewById(R.id.sWeight2);
        etWeight = (EditText) findViewById(R.id.etWeight);
        bWeightConverter = (Button) findViewById(R.id.bWeightConvert);
        bWeightConverter.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Weight, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sWeight1.setAdapter(adapter);
        sWeight2.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        String inUnit, outUnit, textValue;
        inUnit = sWeight1.getSelectedItem().toString();
        outUnit = sWeight2.getSelectedItem().toString();
        String etValue = etWeight.getText().toString();
        Double value = Convert(inUnit, outUnit, etValue);
        textValue = value.toString();
        Intent explicitIntent = new Intent(getApplicationContext(),ResultActivity.class);
        explicitIntent.putExtra(KEY_INPUT_UNIT, inUnit);
        explicitIntent.putExtra(KEY_OUTPUT_UNIT, outUnit);
        explicitIntent.putExtra(KEY_INPUT_VALUE, etValue);
        explicitIntent.putExtra(KEY_OUTPUT_VALUE, textValue);
        this.startActivity(explicitIntent);
    }

    private double Convert(String inUnit, String outUnit, String inValue) {
        double outValue = 0;
        double numberValue;
        numberValue = Double.parseDouble(inValue);

        switch (inUnit) {
            case "Gram":
                if (outUnit.equals("Gram"))
                    outValue = numberValue;
                if (outUnit.equals("Kilogram"))
                    outValue = numberValue / 1000;
                if (outUnit.equals("Pound"))
                    outValue = numberValue * 0.0022046;
                break;
            case "Kilogram":
                if (outUnit.equals("Gram"))
                    outValue = numberValue * 1000;
                if (outUnit.equals("Kilogram"))
                    outValue = numberValue;
                if (outUnit.equals("Pound"))
                    outValue = numberValue * 2.2046;
                break;
            case "Pound":
                if (outUnit.equals("Gram"))
                    outValue = numberValue / 0.0022046 ;
                if (outUnit.equals("Kilogram"))
                    outValue = numberValue / 2.2046;
                if (outUnit.equals("Pound"))
                    outValue = numberValue;
                break;
        }
        return outValue;
    }
}


