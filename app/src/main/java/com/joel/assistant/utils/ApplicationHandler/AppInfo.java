package com.joel.assistant.utils.ApplicationHandler;

/**
 * Created by Joel on 12-03-2016.
 */


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.joel.assistant.utils.StateProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AppInfo {

    private static AppInfo provider = new AppInfo();
    HashMap<String, String> app;
    Context context;

    private AppInfo() {
        context = StateProvider.getContext();
        app = new HashMap<String, String>();
        initAppList();
    }

    AppInfo(Context ct) {
        app = new HashMap<String, String>();
        context = ct;
        initAppList();
    }


    public static void getPackageName(String label, appHandler ah) {
        if (provider.app.containsKey(label))
            ah.onSuccess(provider.app.get(label));
        else
            ah.onFailure(label);
    }

    public static void LaunchApp(String name, appHandler appHandler) {
        if (provider.app.containsKey(name.toLowerCase()))
            appHandler.onSuccess(provider.app.get(name.toLowerCase()));
        else
            appHandler.onFailure(name);
    }

    private void initAppList() {
        int flag = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES |
                PackageManager.GET_INTENT_FILTERS |
                PackageManager.GET_SIGNATURES |
                PackageManager.GET_RESOLVED_FILTER;

        PackageManager pm = context.getPackageManager();

        List<ApplicationInfo> app_info = pm.getInstalledApplications(flag);

        Iterator<ApplicationInfo> ai = app_info.iterator();

        ApplicationInfo a;
        String packageName, label;

        while (ai.hasNext()) {
            a = ai.next();
            label = pm.getApplicationLabel(a).toString();
            packageName = a.packageName;
            app.put(label.toLowerCase(), packageName);
//            Log.i(label, packageName);
        }
    }
}

