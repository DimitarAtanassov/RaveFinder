package com.example.RaveFinder.rave_finder.controllers;
import com.example.RaveFinder.rave_finder.services.EventService;
import com.example.RaveFinder.rave_finder.model.Event;
import com.example.RaveFinder.rave_finder.services.TicketMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final TicketMasterService ticketMasterService;
    private final EventService eventService;

    @Autowired
    public EventController(TicketMasterService ticketMasterService, EventService eventService) {
        this.ticketMasterService = ticketMasterService;
        this.eventService = eventService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/searchByLocation")
    public List<Event> searchEventsByLocation(@RequestParam String location) {
        return ticketMasterService.fetchAndSaveEventsByLocation(location);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/searchByArtist")
    public List<Event> searchEventsByArtist(@RequestParam String artist) {
        return ticketMasterService.fetchAndSaveEventsByArtist(artist);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/searchByArtistAndLocation")
    public List<Event> searchEventsByArtistAndLocation(@RequestParam String artist, @RequestParam String location) {
        return ticketMasterService.fetchAndSaveEventsByArtistAndLocation(artist, location);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam(required = false) String location, @RequestParam(required = false) String artist) {
        return eventService.searchEvents(location, artist);
    }
}
