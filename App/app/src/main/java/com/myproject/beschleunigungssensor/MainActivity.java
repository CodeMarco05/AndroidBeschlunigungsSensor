package com.myproject.beschleunigungssensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_x, tv_y, tv_z, tv_maxX, tv_maxY, tv_maxZ;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private double maxX, maxZ, maxY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tv_x = findViewById(R.id.tv_x);
        tv_y = findViewById(R.id.tv_y);
        tv_z = findViewById(R.id.tv_z);

        tv_maxX = findViewById(R.id.tv_maxX);
        tv_maxY = findViewById(R.id.tv_maxY);
        tv_maxZ = findViewById(R.id.tv_maxZ);


        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        double x = sensorEvent.values[0];
        double y = sensorEvent.values[1];
        double z = sensorEvent.values[2];

        tv_x.setText("X: " + x);
        tv_y.setText("Y: " + y);
        tv_z.setText("Z: " + z);

        if(x > maxX){
            maxX = x;
            tv_maxX.setText("Max X: " + maxX);
        }
        if(y > maxY){
            maxY = y;
            tv_maxY.setText("Max Y: " + maxY);
        }
        if(z > maxZ){
            maxZ = z;
            tv_maxZ.setText("Max Z: " + maxZ);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}