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
        if(ret < 0)
            return 0;
        else if(ret > 255)
            return 255;
        return ret;
    }

    SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float range = 9.81f;
            int r = sensorValToInt(sensorEvent.values[0], range);
            int g = sensorValToInt(sensorEvent.values[1], range);
            int b = sensorValToInt(sensorEvent.values[2], range);
            getWindow().getDecorView().setBackgroundColor(Color.rgb(r, g, b));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
}