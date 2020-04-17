package com.discoveryit.assessment.model;

import org.jgrapht.graph.DefaultWeightedEdge;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Route")
public class Route extends DefaultWeightedEdge {

    @Id
    @Column(name = "planetId")
    private String id;

    @OneToOne
    @JoinColumn(name= "source")
    @NotNull
    private Planet source;

    @OneToOne
    @JoinColumn(name= "destination")
    @NotNull
    private Planet destination;

    private float distance;

    public Route(String id, Planet source, Planet destination, float distance){
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public Route(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Planet getSource() {
        return source;
    }

    public void setSource(Planet source) {
        this.source = source;
    }

    public Planet getDestination() {
        return destination;
    }

    public void setDestination(Planet destination) {
        this.destination = destination;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Float.compare(route.distance, distance) == 0 &&
                id.equals(route.id) &&
                source.equals(route.source) &&
                destination.equals(route.destination);
    }
}
