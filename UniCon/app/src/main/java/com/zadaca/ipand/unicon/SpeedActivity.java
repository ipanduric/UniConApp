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

import static com.zadaca.ipand.unicon.R.id.sDistance1;
import static com.zadaca.ipand.unicon.R.id.sDistance2;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_INPUT_UNIT;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_INPUT_VALUE;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_OUTPUT_UNIT;
import static com.zadaca.ipand.unicon.ResultActivity.KEY_OUTPUT_VALUE;

/**
 * Created by ipand on 28.3.2017..
 */

public class SpeedActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner sSpeed1, sSpeed2;
    EditText etSpeed;
    Button bSpeedConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);
        setUpUI();
    }

    private void setUpUI() {
        sSpeed1 = (Spinner) findViewById(R.id.sSpeed1);
        sSpeed2 = (Spinner) findViewById(R.id.sSpeed2);
        etSpeed = (EditText) findViewById(R.id.etSpeed);
        bSpeedConverter = (Button) findViewById(R.id.bSpeedConvert);
        bSpeedConverter.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Speed, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSpeed1.setAdapter(adapter);
        sSpeed2.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        String inUnit, outUnit, textValue;
        inUnit = sSpeed1.getSelectedItem().toString();
        outUnit = sSpeed2.getSelectedItem().toString();
        String etValue = etSpeed.getText().toString();
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
            case "m/s":
                if (outUnit.equals("m/s"))
                    outValue = numberValue;
                if (outUnit.equals("km/h"))
                    outValue = numberValue * 3.6;
                if (outUnit.equals("mph"))
                    outValue = numberValue * 2.2369;
                break;
            case "km/h":
                if (outUnit.equals("m/s"))
                    outValue = numberValue / 3.6;
                if (outUnit.equals("km/h"))
                    outValue = numberValue;
                if (outUnit.equals("mph"))
                    outValue = numberValue / 1.6093;
                break;
            case "mph":
                if (outUnit.equals("m/s"))
                    outValue = numberValue / 2.2369;
                if (outUnit.equals("km/h"))
                    outValue = numberValue / 1.6093;
                if (outUnit.equals("mph"))
                    outValue = numberValue;
                break;
        }
        return outValue;
    }
}
