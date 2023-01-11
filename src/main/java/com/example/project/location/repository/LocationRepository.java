package com.example.project.location.repository;

import com.example.project.location.entitiy.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
