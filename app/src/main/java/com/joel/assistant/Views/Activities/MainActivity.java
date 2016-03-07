package com.joel.assistant.Views.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.provider.SyncStateContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.joel.assistant.R;

import com.joel.assistant.R;
import com.joel.assistant.Views.Fragmants.Frag_Request;
import com.joel.assistant.Views.Fragmants.Response_Text;
import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.TTS;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Frag_Request.Communicator {

    TextToSpeech tts;
    FragmentManager m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();

        initTTS();
        TTS.initTTS(getApplicationContext());


        Response_Fragments.setActivity(this);
        try {
            Response_Fragments.initFragments();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initTTS() {




    }



    @Override
    public void Process(String s) {
        System.out.println("Processing Query.....  "+s);
        Handler_AI.process_update(s);
    }


}
