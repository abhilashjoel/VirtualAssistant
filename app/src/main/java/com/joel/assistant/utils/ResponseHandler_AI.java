package com.joel.assistant.utils;

import android.util.Log;

import com.joel.assistant.Views.Activities.Response_Fragments;

/**
 * Created by Joel on 08-03-2016.
 */
public class ResponseHandler_AI {

    public static void TextResponse(String data, String tts) {
        Log.i("Text Response", data);
        Log.i("TTS Response", tts);
        Response_Fragments.SetTextResponse().update(data);
        TTS.speak(tts);
    }

    public static void TextResponse(String s) {
        TextResponse(s, s);
    }
}
