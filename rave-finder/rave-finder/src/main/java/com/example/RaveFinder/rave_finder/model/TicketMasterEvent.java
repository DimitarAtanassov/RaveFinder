package com.example.RaveFinder.rave_finder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketMasterEvent {

    private String name;
    private Dates dates;
    private List<Image> images;
    private String url;
    @JsonProperty("_embedded")
    private Embedded embedded;
    private List<PriceRange> priceRanges;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public List<PriceRange> getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(List<PriceRange> priceRanges) {
        this.priceRanges = priceRanges;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dates {
        private Start start;

        public Start getStart() {
            return start;
        }

        public void setStart(Start start) {
            this.start = start;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Start {
            @JsonProperty("localDate")
            private String localDate;

            public String getLocalDate() {
                return localDate;
            }

            public void setLocalDate(String localDate) {
                this.localDate = localDate;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded {
        @JsonProperty("attractions")
        private List<Attraction> attractions;

        @JsonProperty("venues")
        private List<Venue> venues;

        public List<Attraction> getAttractions() {
            return attractions;
        }

        public void setAttractions(List<Attraction> attractions) {
            this.attractions = attractions;
        }

        public List<Venue> getVenues() {
            return venues;
        }

        public void setVenues(List<Venue> venues) {
            this.venues = venues;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Venue {
        private City city;
        private String name;

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class City {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attraction {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PriceRange {
        private String type;
        private String currency;
        private double min;
        private double max;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }
    }
}
