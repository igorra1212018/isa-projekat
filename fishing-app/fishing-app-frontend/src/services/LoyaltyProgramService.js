import axios from 'axios';
import authHeader from '../auth-header';

let BASE_URL = 'http://localhost:8080/api/loyaltyProgram/';

class LoyaltyProgramService{

	getAllLevels() {
		return axios.get(BASE_URL + 'all', { headers: authHeader() });
	}

	deleteLevel(id) {
		return axios.delete(BASE_URL + id + '/delete', { headers: authHeader() });
	}

	addLevel(level) {
		return axios.post(BASE_URL + 'add', level , { headers: authHeader() });
	}

	changeUserPointsAdd(pointsAdd) {
		return axios.put(BASE_URL + 'changeUserPointsAdd/' + pointsAdd, {}, { headers: authHeader() });
	}

	changeOwnerPointsAdd(pointsAdd) {
		return axios.put(BASE_URL + 'changeOwnerPointsAdd/' + pointsAdd, {}, { headers: authHeader() });
	}

}

export default new LoyaltyProgramService();