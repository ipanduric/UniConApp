package com.zadaca.ipand.unicon;

import android.content.Intent;
import android.os.Bundle;
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

public class DistanceActivity extends AppCompatActivity implements View.OnClickListener {



    Spinner sDistance1, sDistance2;
    EditText etDistance;
    Button bDistanceConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        setUpUI();
    }

    private void setUpUI() {
        sDistance1 = (Spinner) findViewById(R.id.sDistance1);
        sDistance2 = (Spinner) findViewById(R.id.sDistance2);
        etDistance = (EditText) findViewById(R.id.etDistance);
        bDistanceConvert = (Button) findViewById(R.id.bDistanceConvert);
        bDistanceConvert.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Distance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDistance1.setAdapter(adapter);
        sDistance2.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        String inUnit, outUnit, textValue;
        inUnit = sDistance1.getSelectedItem().toString();
        outUnit = sDistance2.getSelectedItem().toString();
        String etValue = etDistance.getText().toString();
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
            case "Meter":
                if (outUnit.equals("Meter"))
                    outValue = numberValue;
                if (outUnit.equals("Kilometer"))
                    outValue = numberValue / 1000;
                if (outUnit.equals("Inch"))
                    outValue = numberValue * 39.37;
                break;
            case "Kilometer":
                if (outUnit.equals("Meter"))
                    outValue = numberValue * 1000;
                if (outUnit.equals("Kilometer"))
                    outValue = numberValue;
                if (outUnit.equals("Inch"))
                    outValue = numberValue * 39370;
                break;
            case "Inch":
                if (outUnit.equals("Meter"))
                    outValue = numberValue / 39.37;
                if (outUnit.equals("Kilometer"))
                    outValue = numberValue / 39370;
                if (outUnit.equals("Inch"))
                    outValue = numberValue;
                break;
        }
        return outValue;
    }
}
