package com.discoveryit.assessment.service;

import com.discoveryit.assessment.model.Planet;
import com.discoveryit.assessment.model.Route;
import com.discoveryit.assessment.repository.PlanetRepository;
import com.discoveryit.assessment.repository.RouteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterstellarServiceTest {

    //system under test
    InterstellarService sut;

    @Mock
    private InterstellarGraph graph;
    @Mock
    private PlanetRepository planetRepo;
    @Mock
    private RouteRepository routeRepo;

    @Before
    public void setUp() {
        sut = new InterstellarService(graph, planetRepo, routeRepo);
    }


    @Test
    public void getAllPlanets() {

        //given
        List<Planet> plnts = new ArrayList<>();
        plnts.add(new Planet());
        plnts.add(new Planet());
        plnts.add(new Planet());
        plnts.add(new Planet());

        //when
        when(planetRepo.findAll()).thenReturn(plnts);

        List<Planet> planets = sut.getAllPlanets();

        //verify
        Assert.assertEquals("", 4, plnts.size());
    }

    @Test
    public void getAllRoutes() {

        //given
        List<Route> routes = new ArrayList<>();
        routes.add(new Route());
        routes.add(new Route());
        routes.add(new Route());
        routes.add(new Route());

        //when
        when(routeRepo.findAll()).thenReturn(routes);

        List<Planet> planets = sut.getAllPlanets();

        //verify
        Assert.assertEquals("", 4, routes.size());
    }

    @Test
    public void getDirections() {
    }
}