<template>
  <div class="app-container">
    <h1 class="title">Rave Finder.</h1>
    <SearchBar />
  </div>
</template>

<script>
import SearchBar from './components/SearchBar.vue';

export default {
  name: 'App',
  components: {
    SearchBar
  }
};
</script>

<style scoped>
body, html {
  background-color: #121212;
  color: #ffffff;
  height: 100%;
  margin: 0;
  font-family: Arial, sans-serif;
}

.app-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  min-height: 100vh;
}

.title {
  font-size: 2.5em;
  margin-bottom: 20px;
  text-align: center;
  background: conic-gradient(from 0deg, #ff0000, #ff8000, #ffff00, #80ff00, #00ff00, #00ff80, #00ffff, #ff00ff, #ff0000);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: rainbow-text 3s linear infinite;
}

@keyframes rainbow-text {
  0% { background-position: 0% 50%; }
  100% { background-position: 100% 50%; }
}
</style>
