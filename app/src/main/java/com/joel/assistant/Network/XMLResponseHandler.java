package com.joel.assistant.Network;

import android.util.Log;

import org.w3c.dom.Element;

/**
 * Created by Joel on 29-04-2016.
 */
public abstract class XMLResponseHandler {

    public static String TAG = "XMLResponseHandler";

    public void onSuccess(int statusCode, Element root) {
        Log.i(TAG, "HTTP Request was Successful");
        Log.i(TAG, "Status Code - " + statusCode);
    }

    public void onFailure(int statusCode) {
        Log.i(TAG, "HTTP Request Failed.");
        Log.i(TAG, "Status Code - " + statusCode);
    }
}
