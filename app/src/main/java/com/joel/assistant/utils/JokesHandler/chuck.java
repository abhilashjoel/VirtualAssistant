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
public class chuck implements ActionHandler {

    static Random rand = new Random();

    @Override
    public void performAction(JSONObject res) {

        Context context = StateProvider.getContext();
        InputStream ins = context.getResources().openRawResource(
                context.getResources().getIdentifier("chuck",
                        "raw", context.getPackageName()));

        String Joke = "Chuck Norris once shot down a German fighter plane with his finger. By yelling \"Bang!\"";
        BufferedReader ip = new BufferedReader(new InputStreamReader(ins));
        try {
            JSONArray jokes = new JSONArray(ip.readLine());

//            rand.
            int n = rand.nextInt(jokes.length());
            Joke = ((JSONObject) jokes.get(n)).getString("joke");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseHandler_AI.TextResponse(Joke);

    }
}
