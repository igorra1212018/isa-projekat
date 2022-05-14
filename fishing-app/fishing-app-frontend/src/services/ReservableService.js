import axios from 'axios';
import authHeader from '../auth-header';

let USER_API_BASE_URL = 'http://localhost:8080/api/';

export default class ReservableService{

    CONTROLLER_ENDPOINT = ''

    constructor(CONTROLLER_ENDPOINT) {
        this.CONTROLLER_ENDPOINT = USER_API_BASE_URL + CONTROLLER_ENDPOINT + '/'
    }

    getAllReservables(){
        return axios.get(this.CONTROLLER_ENDPOINT + 'all', { headers: authHeader() });
    }

    getAllReservablesSearch(searchParameters){
        return axios.post(this.CONTROLLER_ENDPOINT + 'search', searchParameters);
    }

    getReservable(id){
        return axios.get(this.CONTROLLER_ENDPOINT + id);
    }

    getReservationsByUser(userId){
        return axios.get(this.CONTROLLER_ENDPOINT + 'reservations/' + userId, { headers: authHeader() });
    }

    getAvailableReservablesReservationDates(id){
        return axios.get(this.CONTROLLER_ENDPOINT + 'get_available_reservation_dates/' + id);
    }

    reserveReservable(reservationParameters){
        return axios.post(this.CONTROLLER_ENDPOINT + 'reserve', reservationParameters, { headers: authHeader() });
    }

    cancelReservation(reservationId){
        return axios.put(this.CONTROLLER_ENDPOINT + 'reservations/' + reservationId + '/cancel', reservationId, { headers: authHeader() });
    }
}