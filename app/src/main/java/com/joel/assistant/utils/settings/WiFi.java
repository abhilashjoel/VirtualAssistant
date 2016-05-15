package com.joel.assistant.utils.settings;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.joel.assistant.utils.StateProvider;

/**
 * Created by Joel on 08-05-2016.
 */
public class WiFi {
    WifiManager wm;
    private static WiFi wifi = new WiFi();

    private WiFi() {
        wm = (WifiManager) StateProvider.getActivity().getSystemService(Context.WIFI_SERVICE);

    }

    public static void on() {
        if (wifi.wm.isWifiEnabled() != true) {
            wifi.wm.setWifiEnabled(true);
        }
    }

    public static void off() {
        if (wifi.wm.isWifiEnabled() != true) {
            wifi.wm.setWifiEnabled(true);
        }
    }

    public static WiFi getInstance() {
        return wifi;
    }

}
