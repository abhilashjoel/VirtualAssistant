package com.joel.assistant.utils.WolframAPI;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joel on 28-04-2016.
 */
public class ResultPodList extends ArrayList<ResultPod> {

    public void tst() {
        Iterator<ResultPod> i = super.iterator();
        ResultPod x = i.next();
    }

    public void show() {
        Iterator<ResultPod> i = super.iterator();
        ResultPod res;
        while (i.hasNext()) {
            res = i.next();
            System.out.println(res.toString());
        }

    }

}
