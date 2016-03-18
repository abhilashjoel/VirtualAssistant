package com.joel.assistant.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.net.ContentHandler;

/**
 * Created by Joel on 13-03-2016.
 */
public class StateProvider {

    static Context context;
    static Activity activity;

    public static Context getContext() {
        if (context == null)
            Log.e("State Provider", "Context is not initialized");
        return context;
    }

    static public void setContext(Context ct) {
        context = ct;
    }

    public static Activity getActivity() {
        if (context == null)
            Log.e("State Provider", "Activity is not initialized");
        return activity;
    }

    static public void setActivity(Activity a) {
        activity = a;
    }

}
