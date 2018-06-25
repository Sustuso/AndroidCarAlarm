package com.sustuso.caralarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;

public class BackgroundService extends Service {

    public MotionSensorClass motionSensorClass;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        motionSensorClass = new MotionSensorClass(this);
        motionSensorClass.register();

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

                if (motionSensorClass.Alert){
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(400);
                    motionSensorClass.Alert = false;
                    motionSensorClass.init(3);
                }

            }

            public void onFinish() {
            }
        }.start();

        return super.onStartCommand(intent, flags, startId);
    }

}