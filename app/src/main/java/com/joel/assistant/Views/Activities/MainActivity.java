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

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Frag_Request.Communicator {

    TextToSpeech tts;
    FragmentManager m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();
        initFragments();
        initTTS();
    }

    private void initTTS() {




    }

    private void initFragments() {

        m = getFragmentManager();
        FragmentTransaction FT_Req = m.beginTransaction();
        Frag_Request fReq = new Frag_Request();
        FT_Req.add(R.id.Request_Fragment_main, fReq, Constants.Request);
        FT_Req.commit();
        fReq.setCommunicator(this);

        FragmentTransaction FT_Resp = m.beginTransaction();
        Response_Text fRespTxt = new Response_Text();
        FT_Resp.add(R.id.Response_Fragment_main,fRespTxt,Constants.Response);
        FT_Resp.commit();

        Handler_AI.setTransaction(FT_Resp);
        Handler_AI.setFragmentManager(m);
        Handler_AI.setActivity(this);




    }

    @Override
    public void Process(String s) {
        System.out.println("Processing Query.....  "+s);
        Handler_AI.process_update(s);
    }


}
