package com.joel.assistant.Views.Activities;

import android.app.FragmentManager;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joel.assistant.R;

import com.joel.assistant.Views.Fragmants.Frag_Request;
import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;
import com.joel.assistant.utils.TTS;

public class MainActivity extends AppCompatActivity implements Frag_Request.Communicator {

    TextToSpeech tts;
    FragmentManager m;

    @Override
    protected void onPause() {
        super.onPause();
        TTS.stopTTS();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TTS.stopTTS();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();
        StateProvider.setContext(getApplicationContext());

        initTTS();
        TTS.initTTS(getApplicationContext());

        Handler_AI.setContext(getApplicationContext());

        Response_Fragments.setActivity(this);
        try {
            Response_Fragments.initFragments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler_AI.updateSession("Call me Joel");


    }

    private void initTTS() {


    }


    @Override
    public void Process(String s) {
        System.out.println("Processing Query.....  " + s);
        ResponseHandler_AI.TextResponse("Processing...", "");
        Handler_AI.process_update(s);
    }


}
