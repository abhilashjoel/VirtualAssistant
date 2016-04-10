package com.joel.assistant.utils.JokesHandler;

import android.content.Context;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by Joel on 10-04-2016.
 */
public class knock implements ActionHandler {
    static Random rand = new Random();

    static String who;
    static String punch;

    @Override
    public void performAction(JSONObject res) {
        try {
            String state = res.getJSONObject("parameters").getString("state");
            System.out.println("Knock : " + state);
            if (state.equalsIgnoreCase("begin"))
                begin();
            else if (state.equalsIgnoreCase("who"))
                who();
            else if (state.equalsIgnoreCase("punch")) punch();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void begin() {
        JSONArray jokes = get();
        int n = rand.nextInt(jokes.length());
        JSONObject jo;
        try {
            jo = jokes.getJSONObject(n);
            who = jo.getString("who");
            punch = jo.getString("punch");
        } catch (JSONException e) {
            System.out.println("------Caught JSON exception.....");
            who = "God";
            punch = "God-Zilla";
            e.printStackTrace();
        }

        ResponseHandler_AI.TextResponse("Knock Knock");

    }

    private JSONArray get() {

        Context context = StateProvider.getContext();
        InputStream ins = context.getResources().openRawResource(
                context.getResources().getIdentifier("knock",
                        "raw", context.getPackageName()));

        JSONArray jokes = null;
        BufferedReader ip = new BufferedReader(new InputStreamReader(ins));
        try {
            jokes = new JSONArray(ip.readLine());

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jokes;

    }

    void who() {

        ResponseHandler_AI.TextResponse(who);

    }

    void punch() {

        ResponseHandler_AI.TextResponse(punch);
    }
}
