package com.danat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Unit test for simple TrainsAndTownsApp.
 */
public class TrainsAndTownsAppTest
{
    @Test
    public void defaultTest() {
        Graph graph = new Graph(getDefaultRoutesStub());

        RoutesCalculator calculator = new RoutesCalculator(graph);

        assertEquals(calculator.calculateRoute(graph.parseRoute("A-B-C")), "9");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-D")), "5");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-D-C")), "13");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-E-B-C-D")), "22");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-E-D")), "NO SUCH ROUTE");

        assertEquals(calculator.calculateRoutesByStops('C', 'C', 1, 3), "2");
        assertEquals(calculator.calculateRoutesByStops('A', 'C', 4, 4), "3");

        assertEquals(calculator.calculateShortestDistance('A', 'C'), "9");
        assertEquals(calculator.calculateShortestDistance('B', 'B'),"9");

        assertEquals(calculator.calculateRoutesByDistance('C', 'C', 0, 30), "7");
    }

    @Test
    public void circleTest() {
        Graph graph = new Graph(getCircleRoutesStub());

        RoutesCalculator calculator = new RoutesCalculator(graph);

        assertEquals(calculator.calculateRoute(graph.parseRoute("A-B-C")), "NO SUCH ROUTE");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-D")), "2");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-D-C")), "7");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-E-B-C-D")), "NO SUCH ROUTE");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-E-D")), "NO SUCH ROUTE");

        assertEquals(calculator.calculateRoutesByStops('C', 'C', 1, 3), "0");
        assertEquals(calculator.calculateRoutesByStops('A', 'C', 4, 4), "0");

        assertEquals(calculator.calculateShortestDistance('A', 'C'), "7");
        assertEquals(calculator.calculateShortestDistance('B', 'B'),"19");

        assertEquals(calculator.calculateRoutesByDistance('C', 'C', 0, 30), "1");
    }

    @Test
    public void symmetricEdgesTest() {
        Graph graph = new Graph(getSymmetricEdgesStub());

        RoutesCalculator calculator = new RoutesCalculator(graph);

        assertEquals(calculator.calculateRoute(graph.parseRoute("A-B-C")), "24");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-D")), "20");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-D-C")), "36");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-E-B-C-D")), "44");
        assertEquals(calculator.calculateRoute(graph.parseRoute("A-E-D")), "20");

        assertEquals(calculator.calculateRoutesByStops('C', 'C', 1, 3), "7");
        assertEquals(calculator.calculateRoutesByStops('A', 'C', 4, 4), "24");

        assertEquals(calculator.calculateShortestDistance('A', 'C'), "16");
        assertEquals(calculator.calculateShortestDistance('B', 'B'),"16");

        assertEquals(calculator.calculateRoutesByDistance('C', 'C', 0, 30), "4");
    }

    private String[] getDefaultRoutesStub() {
        return "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7".split(", ");
    }

    private String[] getCircleRoutesStub() {
        return "AD2, DC5, CB2, BE4, EA6".split(", ");
    }

    private String[] getSymmetricEdgesStub() {
        return "AB12, BA12, BC12, CB12, CD16, DC16, AD20, DA20, AE8, EA8, BE8, EB8, CE8, EC8, ED12, DE12".split(", ");
    }
}
