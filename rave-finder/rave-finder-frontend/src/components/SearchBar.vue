<template>

    <div class="search-container">
      <input
        type="text"
        v-model="locationQuery"
        placeholder="Search by location..."
        class="search-input"
      />
      <input
        type="text"
        v-model="artistQuery"
        placeholder="Search by artist..."
        class="search-input"
      />
      <button @click="onSearch" class="search-button">Search</button>
      <div class="results-container">
        <div v-if="events.length === 0 && searched">
          <p class="no-events">No events found</p>
        </div>
        <div v-else>
          <div v-for="event in events" :key="event.id" class="event-card">
            <img :src="event.imageUrl" alt="Event Image" class="event-image"/>
            <div class="event-details">
              <h3>{{ event.name }}</h3>
              <p>{{ event.location }}</p>
              <p>{{ event.date }}</p>
              <a :href="event.url" target="_blank">More Info</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        locationQuery: '',
        artistQuery: '',
        events: [],
        searched: false
      };
    },
    methods: {
      async onSearch() {
        try {
          this.searched = true;
          const params = {};
          if (this.locationQuery) {
            params.location = this.locationQuery;
          }
          if (this.artistQuery) {
            params.artist = this.artistQuery;
          }
  
          const response = await axios.get(`http://localhost:8080/events/search`, { params });
          this.events = response.data;
        } catch (error) {
          console.error("Error fetching events:", error);
          this.events = [];
        }
      }
    }
  };
  </script>
  <style scoped>
  .search-container {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .search-input {
    width: 300px;
    padding: 10px;
    margin: 10px 0;
    border-radius: 5px;
    border: 2px solid transparent;
    outline: none;
    font-size: 16px;
    transition: border-color 0.3s;
    background-color: #f0f0f0;
    color: #000000;
    position: relative;
    animation: rainbow-border 3s linear infinite;
  }
  
  @keyframes rainbow-border {
    0% { border-color: #ff0000; }
    14% { border-color: #ff8000; }
    28% { border-color: #ffff00; }
    42% { border-color: #80ff00; }
    56% { border-color: #00ff00; }
    70% { border-color: #00ff80; }
    84% { border-color: #00ffff; }
    100% { border-color: #ff00ff; }
  }
  
  .search-input:hover {
    animation-play-state: paused;
  }
  
  .search-button {
    padding: 10px 20px;
    border-radius: 5px;
    border: none;
    background-color: #1e88e5;
    color: white;
    font-size: 16px;
    cursor: pointer;
  }
  
  .search-button:hover {
    background-color: #155da5;
  }
  
  .results-container {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  
  .no-events {
    font-size: 1.5em;
    color: #ff0000;
  }
  
  .event-card {
    border: 1px solid #ddd;
    border-radius: 5px;
    width: 300px;
    margin: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s;
    background-color: #1f1f1f;
  }
  
  .event-card:hover {
    transform: scale(1.05);
  }
  
  .event-image {
    width: 100%;
    border-bottom: 1px solid #ddd;
    border-radius: 5px 5px 0 0;
  }
  
  .event-details {
    padding: 10px;
  }
  
  .event-details h3 {
    margin: 0;
    font-size: 18px;
    color: #ffffff;
  }
  
  .event-details p {
    margin: 5px 0;
    color: #aaaaaa;
  }
  
  .event-details a {
    display: inline-block;
    margin-top: 10px;
    color: #1e88e5;
    text-decoration: none;
    font-weight: bold;
  }
  
  .event-details a:hover {
    text-decoration: underline;
  }
  </style>