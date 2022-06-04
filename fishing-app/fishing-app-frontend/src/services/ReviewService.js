import axios from 'axios';
import authHeader from '../auth-header';

const USER_API_BASE_URL = 'http://localhost:8080/api/reviews/';

class ReviewService{

    addReview(review){
        return axios.post(USER_API_BASE_URL + 'add', review, { headers: authHeader() });
    }

    getAllUserReviews() {
        return axios.get(USER_API_BASE_URL + '/all', { headers: authHeader() });
    }

    approveRequest(id){
        return axios.patch(USER_API_BASE_URL + id + '/approve', {}, { headers: authHeader() });
    }

    rejectRequest(id){
        return axios.patch(USER_API_BASE_URL + id + '/reject', {}, { headers: authHeader() });
    }
}

export default new ReviewService();