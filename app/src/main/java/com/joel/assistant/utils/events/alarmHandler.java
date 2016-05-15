package com.joel.assistant.utils.events;

import android.content.Intent;
import android.provider.AlarmClock;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 5/12/2016.
 */
public class alarmHandler implements ActionHandler {

    public static alarmHandler ah = new alarmHandler();

    private alarmHandler() {

    }

    @Override
    public void performAction(JSONObject res) {
        try {
            String t = res.getJSONObject("parameters").getString("time");
            String[] time = t.split(":");

            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(time[0]));
            i.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(time[1]));
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "Wake UP....!!");
//            i.putExtra(AlarmClock.EXTRA_LENGTH,)
            StateProvider.getActivity().startActivity(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static alarmHandler getInstance() {
        return ah;
    }
}
