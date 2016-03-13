package com.joel.assistant.utils.ApplicationHandler;

import android.util.Log;

public abstract class appHandler {
    void onSuccess(String packageName) {
        Log.i("AppHandler", "Successfully mapped " + packageName);
    }

    void onFailure(String label) {
        Log.i("AppHandler", "Unable to locate the application : " + label);
    }
}
