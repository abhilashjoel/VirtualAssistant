package com.joel.assistant.utils.ActionHandlerFactory;

import android.util.Log;

import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.RandomGenerator;
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

        new Throwable().printStackTrace();
        Log.e("Action Handler", "Default Handler Handler");
        String Speech_TAG = "speech";
        String speech = "";
        System.out.println(res.toString());
        try {

            if (res.has(Speech_TAG) == true)
                speech = res.getString(Speech_TAG);

            if (speech.isEmpty() == true && res.has("metadata") == true) {
                if (res.getJSONObject("metadata").has(Speech_TAG) == true)
                    speech = res.getJSONObject("metadata").getString(Speech_TAG);
            }

            if (speech.isEmpty() == true && res.has("fulfillment") == true)
                if (res.getJSONObject("fulfillment").has(Speech_TAG) == true)
                    speech = res.getJSONObject("fulfillment").getString(Speech_TAG);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(speech.isEmpty() == true)
            speech = Response_Stubs[RandomGenerator.get(1)];
        System.out.print(speech);

        ResponseHandler_AI.TextResponse(speech);
    }

    public static String[] Response_Stubs = new String[]{
            "Sorry, I don't know how to respond to it.",
            "I will learn that now."};
}
