package com.joel.assistant.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import com.joel.assistant.R;
import com.joel.assistant.Views.Activities.MainActivity;
import com.joel.assistant.Views.Fragmants.Response_Text;

/**
 * Created by Joel on 2/13/2016.
 */
public class Handler_AI {

    static FragmentTransaction FT_Resp = null;
    static FragmentManager FM_Resp = null;
    static Context context;
    static Activity activity;
    static OkHttpClient oCli = new OkHttpClient();


    public static void setTransaction(FragmentTransaction ft) {
        FT_Resp = ft;
    }

    public static void setFragmentManager(FragmentManager fm) {
        FM_Resp = fm;
    }

    public static void setActivity(Activity a) {
        activity = a;
    }

    public static void showToast(final String data) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), data, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void process_update(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response res = Parse(s);
                String rt = "Error....!!!";


                try {

                    ResponseBody r = res.body();
                    String jResp = r.string();

                    Log.i("Json Response for Query", jResp);

                    JSONObject j = new JSONObject(jResp);

                    JSONObject result = j.getJSONObject("result");
                    if (result.has("speech"))
                        rt = result.getString("speech");
                    else if (result.has("fulfillment"))
                        if (result.getJSONObject("fulfillment").has("speech"))
                            rt = result.getJSONObject("fulfillment").getString("speech");

                    if (rt.isEmpty())
                        rt = "Try Another QUERY...!!!";
                    System.out.println("Final Response :  " + rt);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final String finalRt = rt;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateText(finalRt);
                    }
                });


            }
        }).start();
    }

    public static void updateText(String Res) {

        FragmentWithType Resp_FRAG = (FragmentWithType) FM_Resp.findFragmentByTag(Constants.Response);

        if (Resp_FRAG.isText() != true) {
            Resp_FRAG = new Response_Text();
            FT_Resp.replace(R.id.Response_Fragment_main, Resp_FRAG, Constants.Response);
        }
        Response_Text r = (Response_Text) Resp_FRAG;
        r.update(Res);
        MainActivity ma = (MainActivity)activity;

        ma.speak(Res);
    }

    public static Response Parse(String s) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(Constants.AI_HOST)
                .addPathSegment(Constants.AI_URL_SEG)
                .addQueryParameter("query", s)
                .addQueryParameter("lang", "en")
                .build();

        Request req = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", Constants.AI_AUTH)
                .addHeader("ocp-apim-subscription-key", Constants.AI_SUBSCR_KEY)
                .build();
        Response res = null;

        try {
            res = oCli.newCall(req).execute();
        } catch (IOException e) {
            showToast("Connection Error....");
            e.printStackTrace();
        }

        return res;


    }


}
