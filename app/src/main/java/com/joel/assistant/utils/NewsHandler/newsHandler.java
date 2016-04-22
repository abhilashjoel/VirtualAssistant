package com.joel.assistant.utils.NewsHandler;

import com.joel.assistant.Views.Activities.Response_Fragments;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONObject;

/**
 * Created by Joel on 22-04-2016.
 */
public class newsHandler implements ActionHandler {
    @Override
    public void performAction(JSONObject res) {

        StateProvider.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Response_Fragments.SetNewsFragment();
            }
        });

    }

    private void setNewsFragment() {
    }
}
