package com.joel.assistant.utils.ActionHandlerFactory;

import android.util.Log;

import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.ResponseHandler_AI;

import java.lang.Throwable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 13-03-2016.
 */
public class DefaultHandler implements ActionHandler {
    @Override
    public void performAction(JSONObject res) {
//        new Exception().printStackTrace();

        new Throwable().printStackTrace();
        Log.e("Action Handler", "Default Handler Handler");
        String Speech_TAG = "speech";
        String speech = "Try another query";
        System.out.println(res.toString());
        try {

            if (res.has(Speech_TAG) == true)
                speech = res.getString(Speech_TAG);
            else if (res.has("metadata") == true) {
                if (res.getJSONObject("metadata").has(Speech_TAG) == true)
                    speech = res.getJSONObject("metadata").getString(Speech_TAG);
            } else if (res.has("fulfillment") == true)
                if (res.getJSONObject("fulfillment").has(Speech_TAG) == true)
                    speech = res.getJSONObject("fulfillment").getString(Speech_TAG);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.print(speech);

        ResponseHandler_AI.TextResponse(speech);
    }
}
