package com.danat;

import java.util.ArrayList;
import java.util.List;

public class MinEdgePriorityQueue {

    private int size = 0;

    private List<Edge> queue = new ArrayList<>();

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public Edge poll() {
        if (size == 0)
            return null;
        int s = --size;
        Edge result = queue.get(0);
        Edge x = queue.get(s);
        queue.remove(s);
        if (s != 0)
            siftDown(0, x);
        return result;
    }

    public void add(Edge e) {
        if (e == null)
            throw new NullPointerException();
        if (size == 0)
            set(0, e);
        else
            siftUp(size, e);
    }

    public void decreaseByDistance() {

    }

    private void siftUp(int key, Edge item) {
        while (key > 0) {
            int parent = (key - 1) >>> 1;
            Edge e = queue.get(parent);
            if (compare(item, e) >= 0)
                break;
            set(key, e);
            key = parent;
        }
        set(key, item);
    }

    private void siftDown(int key, Edge item) {
        int half = size >>> 1;
        while (key < half) {
            int child = (key << 1) + 1;
            Edge c = queue.get(child);
            int right = child + 1;
            if (right < size && compare(c, queue.get(right)) > 0)
                c = queue.get(child = right);
            if (compare(item, c) <= 0)
                break;
            set(key, c);
            key = child;
        }
        set(key, item);
    }

    private int compare(Edge x, Edge y) {
        return Integer.compare(x.getDistance(), y.getDistance());
    }

    private void set(int key, Edge item) {
        if (key == size) {
            queue.add(item);
            size++;
        } else {
            queue.set(key, item);
        }
    }
}
