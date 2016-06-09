package com.joel.assistant.utils.WolframAPI;

import android.util.Log;

import com.joel.assistant.Network.AsyncHTTP;
import com.joel.assistant.Network.XMLResponseHandler;
import com.joel.assistant.Views.Activities.Response_Fragments;
import com.joel.assistant.Views.Fragmants.NullFragment;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandlerFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;

/**
 * Created by Joel on 29-04-2016.
 */
public class wolframHandler implements ActionHandler {

    public static String TAG = "WolframHandler";

    @Override
    public void performAction(JSONObject res) {
        if (res.has("resolvedQuery") != true) {
            ActionHandlerFactory.getDefaultHandler().performAction(res);
            return;
        }

        try {
            String ResolvedQuery = res.getString("resolvedQuery");

            Log.i(TAG, "Performing Async XML HTTP with Parameter " + ResolvedQuery);
            AsyncHTTP.getXML(WolframAPI.buildParams(ResolvedQuery), new XMLResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Element root) {
                    super.onSuccess(statusCode, root);
                    ResultPodList ResultPods = WolframAPI.BuildResultPods(root);

                    System.out.println("Contents of ResultPod Array..\n\n");

                    ResultPods.show();

                    NullFragment WolframFragment = Response_Fragments.SetNullFragment();
                    WolframFragment.showResultPods(ResultPods);

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
