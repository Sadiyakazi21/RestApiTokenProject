package com.example.demo.model;

import java.util.List;

public class Node {
	private String name;
    private String location;
    private String type;
    private List<Node> nodeList;

    public Node() { }

    public Node(String name, String location, String type, List<Node> nodeList) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.nodeList = nodeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}

