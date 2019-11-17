package com.danat;

import java.util.*;

class Graph {

    private Map<Integer, Integer>[] adjacencyDistanceList;

    private List<Edge>[] adjacencyEdgeList;

    private Map<Character, Integer> townIds = new HashMap<>();

    private int verticesCount = 0;

    Graph(String[] routes) {
        Set <Character> towns = new HashSet<>();
        for (String route : routes) {
            char v = route.charAt(0);
            char w = route.charAt(1);
            if (!townIds.containsKey(v)) {
                townIds.put(v, verticesCount++);
            }
            if (!townIds.containsKey(w)) {
                townIds.put(w, verticesCount++);
            }
        }
        adjacencyDistanceList = new Map[verticesCount];
        adjacencyEdgeList = new List[verticesCount];
        for (String route : routes) {
            char v = route.charAt(0);
            char w = route.charAt(1);
            int distance = Character.getNumericValue(route.charAt(2));
            addEdge(v, w, distance);
        }
    }

    void addEdge(char from, char to, int distance) {
        int v = townIds.get(from);
        int w = townIds.get(to);
        if (adjacencyDistanceList[v] == null) {
            adjacencyDistanceList[v] = new HashMap<>();
        }
        if (adjacencyEdgeList[v] == null) {
            adjacencyEdgeList[v] = new ArrayList<>();
        }
        adjacencyDistanceList[v].put(w, distance);
        Edge e = new Edge(w, distance);
        adjacencyEdgeList[v].add(e);
    }

    LinkedList<Integer> parseRoute(String route) {
        String[] towns = route.split("-");
        LinkedList<Integer> result = new LinkedList<>();
        if (towns.length < 2) {
            throw new RuntimeException("Town should be letter");
        }
        for (String town : towns) {
            if (town.length() > 1) {
                throw new RuntimeException("Route should include more then one town");
            }
            result.add(getTownId(town.charAt(0)));
        }
        return result;
    }

    int getEdgeDistance(int v, int w) {
        if (adjacencyDistanceList[v].containsKey(w)) {
            return adjacencyDistanceList[v].get(w);
        }
        throw new EdgeNotFoundException();
    }

    List<Edge> getAdjacentEdges(int v) {
        return adjacencyEdgeList[v];
    }

    int getTownId(Character v) {
        if (townIds.containsKey(v)) {
            return townIds.get(v);
        }
        throw new RuntimeException("Town not found");
    }

    int getVerticesCount() {
        return verticesCount;
    }
}
