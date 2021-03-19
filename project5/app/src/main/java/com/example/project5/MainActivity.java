package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    SensorManager manager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor == null) {
            finish();
        }
        manager.registerListener(sensorListener, sensor, 2 * 1000 * 1000);
    }

    public int sensorValToInt(float val, float range){
        int ret = 127;
        ret += (int)(val / range * 127);
        return ret;
    }

    SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float range = sensor.getMaximumRange();
            //System.out.println(sensor.getMaximumRange());
            //System.out.println(Arrays.toString(sensorEvent.values));
            getWindow().getDecorView().setBackgroundColor(Color.rgb(sensorValToInt(sensorEvent.values[0], 9.81f),sensorValToInt(sensorEvent.values[1], 9.81f), sensorValToInt(sensorEvent.values[2], 9.81f)));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
}