package com.joel.assistant.utils.ApplicationHandler;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 12-03-2016.
 */

public class launchApp implements ActionHandler {

    public static void launch(final Context context, final String name) {

        AppInfo.LaunchApp(name, new appHandler() {
            @Override
            void onFailure(String label) {
                Log.i("OnFailure()", "In Overridden class");
                super.onFailure(label);
                ResponseHandler_AI.TextResponse("Unable to locate the Application " + label, "Error in Processing Your Query...");
//                ResponseHandler_AI.TextResponse("App not found", "App not found");
            }

            @Override
            void onSuccess(String packageName) {
                super.onSuccess(packageName);
                ResponseHandler_AI.TextResponse("Opening " + name, "Launching " + name);
                PackageManager pm = context.getPackageManager();
                Intent i = pm.getLaunchIntentForPackage(packageName);
                context.startActivity(i);
            }
        });

    }

    @Override
    public void performAction(JSONObject res) {

        Log.e("Action Handler", "Application Handler Handler");
        try {
            String app_label = res.getJSONObject("parameters").getString("app_name");
            launch(StateProvider.getContext(), app_label);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
