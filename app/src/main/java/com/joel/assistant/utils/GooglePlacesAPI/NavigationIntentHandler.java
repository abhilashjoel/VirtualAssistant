package com.joel.assistant.utils.GooglePlacesAPI;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.util.Log;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 05-05-2016.
 */
public class NavigationIntentHandler implements ActionHandler {
    public static String Tag = "GooglePlaces Navigation Intent";

    @Override
    public void performAction(JSONObject res) {
        String searchQuery;

        try {
            JSONObject param = (JSONObject) res.get("parameters");
            searchQuery = param.getString("place");
            Log.i(Tag, "Search Query :  " + searchQuery);
            ResponseHandler_AI.TextResponse("Looking for directions to " + searchQuery);

            Uri uri;

            uri = Uri.parse("google.navigation:q=" + Uri.encode(searchQuery));
            Log.i(Tag, "Intent URi :  " + uri.toString());

            Intent mapSearchIntent = new Intent(Intent.ACTION_VIEW, uri);
            mapSearchIntent.setPackage("com.google.android.apps.maps");
            StateProvider.getActivity().startActivity(mapSearchIntent);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
