package com.joel.assistant.utils.WolframAPI;

import android.location.Location;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandlerFactory;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 5/11/2016.
 */
public class currentWeather implements ActionHandler {
    public static currentWeather cWeather= new currentWeather();

    private currentWeather(){

    }

    @Override
    public void performAction(JSONObject res) {
        JSONObject j = new JSONObject();
        Location l = StateProvider.getLocation();
        try {
            j.put("ResolvedQuery","Weather in latitude "+ l.getLatitude()+", longitude "+l.getLongitude());
            ActionHandlerFactory.getWolframHandler().performAction(j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static currentWeather getInstance() {
        return cWeather;
    }
}
