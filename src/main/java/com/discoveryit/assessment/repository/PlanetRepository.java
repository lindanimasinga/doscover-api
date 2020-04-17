package com.discoveryit.assessment.repository;

import com.discoveryit.assessment.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, String> {

    Optional<Planet> findByName(String name);
}
