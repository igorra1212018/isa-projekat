import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/api/';

class LodgingService{

    getLodgings(){
        return axios.get(USER_API_BASE_URL + 'lodgings');
    }

    getLodgingsBySearch(searchParameters){
        return axios.post(USER_API_BASE_URL + 'search_lodgings', searchParameters);
    }
}

export default new LodgingService();