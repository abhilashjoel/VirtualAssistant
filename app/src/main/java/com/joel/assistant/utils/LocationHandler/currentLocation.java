package com.joel.assistant.utils.LocationHandler;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joel.assistant.Views.Fragmants.Fragment_Maps;
import com.joel.assistant.utils.MapUtils.SimpleLocationListener;
import com.joel.assistant.utils.StateProvider;

import java.util.ArrayList;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by Joel on 01-05-2016.
 */
public class currentLocation implements Fragment_Maps.mapsUpdater {
    public static String Tag = "Current Location Handler";

    LocationListener gpsListener;
    LocationListener networkListener;

    @Override
    public void update(final GoogleMap googleMap) {

        final LocationManager lManager = (LocationManager) StateProvider.getActivity().getSystemService(Context.LOCATION_SERVICE);
        final MarkerOptions mo = new MarkerOptions();
        final Marker marker;

        if (checkSelfPermission(StateProvider.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(StateProvider.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location l = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (l != null)
            mo.position(new LatLng(l.getLatitude(), l.getLongitude()));
        else
            mo.position(new LatLng(10, 10));
        mo.title("You're Here..");
        marker = googleMap.addMarker(mo);

//        final LocationListener
        networkListener = new SimpleLocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                super.onLocationChanged(location);
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                marker.setPosition(ll);
            }
        };
//        LocationListener
        gpsListener = new SimpleLocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                super.onLocationChanged(location);
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                marker.setPosition(ll);
                if (checkSelfPermission(StateProvider.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(StateProvider.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                lManager.removeUpdates(networkListener);
            }
        };

        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsListener);
        lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, networkListener);
    }

    @Override
    public ArrayList<LocationListener> getListeners() {
        ArrayList<LocationListener> listenerList = new ArrayList<LocationListener>();
        listenerList.add(gpsListener);
        listenerList.add(networkListener);

        return listenerList;
    }


}


