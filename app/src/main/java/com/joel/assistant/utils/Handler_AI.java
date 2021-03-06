package com.joel.assistant.utils;

/**
 * Created by Joel on 2/13/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

import com.joel.assistant.Network.AsyncHTTP;
import com.joel.assistant.Network.JsonResponseHandler;
import com.joel.assistant.Network.param;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandlerFactory;
import com.joel.assistant.utils.ActionHandlerFactory.DefaultHandler;

//import com.github.dvdme.ForecastIOLib.

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


    public static void process_update(final String s) {

        param req_p = buildParam_AI(s);
        String url = Constants.AI_HOST + Constants.AI_URL_SEG;

        AsyncHTTP.get(req_p, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject res_json) {
                super.onSuccess(statusCode, res_json);

                String text = "This is beyond me..";
                try {
                    session_ID = res_json.getString("id");
                    Log.i("Session ID from AI  ", session_ID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(res_json.toString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    if (res_json.has("result") != true) {
                        new DefaultHandler().performAction(res_json);
                        return;
                    }

                    JSONObject res = res_json.getJSONObject("result");
                    System.out.println("Result Object");
                    System.out.println(res.toString());
                    if (res == null)
                        System.out.print("HA NULL");

                    ActionHandler handler = ActionHandlerFactory.getHandler(res);
                    System.out.println("---------------->>" + handler.getClass().getCanonicalName());
                    if (handler == null)
                        System.out.println("----handler was null");
                    handler.performAction(res);

                } catch (Exception e) {
                    new DefaultHandler().performAction(res_json);
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode) {
                super.onFailure(statusCode);
                showToast("Error in Processing Your Query...");
                ResponseHandler_AI.TextResponse("Error in Processing Your Query...", "Error in Processing Your Query...");
            }
        });


    }

    private static void performAction(JSONObject res) {


//        ActionHandler handler = ActionHandlerFactory.getActionHandler(res);
        //       handler.performAction(res);

/*            action = res.getString("action");

            String a[] = action.split(".");

            if (action.equals("apps.open")) {

                launchApp.
//                String name = res.getJSONObject("parameters").getString("app_name");
//                Log.i("Requested App",name);
//                launchApp.launch(context, name);

            }
            else{
                if(res.has("speech"))
                    ResponseHandler_AI.TextResponse(res.getString("speech"),res.getString("speech"));
            }

*/
    }

    private static param buildParam_AI(String s) {

        param p = new param();

        p.setProtocol(Constants.AI_SCHEME);
        p.setHost(Constants.AI_HOST);
        p.setURLSegment(Constants.AI_URL_SEG);
/*------------------    API.AI QUERY PARAMETERS     -----------------------------------------------*/
        p.addParam("lang", "en");
        p.addParam("query", s);
        p.addParam("timezone", "Asia/Calcutta");
        p.addParam("v", "20150910");
        if (session_ID.isEmpty() != true) {
            p.addParam("sessionID", session_ID);
//            Log.i("Session ID Passed ", session_ID);
        }


/*----------------- API.AI AUTHENTICATION HEADERS   ----------------------------------------------*/
        p.addHeader("Authorization", Constants.AI_AUTH);
        p.addHeader("ocp-apim-subscription-key", Constants.AI_SUBSCR_KEY);

        return p;
    }

    public static void setContext(Context ct) {
        context = ct;
    }


    public static void updateSession(String s) {
        param p = buildParam_AI(s);
        AsyncHTTP.get(p, new JsonResponseHandler() {
        });

    }
}
