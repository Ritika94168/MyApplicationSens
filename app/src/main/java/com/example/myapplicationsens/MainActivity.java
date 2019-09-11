package com.example.myapplicationsens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
Sensor sensor;
SensorManager sendorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sendorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }
    @Override
    public void onResume() {
super.onResume();
sendorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause() {
        super.onPause();
        sendorManager.unregisterListener(this);
    }
    MediaPlayer mp;
    boolean isrunning=true;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Toast.makeText(getApplicationContext(), ""+sensorEvent.values[0], Toast.LENGTH_SHORT).show();
        if(sensorEvent.values[0]>5 && isrunning==false){
            isrunning=true;
mp=new MediaPlayer();
try {
    mp.setDataSource("http://server6.mp3quran.net/thubti/001.mp3");
    mp.prepare();
    mp.start();

} catch (IOException e) {
    e.printStackTrace();
}
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
