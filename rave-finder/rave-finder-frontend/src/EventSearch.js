import axios from 'axios';


export default {
    // Returns an object containing component's data properties
    data() {
        return {
            locationQuery: '',
            artistQuery: '',
            events : [],
            searched : false
        };
    },

    methods: 
    {
        async onSearch(){
            try 
            {
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
            } catch(error) {
                console.error("Error fetching events:", error);
                this.events = [];
            }
            
        }    
    }

    
}