package com.joel.assistant.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.barcode.Barcode;
import com.joel.assistant.utils.MapUtils.SimpleLocationListener;

import java.net.ContentHandler;

/**
 * Created by Joel on 13-03-2016.
 */
public class StateProvider {

    public static String Tag = "State Provider";
    static Context context;
    static Activity activity;
    public static int ResponseFragmentHeight;
    public static Location location;
    public static Location nwLoc;

    public static Context getContext() {
        if (context == null)
            Log.e("State Provider", "Context is not initialized");
        return context;
    }

    static public void setContext(Context ct) {
        context = ct;
    }

    public static Activity getActivity() {
        if (context == null)
            Log.e("State Provider", "Activity is not initialized");
        return activity;
    }

    static public void setActivity(Activity a) {
        activity = a;
    }

    public static Location getLocation() {
        if (location != null)
            return location;
        else if (nwLoc != null)
            return nwLoc;

        Location l = new Location("");
        l.setLatitude(12.92935297);
        l.setLongitude(77.59702467);
        return l;
    }

    public static void updateLocation() {
        LocationListener ll = new com.joel.assistant.utils.MapUtils.SimpleLocationListener() {
            @Override
            public void onLocationChanged(android.location.Location loc) {
                super.onLocationChanged(location);
                Log.i(Tag, "co : " + super.count);
                if (super.count > 5)
                    removeListener();
                location = loc;
                Log.i(Tag, "Location Update :  " + loc.getLatitude() + " , " + loc.getLongitude());
            }
        };
        LocationListener nl = new SimpleLocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                super.onLocationChanged(location);
                Log.i(Tag, "co : " + super.count);
                if (super.count > 5)
                    removeListener();
                nwLoc = location;
            }
        };

        LocationManager lm = (LocationManager) StateProvider.getActivity().getSystemService(Context.LOCATION_SERVICE);
//        if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        //          Log.i("Location Permision Denied","Couldn't update the current location because permission was denied");
        //        return;
        //  }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, nl);
    }

}
