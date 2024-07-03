package com.example.RaveFinder.rave_finder.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.RaveFinder.rave_finder.model.TicketMasterEvent;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketMasterResponse {

    @JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded {

        @JsonProperty("events")
        private List<TicketMasterEvent> events;

        public List<TicketMasterEvent> getEvents() {
            return events;
        }

        public void setEvents(List<TicketMasterEvent> events) {
            this.events = events;
        }
    }
}
