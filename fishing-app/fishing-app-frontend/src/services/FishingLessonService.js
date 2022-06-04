import axios from 'axios';
import authHeader from '../auth-header';

const BASE_URL = 'http://localhost:8080/api/fishinglesson/';

class FishingLessonService{
	
	addFishingLesson(fishingLesson){
		return axios.post(BASE_URL + 'add', fishingLesson , { headers: authHeader() });
	}

	getFishingLesson(id) {
		return axios.get(BASE_URL + id, { headers: authHeader() });
	}

	editFishingLesson(fishingLesson) {
		return axios.put(BASE_URL + 'edit', fishingLesson , { headers: authHeader() });
	}

	addAction(action) {
		return axios.post('http://localhost:8080/api/action/add', action, { headers: authHeader() })
	}

}

export default new FishingLessonService();