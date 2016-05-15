package com.joel.assistant.utils.settings;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Joel on 08-05-2016.
 */
public class BlueTooth {

    private static BlueTooth blueTooth = new BlueTooth();
    private BluetoothAdapter bluetoothAdapter;

    private BlueTooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static void off() {
        if (blueTooth.bluetoothAdapter.isEnabled()) {
            blueTooth.bluetoothAdapter.disable();
        }
    }

    public static void on() {
        if (blueTooth.bluetoothAdapter.isEnabled() != true) {
            blueTooth.bluetoothAdapter.enable();
        }
    }

    public static BlueTooth getInstance() {
        return blueTooth;
    }
}
