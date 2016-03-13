package com.joel.assistant.Network;

/**
 * Created by Joel on 07-03-2016.
 */

import java.util.HashMap;

public class param {

    HashMap<String, String> parameter;
    HashMap<String, String> header;
    String host;
    String URLSegment;
    String url;
    String protocol;

    public param() {
        parameter = new HashMap<String, String>();
        header = new HashMap<String, String>();
    }

    public void setProtocol(String p) {
        protocol = p;
    }

    public void setHost(String h) {
        host = h;
    }

    public void setURLSegment(String seg) {
        URLSegment = seg;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addParam(String key, String value) {
        parameter.put(key, value);
    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }


    public String getURL() {
        return url;
    }

    public HashMap<String, String> getParams() {
        return parameter;
    }

    public HashMap<String, String> getHeaders() {
        return header;
    }

    public String getHost() {
        return host;
    }

    public String getURLSegment() {
        return URLSegment;
    }

    public String getProtocol() {
        return protocol;
    }
}
