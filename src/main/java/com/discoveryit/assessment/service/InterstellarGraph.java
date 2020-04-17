package com.discoveryit.assessment.service;

import com.discoveryit.assessment.model.Planet;
import com.discoveryit.assessment.model.Route;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import java.util.List;

public class InterstellarGraph extends DefaultDirectedWeightedGraph<Planet, Route> {

    public InterstellarGraph(Class<? extends Route> edgeClass) {
        super(edgeClass);
    }

    /**
     * Initialise the graph with its vertices and edges
     * @param planets
     * @param routes
     * @return Interstellar graphed transportation
     */

    public static InterstellarGraph create(List<Planet> planets, List<Route> routes) {
        InterstellarGraph interstellarGraph = new InterstellarGraph(Route.class);

        for (Planet pl : planets) {
            interstellarGraph.addVertex(pl);
        }

        for (Route route : routes) {
            Planet source = planets.stream().filter(planet -> planet.equals(route.getSource())).findFirst().get();
            Planet destination = planets.stream().filter(planet -> planet.equals(route.getDestination())).findFirst().get();

            interstellarGraph.addEdge(source, destination, route);
            interstellarGraph.setEdgeWeight(route, route.getDistance());
        }

        return interstellarGraph;
    }
}
