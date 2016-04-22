package com.joel.assistant.utils.ActionHandlerFactory;

import android.util.Log;

import com.joel.assistant.utils.ResponseHandler_AI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 13-03-2016.
 */
public class SmallTalkHandler implements ActionHandler {
    @Override
    public void performAction(JSONObject res) {
        String Speech_TAG = "speech";
        Log.e("Action Handler", "Small Talk Handler");
        try {
            String speech = "";

            if (res.has("metadata") == true) {
                if (res.getJSONObject("metadata").has(Speech_TAG) == true)
                    speech = res.getJSONObject("metadata").getString(Speech_TAG);
            }

            if (speech.isEmpty() == true && res.has("fulfillment") == true)
                if (res.getJSONObject("fulfillment").has(Speech_TAG) == true)
                    speech = res.getJSONObject("fulfillment").getString(Speech_TAG);

            ResponseHandler_AI.TextResponse(speech);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
