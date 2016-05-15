package com.joel.assistant.utils.LocationHandler;

import android.util.Log;

import com.joel.assistant.Views.Activities.Response_Fragments;
import com.joel.assistant.Views.Fragmants.Fragment_Maps;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandlerFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 01-05-2016.
 */
public class LocationHandler implements ActionHandler {
    public static String Tag = "Location Handler";

    @Override
    public void performAction(JSONObject res) {
        try {
            String action = res.getString("action");

            String type = action.split("\\.")[1];
            Log.i(Tag, "Action Type :  " + type);

            Fragment_Maps.mapsUpdater updater = getUpdater(type);
            if (updater == null) {
                ActionHandlerFactory.getDefaultHandler().performAction(res);
                return;
            }

            Fragment_Maps frag = Response_Fragments.setMapFragment(updater);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Fragment_Maps.mapsUpdater getUpdater(String type) {
        if (type.equalsIgnoreCase("userlocation")) {
            return new currentLocation();
        }

        return null;
    }
}
