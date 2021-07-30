package com.rccode.model;


public class Dictionary {
    Node[] nodes;

    public Dictionary() {
        nodes = new Node[38];
    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }
}
