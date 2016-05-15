package com.joel.assistant.utils.Translation;

import com.joel.assistant.Network.param;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.ResponseHandler_AI;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;

/**
 * Created by Joel on 5/12/2016.
 */
public class BingTranslator implements ActionHandler {
    @Override
    public void performAction(JSONObject res) {

        try {
            JSONObject param = res.getJSONObject("parameters");
            String text = param.getString("text");
            String l = param.getString("lang");
            String lang = LanguageCode.getCode(l);
            if (lang == null) {
                ResponseHandler_AI.TextResponse("Couldn't identify the language " + lang);
                return;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void Translate(String text, String Language) {


    }

    public static param buildParams() {
        param p = new param();
        p.setHost(Constants.MS_Translator_BaseURL);
        p.setURLSegment(Constants.MS_Translator_URL_Seg);
        p.setProtocol("https");
//        p.addParam();

        return p;
    }
}
