package com.joel.assistant.Network;

/**
 * Created by Joel on 07-03-2016.
 */

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class AsyncHTTPGet extends AsyncTask<param, Void, String> {


    String URL;
    JsonResponseHandler jHandler;

    HttpUrl url;
    Request req;
    Response res;
    static OkHttpClient oCli = new OkHttpClient();

    AsyncHTTPGet(String url, JsonResponseHandler jh) {
        URL = url;
        jHandler = jh;
    }

    AsyncHTTPGet(JsonResponseHandler jh) {
        jHandler = jh;
    }

    @Override
    protected String doInBackground(param... params) {

        param pa = params[0];
        boolean method;

        HashMap<String, String> p = pa.getParams();
        Iterator pi = p.entrySet().iterator();

        HashMap<String, String> h = pa.getHeaders();
        Iterator hi = h.entrySet().iterator();

//        String URL = pa.getURL();

        HashMap.Entry<String, String> item;
        HttpUrl.Builder urlX = new HttpUrl.Builder()
                .scheme(pa.getProtocol())
                .host(pa.getHost());
        String[] Urlsegments = pa.getURLSegment().split("/");
        for (String seg : Urlsegments) {
            if (seg.isEmpty() != true)
                urlX.addPathSegment(seg);
        }
//                .addPathSegment(pa.URLSegment);

        while (pi.hasNext()) {
            item = (HashMap.Entry) pi.next();
            urlX.addQueryParameter(item.getKey(), item.getValue());
        }

        url = urlX.build();

        Request.Builder reqX = new Request.Builder()
                .url(url)
                .get();

        while (hi.hasNext()) {
            item = (HashMap.Entry<String, String>) hi.next();
            reqX.addHeader(item.getKey(), item.getValue());
        }

        req = reqX.build();

        try {
            res = oCli.newCall(req).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return res.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String body) {
        super.onPostExecute(body);

        System.out.println("Requested url : " + req.url().url());

        if (res.isSuccessful() == false) {
            jHandler.onFailure(res.code());
            return;
        }

        try {
//            String body = res.body().string();
            Object x = new JSONTokener(body).nextValue();

            if (x instanceof JSONObject) {
                jHandler.onSuccess(res.code(), (JSONObject) x);
            } else if (x instanceof JSONArray) {
                jHandler.onSuccess(res.code(), (JSONArray) x);
            } else {
                jHandler.onFailure(res.code());
                Log.e("HTTP Request", "JSON Object/ JSON Array was not found...");
            }

        } catch (Exception e) {
            Log.e("Http Response", "Caught an Exception...");
            e.printStackTrace();
            jHandler.onFailure(res.code());
        }
    }

}
