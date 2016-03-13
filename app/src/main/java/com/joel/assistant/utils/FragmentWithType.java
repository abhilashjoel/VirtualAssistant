package com.joel.assistant.utils;

import android.app.Fragment;
import android.util.Log;

/**
 * Created by Joel on 2/13/2016.
 */

public abstract class FragmentWithType extends Fragment {

    public Boolean isText() {
        Log.i("Fragment Type", "isText() wasn't OVERRIDDEN..");
        return null;
    }

    public Boolean isMap() {
        Log.i("Fragment Type", "isMap() wasn't OVERRIDDEN..");
        return null;
    }

    public Boolean isWeb() {
        Log.i("Fragment Type", "isWeb() wasn't OVERRIDDEN..");
        return null;
    }

}
