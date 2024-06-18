package com.example.RaveFinder.rave_finder.services;

import com.example.RaveFinder.rave_finder.model.Event;
import com.example.RaveFinder.rave_finder.model.TicketMasterEvent;
import com.example.RaveFinder.rave_finder.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TicketMasterService {

    private static final Logger logger = Logger.getLogger(TicketMasterService.class.getName());

    private final EventRepository eventRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public TicketMasterService(EventRepository eventRepository, RestTemplate restTemplate) {
        this.eventRepository = eventRepository;
        this.restTemplate = restTemplate;
    }

    public List<Event> fetchAndSaveEventsByLocation(String location) {
        String url = buildUrl("music", "KnvZfZ7vAvF", location, null);
        logger.info("Constructed URL: " + url);
        return fetchAndSaveEvents(url);
    }

    public List<Event> fetchAndSaveEventsByArtist(String artistName) {
        String url = buildUrl("music", "KnvZfZ7vAvF", null, artistName);
        logger.info("Constructed URL: " + url);
        return fetchAndSaveEvents(url);
    }

    public List<Event> fetchAndSaveEventsByArtistAndLocation(String artistName, String location) {
        String url = buildUrl("music", "KnvZfZ7vAvF", location, artistName);
        logger.info("Constructed URL: " + url);
        return fetchAndSaveEvents(url);
    }

    private String buildUrl(String classificationName, String genreId, String city, String keyword) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://app.ticketmaster.com/discovery/v2/events.json")
                .queryParam("apikey", "9ncrKs0VKYpCUzvLuOHtgdHqik3D0kDI")
                .queryParam("classificationName", classificationName)
                .queryParam("genreId", genreId);
        
        if (city != null) {
            builder.queryParam("city", city.replace(" ", "+"));
        }
        
        if (keyword != null) {
            builder.queryParam("keyword", keyword.replace(" ", "+"));
        }

        return builder.toUriString();
    }

    private List<Event> fetchAndSaveEvents(String url) {
        TicketMasterResponse response = restTemplate.getForObject(url, TicketMasterResponse.class);

        List<Event> events = new ArrayList<>();
        if (response != null && response.getEmbedded() != null && response.getEmbedded().getEvents() != null) {
            for (TicketMasterEvent ticketMasterEvent : response.getEmbedded().getEvents()) {
                Event event = new Event();
                event.setName(ticketMasterEvent.getName());
                event.setDate(LocalDate.parse(ticketMasterEvent.getDates().getStart().getLocalDate()));
                event.setUrl(ticketMasterEvent.getUrl());

                // Process images - take the first image URL
                String imageUrl = ticketMasterEvent.getImages() != null && !ticketMasterEvent.getImages().isEmpty()
                        ? ticketMasterEvent.getImages().get(0).getUrl()
                        : null;
                event.setImageUrl(imageUrl);

                // Process artists
                List<String> artists = new ArrayList<>();
                if (ticketMasterEvent.getEmbedded() != null && ticketMasterEvent.getEmbedded().getAttractions() != null) {
                    for (TicketMasterEvent.Attraction attraction : ticketMasterEvent.getEmbedded().getAttractions()) {
                        artists.add(attraction.getName());
                    }
                }
                event.setArtists(artists);

                // Process location
                String location = "Unknown Location";
                if (ticketMasterEvent.getEmbedded() != null && ticketMasterEvent.getEmbedded().getVenues() != null) {
                    logger.info("Venues: " + ticketMasterEvent.getEmbedded().getVenues());
                    TicketMasterEvent.Venue venue = ticketMasterEvent.getEmbedded().getVenues().get(0);
                    if (venue.getCity() != null) {
                        location = venue.getCity().getName();
                    }
                }
                event.setLocation(location);

                            // Process price ranges
                if (ticketMasterEvent.getPriceRanges() != null && !ticketMasterEvent.getPriceRanges().isEmpty()) {
                    TicketMasterEvent.PriceRange priceRange = ticketMasterEvent.getPriceRanges().get(0);
                    event.setMinPrice(priceRange.getMin());
                    event.setMaxPrice(priceRange.getMax());
                } else {
                    event.setMinPrice(null);
                    event.setMaxPrice(null);
                }

                events.add(event);
            }
        }

        return eventRepository.saveAll(events);
    }
}
