package com.danat;

import java.util.*;

class Graph {

    private Map<Integer, Integer>[] adjacencyDistanceList;

    private List<Edge>[] adjacencyEdgeList;

    private Map<Character, Integer> townIds = new HashMap<>();

    private int verticesCount = 0;

    /**
     * Format of route should look like AB12
     * A and B are the names of towns. 12 - is the distance between A and B
     * @param routes
     */
    Graph(String[] routes) {
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
            int distance = Integer.parseInt(route.substring(2));
            addEdge(v, w, distance);
        }
    }

    /**
     *
     * @param from Name of town
     * @param to Name of town
     * @param distance distance between towns
     */
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

    /**
     * Format of route should look like list of towns divided by "-"
     * Example : A-B-C
     * @param route
     * @return
     */
    LinkedList<Integer> parseRoute(String route) {
        String[] towns = route.split("-");
        LinkedList<Integer> result = new LinkedList<>();
        if (towns.length < 2) {
            throw new RuntimeException("Town should be a letter");
        }
        for (String town : towns) {
            if (town.length() > 1) {
                throw new RuntimeException("Route should include more then one town");
            }
            result.add(getTownId(town.charAt(0)));
        }
        return result;
    }

    /**
     *
     * @param v - id of town
     * @param w - id of town
     * @return
     */
    int getEdgeDistance(int v, int w) {
        if (adjacencyDistanceList[v].containsKey(w)) {
            return adjacencyDistanceList[v].get(w);
        }
        throw new EdgeNotFoundException();
    }

    /**
     * Returns list of adjacent edges of vertex v
     * @param v
     * @return
     */
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
