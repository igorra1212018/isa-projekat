import axios from 'axios';
import authHeader from '../auth-header';

const USER_API_BASE_URL = 'http://localhost:8080/api/lodging/';

class LodgingService{

    getLodgings(){
        return axios.get(USER_API_BASE_URL + 'all', { headers: authHeader() });
    }

    getLodging(id){
        return axios.get(USER_API_BASE_URL + id);
    }

    getAvailableLodgingReservationDates(id){
        return axios.get(USER_API_BASE_URL + 'get_available_lodging_reservation_dates/' + id);
    }

    getLodgingsBySearch(searchParameters){
        return axios.post(USER_API_BASE_URL + 'search', searchParameters);
    }

    reserveLodging(reservationParameters){
        return axios.post(USER_API_BASE_URL + 'reserve_lodging/', reservationParameters, { headers: authHeader() });
    }
}

export default new LodgingService();