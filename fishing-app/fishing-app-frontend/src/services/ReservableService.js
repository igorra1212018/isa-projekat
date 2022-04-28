import axios from 'axios';
import authHeader from '../auth-header';

let USER_API_BASE_URL = 'http://localhost:8080/api/';

class ReservableService{

    CONTROLLER_ENDPOINT = USER_API_BASE_URL

    constructor(CONTROLLER_ENDPOINT) {
        this.CONTROLLER_ENDPOINT = USER_API_BASE_URL + CONTROLLER_ENDPOINT + '/'
    }

    getAllReservables(){
        return axios.get(USER_API_BASE_URL + 'all', { headers: authHeader() });
    }

    getReservable(id){
        return axios.get(USER_API_BASE_URL + id);
    }

    getAvailableReservableReservationDates(id){
        return axios.get(USER_API_BASE_URL + 'get_available_lodging_reservation_dates/' + id);
    }

    getReservablesBySearch(searchParameters){
        return axios.post(USER_API_BASE_URL + 'search', searchParameters);
    }

    reserveReservable(reservationParameters){
        return axios.post(USER_API_BASE_URL + 'reserve_lodging/', reservationParameters, { headers: authHeader() });
    }
}