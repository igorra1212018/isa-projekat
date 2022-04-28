import axios from 'axios';
import authHeader from '../auth-header';

const USER_API_BASE_URL = 'http://localhost:8080/api/lodging/';

class LodgingService{

    getAllReservables(){
        return axios.get(USER_API_BASE_URL + 'all', { headers: authHeader() });
    }

    getAllReservablesSearch(searchParameters){
        return axios.post(USER_API_BASE_URL + 'search', searchParameters);
    }

    getReservable(id){
        return axios.get(USER_API_BASE_URL + id);
    }

    getAvailableReservablesReservationDates(id){
        return axios.get(USER_API_BASE_URL + 'get_available_reservation_dates/' + id);
    }

    reserveReservable(reservationParameters){
        return axios.post(USER_API_BASE_URL + 'reserve', reservationParameters, { headers: authHeader() });
    }
}

export default new LodgingService();