package com.joel.assistant.utils.WolframAPI;

import android.util.Log;

import com.joel.assistant.Network.param;
import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.Utils_XML.NodeListExt;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by Joel on 28-04-2016.
 */
public class WolframAPI {

    private static WolframAPI wolfram = new WolframAPI();
    public static String TAG = "Wolfram API";

    private WolframAPI() {

    }

    public static ResultPodList BuildResultPods(Element root) {
        ResultPodList res = new ResultPodList();
        int i, j;

        NodeList podList = root.getElementsByTagName("pod");
        Node pod;

        String title, text, imageURL;
        Log.i(TAG, "Building Result Array");
        Log.i(TAG, "Found " + podList.getLength() + " pods");

        for (i = 0; i < podList.getLength(); i++) {
            pod = podList.item(i);

            title = getNamedAttrib(pod, "title");

            NodeListExt sPod = new NodeListExt(pod.getChildNodes());
            Node subpod = sPod.getByNodeName("subpod");

            NodeListExt sPodData = new NodeListExt(subpod.getChildNodes());

            text = sPodData.getByNodeName("plaintext").getTextContent();

            imageURL = getNamedAttrib(sPodData.getByNodeName("img"), "src");

            Log.i(TAG, "Title :  " + title);
            Log.i(TAG, "Text  :  " + text);
            Log.i(TAG, "Image URL :  " + imageURL);

            ResultPod resultPod = new ResultPod();
            resultPod.setTitle(title);
            resultPod.setText(text);
            resultPod.setImageURL(imageURL);
            res.add(resultPod);
        }

        return res;
    }

    private static String getNamedAttrib(Node pod, String key) {
        NamedNodeMap nm = pod.getAttributes();
        return nm.getNamedItem(key).getNodeValue();
    }


    public static param buildParams(String Query) {

        param p = new param();
        p.setProtocol("http");
        p.setHost(Constants.Wolfram_BaseUrl);
        p.setURLSegment(Constants.Wolfram_URL_SEG);
        p.addParam("appid", Constants.Wolfram_APPID);
        p.addParam("format", Constants.Wolfram_FORMAT);
        p.addParam("input", Query);

        return p;
    }
}
