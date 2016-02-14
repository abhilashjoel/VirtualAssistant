package com.joel.assistant.Views.Fragmants;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joel.assistant.R;
import com.joel.assistant.utils.FragmentWithType;


public class Response_Map extends FragmentWithType implements OnMapReadyCallback {

    private GoogleMap mMap;
    View root;


    public static Response_Map newInstance(String param1, String param2) {
        Response_Map fragment = new Response_Map();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Response_Map() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_response__map, container, false);


        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.Map_Response);
        mapFragment.getMapAsync(this);
        return root;
    }

    /*

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




     */

     @Override
    public Boolean isText() {
        return false;
    }

    @Override
    public Boolean isMap() {
        return true;
    }

    @Override
    public Boolean isWeb() {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
