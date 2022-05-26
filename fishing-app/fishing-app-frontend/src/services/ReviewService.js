import axios from 'axios';
import authHeader from '../auth-header';

const USER_API_BASE_URL = 'http://localhost:8080/api/reviews/';

class ReviewService{

    addReview(review){
        return axios.post(USER_API_BASE_URL + 'add', review, { headers: authHeader() });
    }
}

export default new ReviewService();