package com.joel.assistant.utils.ActionHandlerFactory;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;

import java.util.HashMap;

/**
 * Created by Joel on 14-03-2016.
 */

public class handlerMap extends HashMap<String, ActionHandler> {

    public ActionHandler get(String key) {
        return super.get(key.toLowerCase());
    }


    public ActionHandler put(String key, ActionHandler value) {
        return super.put(key.toLowerCase(), value);
    }


    public ActionHandler get(String key, ActionHandler def) {
        if (containsKey(key)) {
            return get(key);
        } else
            return def;

    }

    public boolean containsKey(String key) {
        return super.containsKey(key.toLowerCase());
    }

}
