package com.joel.assistant.Views.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joel.assistant.R;
import com.joel.assistant.Views.Fragmants.Frag_Request;
import com.joel.assistant.Views.Fragmants.Fragment_Maps;
import com.joel.assistant.Views.Fragmants.Fragment_News;
import com.joel.assistant.Views.Fragmants.NullFragment;
import com.joel.assistant.Views.Fragmants.Response_Text;
import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.TTS;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Joel on 08-03-2016.
 */
public class Response_Fragments {

    public static MainActivity activity = null;
    static FragmentManager fManager;

    public static Frag_Request fReq;// = null;
    public static Response_Text tRes = null;// = null;
    public static Fragment_News nRes;
    public static NullFragment nullRes;
    public static Fragment_Maps fMaps;

    public static Fragment currentFragment;
    public static String currentFragmentTAG;

    public static void setActivity(MainActivity a) {
        activity = a;
    }


    public static void initFragments() throws Exception {

        Log.e("Fragment Store", "In INIT Frag..");


        if (activity == null) {
            Log.e("Fragment Store", "Activity reference is null");
            throw new NullReferenceException();
        }
        fManager = activity.getFragmentManager();
        FragmentTransaction FT_Req = fManager.beginTransaction();
        fReq = new Frag_Request();
        FT_Req.add(R.id.Request_Fragment_main, fReq, Constants.Request);
        FT_Req.commit();
        fReq.setCommunicator(activity);
        SetTextResponse("Hello");
//        SetNewsFragment();

        Log.e("Fragment Store", "Init Req--->>>");
    }


    public static Response_Text SetTextResponse(String data) {
        String Tag = "Text Response Init";
        if (fManager.findFragmentByTag(Constants.Response_Text) != null) {
            System.out.println("Found Text Fragment..... From Tags...");
            tRes.update(data);
            return tRes;
        }
        Log.i(Tag, "Creating new Text Fragment");
        FragmentTransaction FT_Resp = fManager.beginTransaction();
        tRes = Response_Text.newInstance(data);

        LinearLayout ll = (LinearLayout) activity.findViewById(R.id.Response_Fragment_main);
        ll.removeAllViews();

        if (currentFragment != null) {
            Log.i(Tag, "calling Replace...");
            FT_Resp.replace(R.id.Response_Fragment_main, tRes, Constants.Response_Text);
        } else {
            Log.i(Tag, "Calling add...");
            FT_Resp.add(R.id.Response_Fragment_main, tRes, Constants.Response_Text);
        }

        FT_Resp.commit();

        currentFragment = tRes;
        currentFragmentTAG = Constants.Response_Text;

        return tRes;
    }

    public static Fragment_News SetNewsFragment() {
        String TAG = "News Fragment Init";

        if (fManager.isDestroyed() == true) {
            System.out.println("Are you Fucking with me...");
            fManager = activity.getFragmentManager();
        }
        FragmentTransaction ft = fManager.beginTransaction();

        Log.i(TAG, "Creating new Fragment");
        nRes = new Fragment_News();

        LinearLayout ll = (LinearLayout) activity.findViewById(R.id.Response_Fragment_main);
        ll.removeAllViews();

        if (currentFragment != null) {
            Log.i(TAG, "Calling Replace...");
            ft.replace(R.id.Response_Fragment_main, nRes, Constants.Response_News);
        } else {
            Log.i(TAG, "Calling Add..");
            ft.add(R.id.Response_Fragment_main, nRes, Constants.Response_News);
        }
        currentFragment = nRes;
        currentFragmentTAG = Constants.Response_News;

        ft.commit();

        fManager.executePendingTransactions();
        return nRes;
    }


    public static NullFragment SetNullFragment() {
        String Tag = "Null Fragment init";
        if (fManager.isDestroyed() == true) {
            fManager = activity.getFragmentManager();
        }

        FragmentTransaction ft = fManager.beginTransaction();
        nullRes = new NullFragment();
        if (currentFragment != null) {
            Log.i(Tag, "Calling replace");
            ft.replace(R.id.Response_Fragment_main, nullRes, Constants.Response_null);
        } else {
            Log.i(Tag, "Calling add");
            ft.add(R.id.Response_Fragment_main, nullRes, Constants.Response_null);
        }

        ft.commit();
        fManager.executePendingTransactions();
        currentFragment = nullRes;
        currentFragmentTAG = Constants.Response_null;
        return nullRes;
    }

    static class NullReferenceException extends Exception {
        NullReferenceException() {
            super("Activity reference was NULL");
        }

    }

    public static Fragment_Maps setMapFragment2() {

        SupportMapFragment mf = new SupportMapFragment();

        FragmentTransaction ft = fManager.beginTransaction();
//        ft.replace(R.id.Response_Fragment_main, mf,Constants.Response_Maps);
//        currentFragment = mf.;

        return null;
    }

    public static Fragment_Maps setMapFragment(Fragment_Maps.mapsUpdater arg) {

        String Tag = "Map Fragment init";
        if (fManager.isDestroyed() == true) {
            fManager = activity.getFragmentManager();
        }

        FragmentTransaction ft = fManager.beginTransaction();
        fMaps = Fragment_Maps.getInstance(arg);


        if (currentFragment != null) {
            Log.i(Tag, "Calling replace");
            ft.replace(R.id.Response_Fragment_main, fMaps, Constants.Response_Maps);
        } else {
            Log.i(Tag, "Calling add");
            ft.add(R.id.Response_Fragment_main, fMaps, Constants.Response_Maps);
        }

        ft.commit();
//        fManager.executePendingTransactions();

        currentFragment = fMaps;
        currentFragmentTAG = Constants.Response_Maps;

        return fMaps;
    }

    public static void setQueryString(String q){

        String Tag = "Request Fragment handler";
        if(fReq == null){
            Log.e(Tag, "Fragment Instance was null...");
            return;
        }

        fReq.setQuery(q);
    }

}

/* news Frag--- test
    public static Fragment_News SetNewsFragment(){
        String TAG = "News Fragment Init";
        if(fManager.findFragmentByTag(Constants.Response_News) != null){
            System.out.println(TAG+"  found News Fragment... From tags,,,");
            return nRes;
        }
        LinearLayout l = (LinearLayout) activity.findViewById(R.id.Request_Fragment_main);

        fManager = activity.getFragmentManager();
        if(fManager.isDestroyed() == true){
            System.out.println("Are you Fucking with me...");
            fManager = activity.getFragmentManager();
        }
        FragmentTransaction ft = fManager.beginTransaction();

        if(nRes == null){
            Log.i(TAG,"Found Null Reference.... Creating a new Object");
            nRes = new Fragment_News();
        }else
            Log.i(TAG,"Found a News Fragment Object");

        if(ft.isEmpty())
            ft.add(R.id.Response_Fragment_main,nRes,Constants.Response_News);
        else {
            System.out.println("cTag : "+currentFragmentTAG+"  Class"+fManager.findFragmentByTag(currentFragmentTAG).getClass().getName());
            ft.detach(fManager.findFragmentByTag(currentFragmentTAG));
            ft.remove(fManager.findFragmentByTag(currentFragmentTAG));
            ft.hide(fManager.findFragmentByTag(currentFragmentTAG));
            ft.commit();
            fManager.executePendingTransactions();
            ft = fManager.beginTransaction();
            ft.replace(R.id.Response_Fragment_main, nRes, Constants.Response_News);
        }
//        ft.attach(nRes);
        ft.commit();
        fManager.executePendingTransactions();
        return nRes;
    }

 */