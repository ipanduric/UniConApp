package com.zadaca.ipand.unicon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ipand on 28.3.2017..
 */

public class ResultActivity extends AppCompatActivity {

    public static final String KEY_INPUT_UNIT = "input_unit";
    public static final String KEY_OUTPUT_UNIT = "output_unit";
    public static final String KEY_INPUT_VALUE = "input_value";
    public static final String KEY_OUTPUT_VALUE = "output_value";


    TextView tvInputValue, tvInputUnit, tvOutputValue, tvOutputUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        SetUpUI();
    }

    private void SetUpUI() {

        tvInputValue = (TextView) findViewById(R.id.tvInputValue);
        tvInputUnit = (TextView) findViewById(R.id.tvInputUnit);
        tvOutputValue = (TextView) findViewById(R.id.tvOutputValue);
        tvOutputUnit = (TextView) findViewById(R.id.tvOutputUnit);
        Intent intent = this.getIntent();

        if (intent.hasExtra(KEY_INPUT_UNIT)) {
            tvInputUnit.setText(intent.getStringExtra(KEY_INPUT_UNIT));
        }
        if (intent.hasExtra(KEY_INPUT_VALUE)) {
            tvInputValue.setText(intent.getStringExtra(KEY_INPUT_VALUE));
        }
        if (intent.hasExtra(KEY_OUTPUT_UNIT)) {
            tvOutputUnit.setText(intent.getStringExtra(KEY_OUTPUT_UNIT));
        }
        if (intent.hasExtra(KEY_OUTPUT_VALUE)) {
            tvOutputValue.setText(intent.getStringExtra(KEY_OUTPUT_VALUE));
        }


    }
}
