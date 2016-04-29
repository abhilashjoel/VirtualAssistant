package com.joel.assistant.utils.Utils_XML;

/**
 * Created by Joel on 29-04-2016.
 */

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListExt {

    NodeList nodeList;

    public NodeListExt(NodeList nl) {
        nodeList = nl;
    }

    public Node getByNodeName(String name) {
        int i, n = nodeList.getLength();
        Node node;
        for (i = 0; i < n; i++) {
            node = nodeList.item(i);
            if (node.getNodeName().equals(name) == true)
                return node;
        }
        return null;
    }
}
