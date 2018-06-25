package com.sustuso.caralarm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.UUID;
import android.os.Vibrator;
//import android.R;

public class MainActivity extends AppCompatActivity {

    private DbClass DB = new DbClass();
    private OptionClass optionclass;
//    MotionSensorClass motionSensorClass;
//    List<Integer> myCoords = new ArrayList<Integer>();
    private LocationServiceClass appLocationManager;

    protected void SetGlobalVariable() {
        DB.createOptionTable(getApplicationContext());
        DB.insertOptionTable(getApplicationContext(),"apptype","NotSet");
        DB.insertOptionTable(getApplicationContext(),"guid",UUID.randomUUID().toString());
        DB.insertOptionTable(getApplicationContext(),"servicerefresh","30");
        DB.insertOptionTable(getApplicationContext(),"active","false");
        DB.insertOptionTable(getApplicationContext(),"debug","true");


//            this.goToOptionActivity(null);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.simple_spinner_item);
        DB.createLogTable(getApplicationContext());
        DB.createOptionTable(getApplicationContext());

//        SetGlobalVariable(); //Da attivare la prima volta che viene eseguita l'app


    }
    public void modifyPar(List<Integer> _list){

//        _list.set(0,100);
    }

    public void goToOptionActivity(View view) {
//          TextView TW = (TextView)findViewById(R.id.textView1);
//          optionclass = DB.readOptionTable(getApplicationContext());
//          if (optionclass != null) {
//          TextView TW = (TextView)findViewById(R.id.textView1);


//            Intent myIntent = new Intent(MainActivity.this, OptionsActivity.class);
//            myIntent.putExtra("optionclass", optionclass); //Optional parameters
//            MainActivity.this.startActivity(myIntent);

//            TextView TW = (TextView)findViewById(R.id.textView2);
//            TW.setText(motionSensorClass.mSensorAccelerometer.toString());

//            TextView TW1 = (TextView)findViewById(R.id.textView);
//            TW1.setText(motionSensorClass.mSensorGyroscope.toString());
//            SensorManager  mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

//            TextView TW1 = (TextView)findViewById(R.id.textView);
//            TW1.setText("MaxX = "+motionSensorClass.maxX+" MinX = "+motionSensorClass.minX + System.getProperty("line.separator")+
//                    "MaxY = "+motionSensorClass.maxY+" MinY = "+motionSensorClass.minY + System.getProperty("line.separator")+
//                    "MaxZ = "+motionSensorClass.maxZ+" MinZ = "+motionSensorClass.minZ + System.getProperty("line.separator"));
//
//            TextView TW2 = (TextView)findViewById(R.id.textView2);
//            TW2.setText(motionSensorClass.Alert.toString());

//            myCoords.add(10);
//            modifyPar(myCoords);

        startService(new Intent(this, BackGroundCarAlarm.class));
        }
    public void  setAlertOff (View view){
//        motionSensorClass.init(3);
//        TextView TW1 = (TextView)findViewById(R.id.textView);
//        TW1.setText(myCoords.get(0).toString());

//        Toast.makeText( this, L.getLatitude() + " " + L.getLongitude(),
//                Toast.LENGTH_LONG).show();


//        getlocation();
        getLocation();
    }

    @Override
    protected void onResume(){
        super.onResume();
//        motionSensorClass.register();
    }

    @Override
    protected void onPause(){
        super.onPause();
//        motionSensorClass.unregister();
    }
    private void notification() {
    }

    private void getlocation(){
    }

    private void locationtest(){
    }
    public void getLocation()
    {

    }

}

