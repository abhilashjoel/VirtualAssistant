package com.joel.assistant.utils.MapUtils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.joel.assistant.utils.StateProvider;

/**
 * Created by Joel on 01-05-2016.
 */
public abstract class SimpleLocationListener implements LocationListener {
    public static String Tag = "SimpleLocationListener";
    public int count = 0;

    @Override
    public void onLocationChanged(Location location) {
        count++;
        Log.i(Tag, "Updated Location(" + count + "):  " + location.getLatitude() + ", " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(Tag, "Status changed to " + status + "  Provider :  " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i(Tag, "Provider Enabled -  " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i(Tag, "Provider Disabled -  " + provider);
    }

    public void removeListener() {
        LocationManager lm = (LocationManager) StateProvider.getContext().getSystemService(Context.LOCATION_SERVICE);
//        if (StateProvider.getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && StateProvider.getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
        lm.removeUpdates(this);
    }
}
