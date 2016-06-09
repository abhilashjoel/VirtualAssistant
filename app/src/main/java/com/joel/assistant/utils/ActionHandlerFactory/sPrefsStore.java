package com.joel.assistant.utils.ActionHandlerFactory;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 6/10/2016.
 */
public class sPrefsStore implements ActionHandler{
    public static String Tag = "UserPrefs";
    @Override
    public void performAction(JSONObject res) {
        try {
            String action = res.getString("action");
            if(action.equalsIgnoreCase("user.getname") == true)
                showName();
            else if(action.equalsIgnoreCase("user.setName") == true)
                saveName(res.getJSONObject("parameters"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showName() {
        SharedPreferences user = StateProvider.getActivity().getSharedPreferences(Constants.userPrefsTag, Context.MODE_PRIVATE);
        String name = user.getString("name","a Genious");
        Log.i(Tag,"Retrieved Name :  "+name);
        ResponseHandler_AI.TextResponse("You're "+name);
    }

    private void saveName(JSONObject param) throws JSONException {
        String name = param.getString("name");
        SharedPreferences user = StateProvider.getActivity().getSharedPreferences(Constants.userPrefsTag, Context.MODE_PRIVATE);
        SharedPreferences.Editor ue = user.edit();
        ue.putString("name", name);
        ue.commit();
        ResponseHandler_AI.TextResponse("I will remember that, "+name);
    }
}
