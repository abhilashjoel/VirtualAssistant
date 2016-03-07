package com.joel.assistant.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by Joel on 2/14/2016.
 */
public class TTS {

    public static TextToSpeech tts;

    public static void initTTS(Context ct) {
        tts = new TextToSpeech(ct, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }

    public static void speak(String s){
        tts.speak(String.valueOf(s.subSequence(0,s.length())), TextToSpeech.QUEUE_FLUSH, null,"");
//        tts.s
    }

    public static void stopTTS() {
        tts.stop();

    }
}
