package com.joel.assistant.utils.events;

import android.content.Intent;
import android.provider.AlarmClock;
import android.provider.CalendarContract;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Joel on 5/12/2016.
 */
public class reminderHandler implements ActionHandler {

    private static reminderHandler rh = new reminderHandler();

    private reminderHandler() {

    }

    public void performAction1(JSONObject res) {
        try {
            JSONObject param = res.getJSONObject("parameters");
            String task = param.getString("task");
            String t = param.getString("time");
            String[] time = t.split(":");
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(time[0]));
            i.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(time[1]));
            i.putExtra(AlarmClock.EXTRA_MESSAGE, task);
            i.putExtra(AlarmClock.EXTRA_RINGTONE, (boolean[]) null);
            StateProvider.getActivity().startActivity(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static reminderHandler getInstance() {
        return rh;
    }

    @Override
    public void performAction(JSONObject res) {
        try {
            JSONObject param = res.getJSONObject("parameters");
            String task = param.getString("task");
            String t = param.getString("time");
            String[] time = t.split(":");
            long startDateMillis = System.currentTimeMillis() + ((((Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]) * 60) + Integer.parseInt(time[2]) * 60) * 1000);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            c.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            c.set(Calendar.SECOND, Integer.parseInt(time[2]));


            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra(CalendarContract.Events.TITLE, task);
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, c.getTimeInMillis());
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, c.getTimeInMillis() + 10000);
            intent.putExtra(CalendarContract.Events.ALL_DAY, false);// periodicity
            intent.putExtra(CalendarContract.Events.DESCRIPTION, task);
            StateProvider.getActivity().startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
