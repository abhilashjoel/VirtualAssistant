package com.joel.assistant.Network;

/**
 * Created by Joel on 08-03-2016.
 */
public class AsyncHTTP {

    public static void get(String url, param p, JsonResponseHandler j) {
        p.setUrl(url);
        AsyncHTTPGet g = new AsyncHTTPGet(url, j);
        g.execute(p);
    }

    public static void get(param p, JsonResponseHandler j) {
//        p.setUrl(url);
        AsyncHTTPGet g = new AsyncHTTPGet(j);
        g.execute(p);
    }

    public static void getXML(param p, XMLResponseHandler xhr) {

        AsyncHTTP_XML asyncClient = new AsyncHTTP_XML(xhr);
        asyncClient.execute(p);
    }
}
