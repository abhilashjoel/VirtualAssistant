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
        Log.e("Action Handler", "Small Talk Handler");
        try {
            String speech = res.getJSONObject("metadata").getString("speech");
            ResponseHandler_AI.TextResponse(speech);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
