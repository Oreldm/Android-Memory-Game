package com.example.h1z1.memorygame.Activities;


import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.example.h1z1.memorygame.R;

public class MyService extends Service implements SensorEventListener {
    public SensorServiceBinder binder = new SensorServiceBinder();
    private Sensor rotationSens;
    private SensorManager sensManager;
    private Handler serviceHandler;
    private static final int delayForSensor = 100000;
    private boolean isFirstTime = true;
    private boolean isSameAsFirstRotation = true;
    private float y;
    private float z;
    private float x;
    private float[] worldArr = new float[3];
    private HandlerThread threadSens;
    private boolean listenerBool = false;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeSensor();
        initializeThread();
    }

    private void initializeSensor() {
        sensManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
        rotationSens = sensManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensManager.registerListener(this, rotationSens, delayForSensor);
    }

    private void initializeThread() {
        threadSens = new HandlerThread(MyService.class.getSimpleName());
        threadSens.start();
        serviceHandler = new Handler(threadSens.getLooper());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            sensManager.unregisterListener(this);
        } catch (Exception e) {

        }
        try {
            threadSens.quit();
        } catch (Exception e) {
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        binder.currentService = this;
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        try {
            sensManager.unregisterListener(this);
            sensManager = null;
        } catch (NullPointerException e) {
        }
        return super.onUnbind(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        if (e.sensor != rotationSens)
            return;

        float[] rotationArr = new float[9], newRotationArr = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationArr, e.values);
        SensorManager.remapCoordinateSystem(rotationArr, SensorManager.AXIS_X, SensorManager.AXIS_Z, newRotationArr);
        SensorManager.getOrientation(newRotationArr, worldArr);
        boolean isRotated = true;
        if (isFirstTime) {
            this.x = worldArr[0];
            this.y = worldArr[1];
            this.z = worldArr[2];
            isFirstTime = false;
        } else if (isChangeInDirection(x, worldArr[0]) || isChangeInDirection(y, worldArr[1]) || isChangeInDirection(z, worldArr[2]))
            isRotated = false;
        if (isRotated != isSameAsFirstRotation) {
            isSameAsFirstRotation = isRotated;
            Intent intent = new Intent(getString(R.string.change_str));
            intent.putExtra(getString(R.string.right_str), isRotated);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    private boolean isChangeInDirection(float oldDirection, float newDirection) {
        if (Math.abs(oldDirection - newDirection) > 0.5)
            return true;
        return false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    class SensorServiceBinder extends Binder {
        private MyService currentService;
        void notifyService(String msg) {
            if (msg != getString(R.string.listen_message) || listenerBool) return;
            Sensor s = sensManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            if (s == null) return;
            sensManager.registerListener(currentService, s, SensorManager.SENSOR_DELAY_UI, serviceHandler);
            listenerBool = true;
        }
    }
}