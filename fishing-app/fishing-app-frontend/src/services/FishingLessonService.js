import axios from 'axios';
import authHeader from '../auth-header';

const BASE_URL = 'http://localhost:8080/api/fishinglesson/';

class FishingLessonService{
	
	addFishingLesson(fishingLesson){
		return axios.post(BASE_URL + 'add', fishingLesson , { headers: authHeader() });
	}

}

export default new FishingLessonService();