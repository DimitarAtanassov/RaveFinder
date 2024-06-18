package com.example.RaveFinder.rave_finder.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "event_artists", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "artist")
    private List<String> artists;

    @Column(nullable = true)
    private Double minPrice;
    
    @Column(nullable = true)
    private Double maxPrice;
    

    public Event() {}

    public Event(String name, String location, LocalDate date, String url, String imageUrl, List<String> artists, Double minPrice, Double maxPrice) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.url = url;
        this.imageUrl = imageUrl;
        this.artists = artists;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
