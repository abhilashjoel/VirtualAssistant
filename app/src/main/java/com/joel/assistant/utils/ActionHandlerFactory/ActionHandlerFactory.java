package com.joel.assistant.utils.ActionHandlerFactory;

import com.joel.assistant.utils.ApplicationHandler.launchApp;
import com.joel.assistant.utils.ResponseHandler_AI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 13-03-2016.
 */
public class ActionHandlerFactory {

    public static ActionHandler getActionHandler(JSONObject j) {

        if (j.has("action") != true) {
            return new DefaultHandler();
        }

        String action = null;
        try {
            action = j.getString("action");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String parts[] = action.split("\\.");
        System.out.println("Action :  " + action);
        System.out.println("------parts : " + parts.toString());

        if (action.equalsIgnoreCase("apps.open") == true) {
            return new launchApp();
        } else if (parts[0].equalsIgnoreCase("smalltalk") == true) {
            return new SmallTalkHandler();
        }
//        else if(action.equalsIgnoreCase())

        return new DefaultHandler();

    }
}
