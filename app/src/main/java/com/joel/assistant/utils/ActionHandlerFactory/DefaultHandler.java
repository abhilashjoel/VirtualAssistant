package com.joel.assistant.utils.ActionHandlerFactory;

import android.util.Log;

import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.ResponseHandler_AI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 13-03-2016.
 */
public class DefaultHandler implements ActionHandler {
    @Override
    public void performAction(JSONObject res) {

        Log.e("Action Handler", "Default Handler Handler");
        String speech = "Try another query";
        try {

            if (res.has("speech"))
                speech = res.getString("speech");
            else if (res.has("metadata") == true) {
                if (res.getJSONObject("metadata").has("speech") == true)
                    speech = res.getJSONObject("metadata").getString("speech");
            } else if (res.has("fulfillment") == true)
                if (res.getJSONObject("fulfillment").has("speech") == true)
                    speech = res.getJSONObject("fulfillment").getString("speech");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ResponseHandler_AI.TextResponse(speech);
    }
}
