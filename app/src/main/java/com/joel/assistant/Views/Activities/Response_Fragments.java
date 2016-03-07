package com.joel.assistant.Views.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;

import com.joel.assistant.R;
import com.joel.assistant.Views.Fragmants.Frag_Request;
import com.joel.assistant.Views.Fragmants.Response_Text;
import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.TTS;

/**
 * Created by Joel on 08-03-2016.
 */
public class Response_Fragments {

    public static MainActivity activity = null;
    static FragmentManager fManager;

    public static Frag_Request fReq;// = null;
    public static Response_Text tRes;// = null;


    public static void setActivity(MainActivity a){
        activity = a;
    }


    public static void initFragments() throws Exception {

        Log.e("Fragment Store","In INIT Frag..");


        if(activity == null){
            Log.e("Fragment Store","Activity reference is null");
            throw new NullReferenceException();
        }
        fManager = activity.getFragmentManager();
        FragmentTransaction FT_Req = fManager.beginTransaction();
        fReq = new Frag_Request();
        FT_Req.add(R.id.Request_Fragment_main, fReq, Constants.Request);
        FT_Req.commit();
        fReq.setCommunicator(activity);

        Log.e("Fragment Store", "Init Req--->>>");


        SetTextResponse().update("Hello! What can I do for you.");
        TTS.speak("Hello! What can I do for you.");

        Log.e("Fragment Store", "Update Instrn---->>");


    }


    public static Response_Text SetTextResponse(){
        if(fManager.findFragmentByTag(Constants.Response_Text) != null) {
         System.out.println("Found Text Fragment..... From Tags...");
            return tRes;
        }
        FragmentTransaction FT_Resp = fManager.beginTransaction();
        if(tRes == null){
            Log.i("SetTextRespFrag","Found Null Reference... Creating a new Fragment");
            tRes = new Response_Text();
        }
        else{
            System.out.println("Obj Already Exists..... oohhh");
        }

        if(FT_Resp.isEmpty())
            FT_Resp.add(R.id.Response_Fragment_main, tRes, Constants.Response_Text);
        else
            FT_Resp.replace(R.id.Response_Fragment_main,tRes,Constants.Response_Text);
        FT_Resp.commit();

        return tRes;
    }


    static class NullReferenceException extends Exception{
        NullReferenceException(){
            super("Activity reference was NULL");
        }

    }

}
