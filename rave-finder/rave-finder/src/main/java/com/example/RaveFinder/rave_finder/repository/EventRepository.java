package com.example.RaveFinder.rave_finder.repository;

import com.example.RaveFinder.rave_finder.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Custom query methods can be added here if needed

    List<Event> findByNameContainingIgnoreCase(String name);

    List<Event> findByLocationContainingIgnoreCase(String location);

    List<Event> findByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(String name, String location);

    void deleteByDateBefore(LocalDate date);

    List<Event> findByLocationAndArtistsContainingIgnoreCase(String city, String artist);
    List<Event> findByLocationIgnoreCase(String city);
    List<Event> findByArtistsContainingIgnoreCase(String artist);
}
