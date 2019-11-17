package com.danat;

public class Edge {

    private int to;

    private int distance;

    public Edge(int to, int distance) {
        this.to = to;
        this.distance = distance;
    }

    public int getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }
}
