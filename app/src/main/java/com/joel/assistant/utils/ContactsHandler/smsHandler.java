package com.joel.assistant.utils.ContactsHandler;

import android.app.UiAutomation;
import android.telephony.SmsManager;
import android.util.Log;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ResponseHandler_AI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 6/10/2016.
 */
public class smsHandler implements ActionHandler {
    public static String Tag = "SMS Handler";
    @Override
    public void performAction(JSONObject res) {
        try {
            JSONObject params = res.getJSONObject("parameters");
            String name = params.getString("name");
            String data = params.getString("data");

            String number = contactsHandler.getContact(name);
            if(number.isEmpty() == true){
                ResponseHandler_AI.TextResponse("Unable to locate the specified contact");
                return;
            }
            String op = "To: " + name + "\nData: "+data;
            Log.i(Tag,"Output :   "+op);
            ResponseHandler_AI.TextResponse(op);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number,null,data,null,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
