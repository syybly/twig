package com.rmit.twig.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.view.Activity_CreateEvent;
import com.rmit.twig.view.Activity_CreateGenralPost;
import com.rmit.twig.view.Activity_CreateOppo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Service_Location extends Service implements LocationListener {
    private String type;
    public Service_Location() {
    }


    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        type=intent.getStringExtra("type");
        return START_NOT_STICKY;
    }

    @SuppressWarnings("MissingPermission")
    @Override
    public void onCreate(){
        super.onCreate();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);
        locationManager.requestSingleUpdate(provider, this, Looper.getMainLooper());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0) {
            String ad=addresses.get(0).getLocality()+", "+addresses.get(0).getAdminArea()+", "+addresses.get(0).getCountryName();
            if(type.equals("general")) {
                Activity_CreateGenralPost.location.setText(ad);
                DataHolder.newpost.setLocation(ad);
            }
            if(type.equals("oppo")){
                Activity_CreateOppo.location.setText(ad);
            }
            if(type.equals("event")){
                Activity_CreateEvent.location.setText(ad);
            }
        }
        stopSelf();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
