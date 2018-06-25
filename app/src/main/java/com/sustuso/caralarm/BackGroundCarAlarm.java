package com.sustuso.caralarm;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.R;

import java.util.Calendar;


public class BackGroundCarAlarm extends IntentService {
    private DbClass DB = new DbClass();
    private Integer Number;
    private Handler handler = new Handler();
    private MotionSensorClass motionSensorClass;
    private OptionClass optionclass;
    private NotificationCompat.Builder mBuilder;
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            //Prendo i parametri dal sensore
            String msg = motionSensorClass.x + " " + motionSensorClass.y + " " + motionSensorClass.z;
            //Scrivo i dati nella tabella di log;
            DB.insertLogTable(getApplicationContext(),  Calendar.getInstance().getTime(), motionSensorClass.Alert.toString()  , msg);
            // Faccio vibrare il cell;
            vibrate();
            handler.postDelayed(this, optionclass.serverrefresh*1000);
        }
    };

    public BackGroundCarAlarm() {
        super("BackGroundCarAlarm");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
//            DB.insertOptionTable(getApplicationContext(), "Background", "12345");
//            DB.insertOptionTable(getApplicationContext(), "Test", "0984");
//            DB.insertOptionTable(getApplicationContext(), "Test1", "TTTTT");
//            final String action = intent.getAction();
//            if (ACTION_CHECKSENSOR.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionCHECKSENSOR(param1, param2);
//            }
            DB.insertLogTable(getApplicationContext(),  Calendar.getInstance().getTime(), "Start"  , "Start");
            optionclass = DB.readOptionTable(getApplicationContext());
            motionSensorClass = new MotionSensorClass(this);
            motionSensorClass.register();

            notificationicon(intent);

            handler.post(runnableCode);
        }
    }
    private void vibrate(){
        if (motionSensorClass.Alert){
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);
            motionSensorClass.init(3); // Da eliminare azzera lo stato di allarme
        }

    }
    private void notificationicon(Intent intent) {


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.sym_def_app_icon)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.sym_def_app_icon))
                .setContentTitle("titolo")
                .setContentText("messaggio")
                .setAutoCancel(true);
//                .setSound(defaultSoundUri);
//                .setContentIntent(intent);

        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
