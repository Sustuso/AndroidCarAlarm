package com.sustuso.caralarm;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class LocationServiceClass  implements LocationListener {

        private LocationManager locationManager;
        private String latitude;
        private String longitude;
        private Criteria criteria;
        private String provider;

        @SuppressLint("MissingPermission")
        public LocationServiceClass(Context context) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            provider = locationManager.getBestProvider(criteria, true);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,0, this);

            setMostRecentLocation(locationManager.getLastKnownLocation(provider));

        }

        private void setMostRecentLocation(Location lastKnownLocation) {

        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.location.LocationListener#onLocationChanged(android.location.
         * Location)
         */
        @Override
        public void onLocationChanged(Location location) {
            double lon = (double) (location.getLongitude());/// * 1E6);
            double lat = (double) (location.getLatitude());// * 1E6);

//      int lontitue = (int) lon;
//      int latitute = (int) lat;
            latitude = lat + "";
            longitude = lon + "";

        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.location.LocationListener#onProviderDisabled(java.lang.String)
         */
        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.location.LocationListener#onProviderEnabled(java.lang.String)
         */
        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         *
         * @see android.location.LocationListener#onStatusChanged(java.lang.String,
         * int, android.os.Bundle)
         */
        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

    }
