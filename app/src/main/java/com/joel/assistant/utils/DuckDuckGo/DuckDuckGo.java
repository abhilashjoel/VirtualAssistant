package com.joel.assistant.utils.DuckDuckGo;

import com.joel.assistant.Network.AsyncHTTP;
import com.joel.assistant.Network.JsonResponseHandler;
import com.joel.assistant.Network.param;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandlerFactory;
import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.ResponseHandler_AI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 29-04-2016.
 */
public class DuckDuckGo implements ActionHandler {
    @Override
    public void performAction(final JSONObject res) {

        if (res.has("resolvedQuery") != true) {
            ActionHandlerFactory.getDefaultHandler().performAction(res);
            return;

        }

        try {
            String resolvedQuery = res.getString("resolvedQuery");

            AsyncHTTP.get(buildParams(resolvedQuery), new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject res_json) {
                    super.onSuccess(statusCode, res_json);

                    System.out.println("DuckDuckGo Response : " + res_json);

                    try {
                        String Abstract = res_json.getString("Abstract");

                        if (Abstract.isEmpty() == true) {
                            ActionHandler wolfram = ActionHandlerFactory.getWolframHandler();
                            wolfram.performAction(res);
                            return;
                        }

                        ResponseHandler_AI.TextResponse(Abstract);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public param buildParams(String ResolvedQuery) {
        param p = new param();
        p.setProtocol("http");
        p.setHost(Constants.DuckDuckGo_BaseURL);
        p.setURLSegment(Constants.DuckDuckGo_URL_Seg);
        p.addParam("format", Constants.DockDuckGo_Format);
        p.addParam("q", ResolvedQuery);

        return p;
    }
}
