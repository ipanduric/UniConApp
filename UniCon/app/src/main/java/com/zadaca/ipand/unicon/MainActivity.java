package com.zadaca.ipand.unicon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivDistance;
    ImageView ivTemperature;
    ImageView ivSpeed;
    ImageView ivWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
    }

    private void setUpUI() {
        ivDistance = (ImageView) findViewById(R.id.ivDistance);
        ivTemperature = (ImageView) findViewById(R.id.ivTemperature);
        ivSpeed = (ImageView) findViewById(R.id.ivSpeed);
        ivWeight = (ImageView) findViewById(R.id.ivWeight);

        ivDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), DistanceActivity.class);
                startActivity(explicitIntent);
            }
        });

        ivTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), TemperatureActivity.class);
                startActivity(explicitIntent);
            }
        });

        ivSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), SpeedActivity.class);
                startActivity(explicitIntent);
            }
        });

        ivWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), WeightActivity.class);
                startActivity(explicitIntent);
            }
        });
    }
}