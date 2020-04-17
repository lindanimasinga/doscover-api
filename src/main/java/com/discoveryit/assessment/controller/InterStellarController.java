package com.discoveryit.assessment.controller;

import com.discoveryit.assessment.model.Planet;
import com.discoveryit.assessment.model.Route;
import com.discoveryit.assessment.service.InterstellarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/interstellar")
public class InterStellarController {

    @Autowired
    private InterstellarService interstellarService;

    @RequestMapping(value = "/route", method = RequestMethod.GET,
            produces = "application/json")
    public List<Route> getAllRutes(){
        return interstellarService.getAllRoutes();
    }

    @RequestMapping(value = "/route", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public List<Route> addRoute(Route route){
        return new ArrayList<>();
    }

    @RequestMapping(value = "/route/shortest", method = RequestMethod.GET,
            produces = "application/json")
    public List<Route> getShortestRouteFromEarth(@RequestParam String planetA, @RequestParam String planetB) {
        return interstellarService.getDirections(planetA, planetB);
    }

    @RequestMapping(value = "/planet", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet){
        return ResponseEntity.ok(interstellarService.addPlanet(planet));
    }

    @RequestMapping(value = "/planet", method = RequestMethod.GET,
            produces = "application/json")
    public List<Planet> getAllPlanets() {
        return interstellarService.getAllPlanets();
    }
    

 }
