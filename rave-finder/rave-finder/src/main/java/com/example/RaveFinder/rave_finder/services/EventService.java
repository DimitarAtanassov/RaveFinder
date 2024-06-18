package com.example.RaveFinder.rave_finder.services;
import com.example.RaveFinder.rave_finder.model.Event;
import com.example.RaveFinder.rave_finder.repository.EventRepository;
import com.example.RaveFinder.rave_finder.services.TicketMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TicketMasterService ticketMasterService;

    @Autowired
    public EventService(EventRepository eventRepository, TicketMasterService ticketMasterService) {
        this.eventRepository = eventRepository;
        this.ticketMasterService = ticketMasterService;

    }

    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    public void removeExpiredEvents() {
        LocalDate today = LocalDate.now();
        eventRepository.deleteByDateBefore(today);
    }

    public List<Event> searchEvents(String location, String artist)
    {
        List<Event> events;

        if(location != null && artist != null)
        {
            events = eventRepository.findByLocationAndArtistsContainingIgnoreCase(location, artist);
            if(events.isEmpty())
            {
                events = ticketMasterService.fetchAndSaveEventsByArtistAndLocation(location, artist);
            }
        }
        else if (location != null)
        {
            events = eventRepository.findByLocationIgnoreCase(location);
            if(events.isEmpty())
            {
                events = ticketMasterService.fetchAndSaveEventsByLocation(location);
            }
        }
        else if(artist != null)
        {
            events = eventRepository.findByArtistsContainingIgnoreCase(artist);
            if(events.isEmpty())
            {
                events = ticketMasterService.fetchAndSaveEventsByArtist(artist);
            }
        }
        else
        {
            events = Collections.emptyList();
        }
        
        return events;

    }
}
