package com.danat;

import java.util.LinkedList;
import java.util.PriorityQueue;

class RoutesCalculator {

    private Graph graph;

    public RoutesCalculator(Graph g) {
        graph = g;
    }

    String calculateRoute(LinkedList<Integer> route){
        int result = 0;
        try {
            int v = route.pop();
            while (!route.isEmpty()) {
                int w = route.pop();
                result += graph.getEdgeDistance(v, w);
                v = w;
            }
        } catch (EdgeNotFoundException e) {
            return "NO SUCH ROUTE";
        }
        return Integer.toString(result);
    }

    String calculateShortestDistance(Character from, Character to) {
        int s = graph.getTownId(from);
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> Integer.compare(x.getDistance(), y.getDistance()));
        int[] distanceTo = new int[graph.getVerticesCount()];
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            distanceTo[i] = Integer.MAX_VALUE;
        }
        distanceTo[s] = 0;
        pq.add(new Edge(s, 0));
        while (!pq.isEmpty()) {
            int v = pq.poll().getTo();
            for (Edge e : graph.getAdjacentEdges(v)) {
                int u = e.getTo();
                if (u == s & distanceTo[s] == 0) {
                    distanceTo[s] = distanceTo[v] + e.getDistance();
                } else if (distanceTo[u] > distanceTo[v] + e.getDistance()){
                    distanceTo[u] = distanceTo[v] + e.getDistance();
                    if (pq.contains(e)) {
                        pq.remove(e);
                        pq.add(new Edge(e.getTo(),  distanceTo[u]));
                    } else {
                        pq.add(e);
                    }
                }
            }
        }
        return Integer.toString(distanceTo[graph.getTownId(to)]);
    }

    String calculateRoutesByStops(Character from, Character to, int minStops, int maxStops) {
        return Integer.toString(routesByStops(graph.getTownId(to),minStops, maxStops, graph.getTownId(from), 0));
    }

    String calculateRoutesByDistance(Character from, Character to, int minDistance, int maxDistance) {
        return Integer.toString(routesByDistance(graph.getTownId(to),minDistance, maxDistance, graph.getTownId(from), 0));
    }

    private int routesByDistance(int destination, int minDistance, int maxDistance, int v, int distance) {
        if (distance >= maxDistance) {
            return 0;
        }
        int res = 0;
        if (distance > minDistance && distance < maxDistance & v == destination) {
            res++;
        }
        for (Edge e : graph.getAdjacentEdges(v)) {
            res += routesByDistance(destination, minDistance, maxDistance, e.getTo(), distance + e.getDistance());
        }
        return res;
    }

    private int routesByStops(int destination, int minStops, int maxStops, int v, int stop) {
        if (stop > maxStops) {
            return 0;
        }
        int res = 0;
        if (stop >= minStops && stop <= maxStops && v == destination) {
            res++;
        }
        for (Edge e : graph.getAdjacentEdges(v)) {
            res += routesByStops(destination, minStops, maxStops, e.getTo(), stop + 1);
        }
        return res;
    }
}
