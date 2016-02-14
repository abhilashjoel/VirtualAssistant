package com.joel.assistant.Views.Fragmants;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joel.assistant.R;
import com.joel.assistant.utils.FragmentWithType;


public class Response_Map extends FragmentWithType {


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_response__map, container, false);
    }


     @Override
    public Boolean isText() {
        return null;
    }

    @Override
    public Boolean isMap() {
        return null;
    }

    @Override
    public Boolean isWeb() {
        return null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
