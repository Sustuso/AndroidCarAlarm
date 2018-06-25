package com.sustuso.caralarm;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

    public class MotionSensorClass implements SensorEventListener{

        private SensorManager mSensorManager;
        private Sensor mAcc;
        private SensorEventListener listener;
        private float oldX, oldY, oldZ;
        public float minX, minY, minZ;
        public float maxX, maxY, maxZ;
        public float x = 0;
        public float y = 0;
        public float z = 0;
        public float Sen;
        private Boolean notSet;
        public Boolean Alert;



        public MotionSensorClass(Context context){
            mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            init(3);
        }
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1){
            // TODO

        }

        @Override
        public void onSensorChanged(SensorEvent arg0){
            x = arg0.values[0];
            y = arg0.values[1];
            z = arg0.values[2];

            oldX = x;
            oldY = y;
            oldZ = z;


            if (notSet){
                notSet = false;
                return;
            }
            if (x>maxX+Sen || x<minX-Sen || y>maxY+Sen || y<minY-Sen
//                    || z>maxZ+Sen || z<minZ-Sen
                    ){
                 Alert = true;
            }

            setLimit(x,1);
            setLimit(y,2);
            setLimit(z,3);

            // TODO

        }

        public void register(){
            if (mAcc != null) {
                mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

        public void unregister(){
            if (mAcc != null) {
                mSensorManager.unregisterListener(this);
            }
        }
        private void setLimit(float value, int type){
            if (type== 1) {
                if (value > maxX) {maxX = value; }

                if (value < minX) {minX = value; }
            }
            if (type==2) {
                if (value > maxY) {maxY = value; }
                if (value < minY) { minY = value; }
            }
            if (type==3) {
                if (value > maxZ) {maxZ = value; }
                if (value < minZ) { minZ = value; }
            }
        }
        public void init(float _sen){
            Sen = _sen;
            notSet = false;
            Alert = false;
            minX = 0;
            minY = 0;
            minZ = 0;
            maxX = 0;
            maxY = 0;
            maxZ = 0;
        }

    }