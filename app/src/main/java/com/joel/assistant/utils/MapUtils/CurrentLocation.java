package com.joel.assistant.utils.MapUtils;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;
import com.joel.assistant.utils.StateProvider;

/**
 * Created by Joel on 01-05-2016.
 */
public class CurrentLocation {

    public static LatLng getCurrentLocation(){
        LatLng ll = new LatLng(1,1);

//        LocationManager lm = StateProvider.getActivity().getSystemService(Context.LOCATION_SERVICE);


        return ll;
    }
}
