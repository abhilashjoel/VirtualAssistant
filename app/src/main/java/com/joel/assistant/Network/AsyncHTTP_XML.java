package com.joel.assistant.Network;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Joel on 29-04-2016.
 */
public class AsyncHTTP_XML extends AsyncHTTPGet {

    public static String TAG = "Async XML HTTP";
    XMLResponseHandler xhr;

    AsyncHTTP_XML(XMLResponseHandler xhr) {
        super(null);
        this.xhr = xhr;

    }

    @Override
    protected void onPostExecute(String body) {
        System.out.println("Requested url : " + req.url().url());
        Log.i(TAG, "HTTP Response :  " + body);
        if (res.isSuccessful() == false) {
            xhr.onFailure(res.code());
            return;
        }

        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(body.getBytes()));
            Element root = doc.getDocumentElement();

            xhr.onSuccess(res.code(), root);

        } catch (Exception e) {
            Log.e("Http Response", "Caught an Exception...");
            e.printStackTrace();
            xhr.onFailure(res.code());
        }

    }


}
