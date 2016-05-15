package com.joel.assistant.Views.Fragmants;


import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.joel.assistant.R;

import java.util.ArrayList;
import java.util.Iterator;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Maps extends Fragment implements OnMapReadyCallback {

    View root;
    //    GoogleMap googleMap;
    mapsUpdater mUpdater;

//    @Bind (R.id.Response_Fragment_Maps)
//    GoogleMap googleMap;


    MapFragment mapFragment;
    //    MapFragment mapFragment;
    GoogleMap googleMap;

    public Fragment_Maps() {
        // Required empty public constructor
    }

    public static Fragment_Maps getInstance(mapsUpdater mapsUpdater) {
        Fragment_Maps fMaps = new Fragment_Maps();
        fMaps.mUpdater = mapsUpdater;
        return fMaps;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        mapFragment = (MapFragment) fm.findFragmentById(R.id.Maps_BaseView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_fragment__maps, container, false);


//        ButterKnife.bind(this,root);
//        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.Response_Fragment_Maps);
//        mapFragment.getMapAsync(this);

/*        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Maps_BaseView);
        mapFragment.getMapAsync(this);
*/

        return root;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if (mUpdater == null)
            return;

        mUpdater.update(googleMap);
    }

    public interface mapsUpdater {
        public void update(GoogleMap googleMap);

        public ArrayList<LocationListener> getListeners();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mUpdater == null)
            return;

        ArrayList<LocationListener> listenerList = mUpdater.getListeners();
        Iterator<LocationListener> i = listenerList.iterator();
        LocationManager lm = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        while (i.hasNext()) {
            if (checkSelfPermission(this.getActivity().getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(this.getActivity().getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.removeUpdates(i.next());
        }
    }
}
