import axios from 'axios';
import authHeader from '../auth-header';

let USER_API_BASE_URL = 'http://localhost:8080/api/';

export default class ReservableService{

    CONTROLLER_ENDPOINT = ''

    constructor(CONTROLLER_ENDPOINT) {
        if(CONTROLLER_ENDPOINT == 'lodging' || CONTROLLER_ENDPOINT == 'boat' || CONTROLLER_ENDPOINT == 'fishinglesson')
            this.CONTROLLER_ENDPOINT = USER_API_BASE_URL + CONTROLLER_ENDPOINT + '/'
        else
            this.CONTROLLER_ENDPOINT = USER_API_BASE_URL + 'lodging/'
    }

    getAllReservables(){
        return axios.get(this.CONTROLLER_ENDPOINT + 'all', { headers: authHeader() });
    }

    getAllReservablesSearch(searchParameters, page){
        if(page == null)
            page = 0
        return axios.post(this.CONTROLLER_ENDPOINT + 'search/' + page, searchParameters);
    }

    getReservable(id){
        return axios.get(this.CONTROLLER_ENDPOINT + id);
    }

    getReservationsByUser(userId){
        return axios.get(this.CONTROLLER_ENDPOINT + 'reservations/' + userId, { headers: authHeader() });
    }

    getActionReservationsByReservable(reservableId){
        return axios.get(this.CONTROLLER_ENDPOINT + 'reservations/actions/' + reservableId, { headers: authHeader() });
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

    isSubscriberOf(userId, reservableId){
        return axios.get(this.CONTROLLER_ENDPOINT + 'subscribers?userId=' + userId + '&reservableId=' + reservableId, { headers: authHeader() });
    }

    getAllSubscribedReservables(userId){
        return axios.get(this.CONTROLLER_ENDPOINT + 'subscribers?userId=' + userId, { headers: authHeader() });
    }

    subscribeUserToReservable(userId, reservableId) {
        return axios.put(this.CONTROLLER_ENDPOINT + 'subscribers?userId=' + userId + '&reservableId=' + reservableId, "", { headers: authHeader() });
    }

    unsubscribeUserToReservable(userId, reservableId) {
        return axios.delete(this.CONTROLLER_ENDPOINT + 'subscribers?userId=' + userId + '&reservableId=' + reservableId, { headers: authHeader() });
    }
}