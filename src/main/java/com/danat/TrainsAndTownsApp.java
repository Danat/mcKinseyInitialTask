package com.danat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class TrainsAndTownsApp {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] routes = scanner.nextLine().split(", ");

        Graph graph = new Graph(routes);

        RoutesCalculator calculator = new RoutesCalculator(graph);
        bufferedWriter.write(calculator.calculateRoute(graph.parseRoute("A-B-C")));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateRoute(graph.parseRoute("A-D")));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateRoute(graph.parseRoute("A-D-C")));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateRoute(graph.parseRoute("A-E-B-C-D")));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateRoute(graph.parseRoute("A-E-D")));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateRoutesByStops('C', 'C', 1, 3));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateRoutesByStops('A', 'C', 4, 4));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateShortestDistance('A', 'C'));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateShortestDistance('B', 'B'));
        bufferedWriter.newLine();
        bufferedWriter.write(calculator.calculateRoutesByDistance('C', 'C', 0, 30));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
