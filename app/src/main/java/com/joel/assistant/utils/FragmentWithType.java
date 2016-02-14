package com.joel.assistant.utils;

import android.app.Fragment;

/**
 * Created by Joel on 2/13/2016.
 */
public abstract class FragmentWithType extends Fragment{

    public abstract Boolean isText();
    public abstract Boolean isMap();
    public abstract Boolean isWeb();
}
