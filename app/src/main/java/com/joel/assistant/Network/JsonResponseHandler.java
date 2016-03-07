package com.joel.assistant.Network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Joel on 07-03-2016.
 */
public abstract class JsonResponseHandler {
    static String tag = "HTTP Response";

    public void onSuccess(int statusCode, JSONObject json_obj){
        Log.i(tag,"HTTP Request was Successful");
        Log.i(tag,"Status Code - "+statusCode);
    }

    public void onSuccess(int statusCode, JSONArray json_arr){
        Log.i(tag,"HTTP Request was Successful");
        Log.i(tag,"Status Code - "+statusCode);
    }

    public void onFailure(int statusCode){
        Log.i(tag,"HTTP Request FAILED...");
        Log.i(tag,"Status Code - "+statusCode);
    }

}
