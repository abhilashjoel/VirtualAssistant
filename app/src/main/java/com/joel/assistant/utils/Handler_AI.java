package com.joel.assistant.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import com.joel.assistant.Network.AsyncHTTP;
import com.joel.assistant.Network.JsonResponseHandler;
import com.joel.assistant.Network.param;
import com.joel.assistant.R;
import com.joel.assistant.Views.Activities.MainActivity;
import com.joel.assistant.Views.Fragmants.Response_Text;

/**
 * Created by Joel on 2/13/2016.
 */
public class Handler_AI {

    static Context context;
    static Activity activity;
    static OkHttpClient oCli = new OkHttpClient();
    static String session_ID = "";



    public static void setActivity(Activity a) {
        activity = a;
    }

    public static void showToast(final String data) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), data, Toast.LENGTH_LONG).show();
            }
        });
    }


    public static void process_update(final String s){

        param req_p = buildParam_AI(s);
        String url = Constants.AI_HOST+Constants.AI_URL_SEG;

        AsyncHTTP.get(req_p, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject json_obj) {
                super.onSuccess(statusCode, json_obj);

                String text = "This is beyond me..";
                System.out.println(json_obj.toString());
                try {
                    JSONObject res = json_obj.getJSONObject("result");
                    if(res.has("speech"))
                        text = res.getString("speech");
                    else if(res.has("fulfillment"))
                        text = res.getString("fulfillment");

                } catch (Exception e) {
                    text = "Error in parsing json..";
                    e.printStackTrace();
                }

                ResponseHandler_AI.TextResponse(text,text);

            }

            @Override
            public void onFailure(int statusCode) {
                super.onFailure(statusCode);
                showToast("Error in Processing Your Query...");
                ResponseHandler_AI.TextResponse("Error in Processing Your Query...","Error in Processing Your Query...");
            }
        });


    }

    private static param buildParam_AI(String s) {

        param p = new param();

        p.setProtocol(Constants.AI_SCHEME);
        p.setHost(Constants.AI_HOST);
        p.setURLSegment(Constants.AI_URL_SEG);
/*------------------    API.AI QUERY PARAMETERS     -----------------------------------------------*/
        p.addParam("lang","en");
        p.addParam("query",s);
        p.addParam("timezone","Asia/Calcutta");
        if(session_ID.isEmpty() != true)
            p.addParam("sessionID",session_ID);


/*----------------- API.AI AUTHENTICATION HEADERS   ----------------------------------------------*/
        p.addHeader("Authorization",Constants.AI_AUTH);
        p.addHeader("ocp-apim-subscription-key",Constants.AI_SUBSCR_KEY);

        return p;
    }




}
