package com.discoveryit.assessment.service;

import com.discoveryit.assessment.model.Planet;
import com.discoveryit.assessment.model.Route;
import com.discoveryit.assessment.repository.PlanetRepository;
import com.discoveryit.assessment.repository.RouteRepository;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class InterstellarService {

    private PlanetRepository planetRepository;

    private RouteRepository routeRepository;

    private InterstellarGraph graph;

    @Autowired
    public InterstellarService(InterstellarGraph graph, PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
        this.graph = graph;
    }

    /**
     * add Route
     *
     * @param route
     * @return
     */
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    /**
     * add planet
     *
     * @param planet
     * @return
     */
    public Planet addPlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    /**
     * get all planets
     *
     * @return
     */
    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    /**
     * get planet by ID or name
     *
     * @param name
     * @return
     */
    public Planet getPlanet(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        Optional<Planet> planetOptional = planetRepository.findById(name);
        return planetOptional.isPresent() ? planetOptional.get() : null;
    }

    /**
     * gets all routes
     *
     * @return
     */
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    /**
     * gets a shortest direction from earth to destination
     *
     * @return
     */
    public List<Route> getDirections(String sourceString, String destinationString) {

        Planet source = graph.vertexSet().stream().filter(planet -> planet.getId().equals(sourceString)).findFirst().get();
        Planet destination = graph.vertexSet().stream().filter(planet -> planet.getId().equals(destinationString)).findFirst().get();
        GraphPath<Planet, Route> sp = DijkstraShortestPath.findPathBetween(graph, source, destination);

        return sp.getEdgeList();
    }
}
